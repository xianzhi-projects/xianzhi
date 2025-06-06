
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

package io.xianzhi.system.bootstrap.dao.dataobj;

import com.baomidou.mybatisplus.annotation.TableName;
import io.xianzhi.common.mybatis.plus.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * OAuth2客户端实体
 *
 * @author Max
 * @since 1.0.0
 */

@Data
@TableName(value = "sys_oauth2_client")
@EqualsAndHashCode(callSuper = true)
public class OAuth2ClientDO extends BaseDO {

    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 客户端发布时间
     */
    private Date clientIdIssuedAt;
    /**
     * 客户端秘钥
     */
    private String clientSecret;
    /**
     * 客户端秘钥过期时间
     */
    private Date clientSecretExpiresAt;
    /**
     * 客户端名称
     */
    private String clientName;
    /**
     * 客户端授权的方法
     */
    private String clientAuthenticationMethods;

    /**
     * 授权类型
     */
    private String authorizationGrantTypes;
    /**
     * 重定向地址
     */
    private String redirectUris;

    /**
     * 退出后重定向地址
     */
    private String postLogoutRedirectUris;
    /**
     * 作用域名
     */
    private String scopes;

    /**
     * 客户端设置
     */
    private String clientSettings;

    /**
     * token设置
     */
    private String tokenSettings;
}

