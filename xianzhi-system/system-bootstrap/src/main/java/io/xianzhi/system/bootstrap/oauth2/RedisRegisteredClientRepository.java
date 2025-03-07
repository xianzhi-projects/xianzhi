

/*
 *  Copyright 2025 XianZhi Group .
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.xianzhi.system.bootstrap.oauth2;


import com.fasterxml.jackson.databind.JsonNode;
import io.xianzhi.common.oauth2.authorization.enums.GrantTypeEnum;
import io.xianzhi.common.oauth2.authorization.properties.OAuth2Properties;
import io.xianzhi.common.oauth2.code.OAuth2Code;
import io.xianzhi.common.oauth2.exception.OAuth2Exception;
import io.xianzhi.common.redis.RedisHandler;
import io.xianzhi.common.security.properties.SecurityProperties;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.utils.JSONUtils;
import io.xianzhi.system.bootstrap.constants.AuthorizationInfoConstant;
import io.xianzhi.system.bootstrap.dao.dataobj.OAuth2ClientDO;
import io.xianzhi.system.bootstrap.dao.mapper.OAuth2ClientMapper;
import io.xianzhi.system.security.context.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.*;

/**
 * 客户端缓存处理
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisRegisteredClientRepository implements RegisteredClientRepository, InitializingBean {
    /**
     * 客户端信息持久层
     */
    private final OAuth2ClientMapper oAuth2ClientMapper;
    /**
     * 缓存处理
     */
    private final RedisHandler redisProcessor;
    /**
     * oauth2配置
     */
    private final OAuth2Properties oAuth2Properties;
    /**
     * 安全配置
     */
    private final SecurityProperties securityProperties;

    /**
     * Saves the registered client.
     *
     * <p>
     * IMPORTANT: Sensitive information should be encoded externally from the implementation, e.g. {@link RegisteredClient#getClientSecret()}
     *
     * @param registeredClient the {@link RegisteredClient}
     */
    @Override
    public void save(RegisteredClient registeredClient) {
        if (null == registeredClient) {
            log.error("保存客户端信息失败，客户端信息为空");
            throw new BusinessException(OAuth2Code.CLIENT_ERROR);
        }
        if (StringUtils.hasText(registeredClient.getId()) && !registeredClient.getId().contains("-")) {
            update(registeredClient);
        } else {
            insert(registeredClient);
        }
    }

    /**
     * Returns the registered client identified by the provided {@code id},
     * or {@code null} if not found.
     *
     * @param id the registration identifier
     * @return the {@link RegisteredClient} if found, otherwise {@code null}
     */
    @Override
    public RegisteredClient findById(String id) {
        if (StringUtils.hasText(id)) {
            OAuth2ClientDO clientDO = redisProcessor.hGet(AuthorizationInfoConstant.SYS_OAUTH2_ID, id, OAuth2ClientDO.class);
            if (null == clientDO) {
                clientDO = oAuth2ClientMapper.queryById(id);
                if (null == clientDO) {
                    log.error("客户端信息不存在，客户端主键ID:{}", id);
                    throw new BusinessException(OAuth2Code.CLIENT_ERROR);
                }
                redisProcessor.hSet(AuthorizationInfoConstant.SYS_OAUTH2_ID, id, clientDO);
            }
            return clientDOToRegisterClient(clientDO);
        }
        return null;
    }

    /**
     * Returns the registered client identified by the provided {@code clientId},
     * or {@code null} if not found.
     *
     * @param clientId the client identifier
     * @return the {@link RegisteredClient} if found, otherwise {@code null}
     */
    @Override
    public RegisteredClient findByClientId(String clientId) {
        if (StringUtils.hasText(clientId)) {
            OAuth2ClientDO clientDO = redisProcessor.hGet(AuthorizationInfoConstant.SYS_OAUTH2_CLIENT_ID, clientId, OAuth2ClientDO.class);
            if (null == clientDO) {
                clientDO = oAuth2ClientMapper.queryByClientId(clientId);
                if (null == clientDO) {
                    log.error("客户端信息不存在，客户端ID:{}", clientId);
                    throw new OAuth2Exception(new ResponseResult<>(OAuth2Code.CLIENT_ERROR, null));
                }
                redisProcessor.hSet(AuthorizationInfoConstant.SYS_OAUTH2_CLIENT_ID, clientId, clientDO);
            }
            return clientDOToRegisterClient(clientDO);
        }
        return null;
    }

    /**
     * 新增客户端信息
     *
     * @param registeredClient 客户端信息
     */
    private void insert(RegisteredClient registeredClient) {
        // 判断客户端ID是否存在
        if (oAuth2ClientMapper.existsClientByIdAndIdNot(registeredClient.getClientId(), null)) {
            throw new RuntimeException("clientId已经存在");
        }
        // 判断客户端名称是否存在
        if (oAuth2ClientMapper.existsClientByNameAndIdNot(registeredClient.getClientName(), null)) {
            throw new RuntimeException("clientName已经存在");
        }
        OAuth2ClientDO OAuth2ClientDO = registeredClientToOAuth2ClientDO(registeredClient);
        UserContextHolder.setAnonymousUser();
        oAuth2ClientMapper.insert(OAuth2ClientDO);

    }

    private void update(RegisteredClient registeredClient) {

    }


    private RegisteredClient clientDOToRegisterClient(OAuth2ClientDO entity) {
        RegisteredClient.Builder builder = RegisteredClient.withId(entity.getId())
                .clientId(entity.getClientId())
                .clientSecret(entity.getClientSecret())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
        String authorizationGrantTypes = entity.getAuthorizationGrantTypes();
        List<JsonNode> jsonNodes = JSONUtils.parseArray(authorizationGrantTypes, JsonNode.class);


        List<AuthorizationGrantType> authorizationGrantTypeList = jsonNodes.stream().map(item -> {
            String value = item.get("value").asText();
            switch (value) {
                case "refresh_token":
                    return AuthorizationGrantType.REFRESH_TOKEN;
                case "password":
                    return AuthorizationGrantType.PASSWORD;
                case "client_credentials":
                    return AuthorizationGrantType.CLIENT_CREDENTIALS;
                default:
                    throw new IllegalArgumentException("Unknown authorization grant type: " + value);
            }
        }).toList();
        for (AuthorizationGrantType authorizedGrantType : authorizationGrantTypeList) {
            builder.authorizationGrantType(authorizedGrantType);
        }
        // 回调地址
        Optional.ofNullable(entity.getRedirectUris())
                .ifPresent(redirectUri -> Objects.requireNonNull(JSONUtils.parseArray(entity.getRedirectUris(), String.class)).stream()
                        .filter(StringUtils::hasText)
                        .forEach(builder::redirectUri));

        // scope
        Optional.ofNullable(entity.getScopes())
                .ifPresent(scope -> Objects.requireNonNull(JSONUtils.parseArray(scope, String.class)).stream()
                        .filter(StringUtils::hasText)
                        .forEach(builder::scope));

        // postLogoutRedirectUris
        Optional.ofNullable(entity.getPostLogoutRedirectUris())
                .ifPresent(postRedirectUri -> Objects.requireNonNull(JSONUtils.parseArray(entity.getPostLogoutRedirectUris(), String.class)).stream()
                        .filter(StringUtils::hasText)
                        .forEach(builder::postLogoutRedirectUri));


        JsonNode tokenSettings = JSONUtils.toObject(entity.getTokenSettings(), JsonNode.class);
        JsonNode clientSettings = JSONUtils.toObject(entity.getClientSettings(), JsonNode.class);
        String tokenSettingsText = tokenSettings.asText();
        String clientSettingsText = clientSettings.asText();
        tokenSettings = JSONUtils.parseObject(tokenSettingsText, JsonNode.class);
        clientSettings = JSONUtils.parseObject(clientSettingsText, JsonNode.class);


        JsonNode settings = clientSettings.get("settings");
        String server = settings.get("server").asText();

        double text = tokenSettings.get("accessTokenTimeToLive").asDouble();

        return builder
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofHours((long) text))
                        .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                        .refreshTokenTimeToLive(Duration.ofHours((long) tokenSettings.get("refreshTokenTimeToLive").asDouble()))
                        .idTokenSignatureAlgorithm(SignatureAlgorithm.RS256)
                        .reuseRefreshTokens(true).build())
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(clientSettings.get("requireAuthorizationConsent").asBoolean())
                        .requireProofKey(clientSettings.get("requireProofKey").asBoolean())
                        .setting("server", server).build()).build();
    }


    public OAuth2ClientDO registeredClientToOAuth2ClientDO(RegisteredClient registeredClient) {
        OAuth2ClientDO entity = new OAuth2ClientDO();
        entity.setClientId(registeredClient.getClientId());
        entity.setClientSecret(registeredClient.getClientSecret());
        entity.setClientName(registeredClient.getClientName());
        entity.setClientSettings(JSONUtils.toJSONString(registeredClient.getClientSettings()));
        entity.setClientAuthenticationMethods(JSONUtils.toJSONString(registeredClient.getClientAuthenticationMethods()));
        if (null == registeredClient.getClientIdIssuedAt()) {
            entity.setClientIdIssuedAt(new Date());

        } else {
            entity.setClientIdIssuedAt(Date.from(registeredClient.getClientIdIssuedAt()));
        }

        if (null != registeredClient.getClientSecretExpiresAt()) {
            entity.setClientSecretExpiresAt(Date.from(registeredClient.getClientSecretExpiresAt()));
        }
        entity.setScopes(JSONUtils.toJSONString(registeredClient.getScopes()));
        entity.setRedirectUris(JSONUtils.toJSONString(registeredClient.getRedirectUris()));
        entity.setTokenSettings(JSONUtils.toJSONString(registeredClient.getTokenSettings()));
        entity.setAuthorizationGrantTypes(JSONUtils.toJSONString(registeredClient.getAuthorizationGrantTypes()));
        entity.setPostLogoutRedirectUris(JSONUtils.toJSONString(registeredClient.getPostLogoutRedirectUris()));
        return entity;
    }


    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (oAuth2ClientMapper.selectCount(null) < 1) {
            UserContextHolder.setAnonymousUser();
            RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                    .clientId("xianzhi")
                    .clientName("xianzhi")
                    .clientSecret(new BCryptPasswordEncoder().encode("xianzhi"))
                    .tokenSettings(TokenSettings
                            .builder()
                            .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                            .accessTokenTimeToLive(Duration.ofDays(2000))
                            .refreshTokenTimeToLive(Duration.ofDays(2000))
                            .build())
                    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .authorizationGrantType(new AuthorizationGrantType(GrantTypeEnum.PASSWORD.getCode()))
                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                    .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                    .scope("server")
                    .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).setting("server", "admin").build())
                    .build();
            save(registeredClient);
        }
    }
}