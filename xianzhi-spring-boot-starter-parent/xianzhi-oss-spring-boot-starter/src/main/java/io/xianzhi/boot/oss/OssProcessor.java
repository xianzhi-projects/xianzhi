/*
 * Copyright 2024 XianZhi Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.xianzhi.boot.oss;

/**
 * Handles operations related to Object Storage Service (OSS), such as file uploads, downloads, and deletions.
 * This class abstracts the details of interacting with the storage provider.
 * <p>
 * Example usage includes storing and retrieving files in a cloud-based storage solution like AWS S3 or Alibaba Cloud OSS.
 *
 * @author Max
 * @since 1.0.0
 */
public class OssProcessor {

    /**
     * Creates a bucket in the OSS provider.
     * <p>
     * This method checks if the bucket already exists. If it does, an exception is thrown.
     * Otherwise, it proceeds to create the bucket.
     *
     * @param bucketName The name of the bucket to create. Bucket names must follow OSS naming rules.
     * @throws io.xianzhi.core.exception.BusinessException if the bucket name is invalid or null or bucket already exists
     * @since 1.0.0
     */
    public void createBucket(String bucketName) {

    }

}
