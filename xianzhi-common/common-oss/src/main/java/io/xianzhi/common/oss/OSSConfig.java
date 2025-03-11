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

package io.xianzhi.common.oss;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

/**
 * OSS配置类
 *
 * @author Max
 * @since 1.0.0
 */
@Configuration
@RequiredArgsConstructor
public class OSSConfig {


    private final OSSProperties ossProperties;


    @Bean
    public S3Client s3Client() {
        // 创建 S3 客户端，配置 MinIO 端点和凭证
        return S3Client.builder()
                .endpointOverride(URI.create(ossProperties.getEndpoint()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(ossProperties.getAccessKey(), ossProperties.getAccessSecret()))) // 设置访问密钥和秘密密钥
                .region(Region.of(ossProperties.getRegion()))
                .forcePathStyle(ossProperties.getPathStyleAccess())
                .build();
    }

    @Bean
    public S3Presigner s3Presigner() {
        // 创建 S3 预签名器，用于生成预签名 URL
        return S3Presigner.builder()
                .endpointOverride(URI.create(ossProperties.getEndpoint()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(ossProperties.getAccessKey(), ossProperties.getAccessSecret()))) // 设置访问密钥和秘密密钥
                .region(Region.US_EAST_1)
                .build();
    }
}
