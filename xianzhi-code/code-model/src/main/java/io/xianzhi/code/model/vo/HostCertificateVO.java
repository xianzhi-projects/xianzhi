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

package io.xianzhi.code.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 主机凭证出参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class HostCertificateVO implements Serializable {

    /**
     * 主键ID
     */
    private String id;
    /**
     * 凭证名称
     */
    private String certName;
    /**
     * 凭证描述
     */
    private String certDesc;
    /**
     * 凭证类型
     */
    private String certType;
    /**
     * 主机用户名
     */
    private String username;
    /**
     * 主机密码
     */
    private String password;
    /**
     * 主机私钥
     */
    private String privateKey;
}
