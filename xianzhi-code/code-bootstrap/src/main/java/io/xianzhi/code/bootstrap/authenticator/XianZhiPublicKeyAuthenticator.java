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

package io.xianzhi.code.bootstrap.authenticator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.common.AttributeRepository;
import org.apache.sshd.common.config.keys.KeyUtils;
import org.apache.sshd.common.digest.BuiltinDigests;
import org.apache.sshd.server.auth.AsyncAuthException;
import org.apache.sshd.server.auth.pubkey.PublickeyAuthenticator;
import org.apache.sshd.server.session.ServerSession;
import org.springframework.stereotype.Component;

import java.security.PublicKey;

/**
 * 公钥认证
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class XianZhiPublicKeyAuthenticator implements PublickeyAuthenticator {

    /**
     * 秘钥ID Session key
     */
    protected static final AttributeRepository.AttributeKey<String> SECRET_KEY = new AttributeRepository.AttributeKey<>();
    /**
     * 秘钥业务类
     */
//    private final SecretKeyBusiness secretKeyBusiness;

    /**
     * Check the validity of a public key.
     *
     * @param username the username
     * @param key      the key
     * @param session  the server session
     * @return a boolean indicating if authentication succeeded or not
     * @throws AsyncAuthException If the authentication is performed asynchronously
     */
    @Override
    public boolean authenticate(String username, PublicKey key, ServerSession session) throws AsyncAuthException {
        // 获取公钥指纹
        String digest = KeyUtils.getFingerPrint(BuiltinDigests.sha256, key);
        // 根据公钥指纹查询出秘钥ID
//        String secretKeyId = secretKeyBusiness.getSecretKeyByFingerprint(digest);
        // 设置秘钥ID到session中，后续可以根据秘钥ID检查权限
//        session.setAttribute(SECRET_KEY, secretKeyId);
        return true;
    }
}
