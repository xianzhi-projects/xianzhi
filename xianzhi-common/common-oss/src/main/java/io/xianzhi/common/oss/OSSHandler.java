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

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.UploadPartPresignRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * OSS处理
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
public class OSSHandler {

    /**
     * OSS客户端
     */
    private final S3Client s3Client;
    /**
     * OSS预签名器
     */
    private final S3Presigner s3Presigner;


    /**
     * 检查指定的存储桶是否存在。
     * 通过发送 HEAD 请求检查桶状态，返回布尔值表示是否存在。
     *
     * @param bucketName 桶的名称，用于标识目标存储桶，必须唯一且符合 S3 命名规范（例如小写字母、数字、连字符）。
     * @return 返回布尔值，true 表示桶存在，false 表示桶不存在。
     */
    public boolean doesBucketExist(String bucketName) {
        try {
            HeadBucketRequest request = HeadBucketRequest.builder()
                    .bucket(bucketName) // 指定要检查的桶名称
                    .build();
            s3Client.headBucket(request);
            return true;
        } catch (S3Exception e) {
            log.error("Error checking bucket: {}", e.getMessage());
            if (e.statusCode() == 404) {
                return false;
            }
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * 创建一个新的存储桶。
     * 如果桶不存在，则创建；如果已存在，则跳过。
     *
     * @param bucketName 桶的名称，要创建的存储桶的唯一标识符。
     */
    public void createBucket(String bucketName) {
        if (!doesBucketExist(bucketName)) {
            CreateBucketRequest request = CreateBucketRequest.builder()
                    .bucket(bucketName) // 指定要创建的桶名称
                    .build();
            s3Client.createBucket(request);
        }
    }

    /**
     * 删除指定的存储桶。
     * 注意：桶必须为空，否则会失败。
     *
     * @param bucketName 桶的名称，要删除的存储桶的唯一标识符。
     */
    public void deleteBucket(String bucketName) {
        DeleteBucketRequest request = DeleteBucketRequest.builder()
                .bucket(bucketName) // 指定要删除的桶名称
                .build();
        s3Client.deleteBucket(request);
    }

    /**
     * 上传文件到指定的存储桶。
     * 将客户端上传的文件存储到 OSS 中，使用指定的键保存。
     *
     * @param bucketName 桶的名称，目标存储桶的唯一标识符。
     * @param key        对象的键，文件的唯一标识符，通常是文件名或路径（例如 "folder/file.txt"）。
     * @param file       要上传的文件，类型为 MultipartFile，通常从 HTTP 请求中获取。
     * @throws IOException 如果文件读取或上传过程中发生 I/O 错误。
     */
    public void uploadFile(String bucketName, String key, MultipartFile file) throws IOException {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName) // 指定目标桶
                .key(key) // 指定文件键
                .build();
        s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
    }

    /**
     * 下载指定存储桶中的文件。
     * 返回文件的字节数组，适用于小文件下载。
     *
     * @param bucketName 桶的名称，存储文件的桶的唯一标识符。
     * @param key        对象的键，要下载的文件的唯一标识符。
     * @return 返回文件的字节数组。
     * @throws IOException 如果下载过程中发生 I/O 错误。
     */
    public byte[] downloadFile(String bucketName, String key) throws IOException {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName) // 指定目标桶
                .key(key) // 指定文件键
                .build();
        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request);
        return response.readAllBytes();
    }

    /**
     * 从 OSS URL 下载数据。
     *
     * @param fileUrl OSS URL
     * @return 文件的字节数组
     * @throws IOException 如果网络请求或数据读取失败
     */
    public static byte[] downloadImageData(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        try (InputStream inputStream = url.openStream();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }

    /**
     * 删除指定存储桶中的对象。
     * 根据桶名称和键删除特定的文件。
     *
     * @param bucketName 桶的名称，包含目标对象的桶的唯一标识符。
     * @param key        对象的键，要删除的文件的唯一标识符。
     */
    public void deleteObject(String bucketName, String key) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucketName) // 指定目标桶
                .key(key) // 指定文件键
                .build();
        s3Client.deleteObject(request);
    }

    /**
     * 列出指定存储桶中的所有对象。
     * 返回桶中所有对象的键列表。
     *
     * @param bucketName 桶的名称，要查询的存储桶的唯一标识符。
     * @return 返回对象键的列表，每个键是字符串类型。
     */
    public List<String> listObjects(String bucketName) {
        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(bucketName) // 指定目标桶
                .build();
        ListObjectsV2Response response = s3Client.listObjectsV2(request);
        return response.contents().stream()
                .map(S3Object::key) // 提取每个对象的键
                .collect(Collectors.toList());
    }

    /**
     * 生成用于上传对象的预签名 URL。
     * 客户端可以使用此 URL 直接上传文件到 OSS，无需通过服务器中转。
     *
     * @param bucketName 桶的名称，目标存储桶的唯一标识符。
     * @param key        对象的键，上传后文件的唯一标识符。
     * @param duration   预签名 URL 的有效时长，例如 Duration.ofMinutes(10) 表示 10 分钟。
     * @return 返回预签名 URL 字符串。
     */
    public PreUploadUrlVO generatePresignedUrlForUpload(String bucketName, String key, Duration duration) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName) // 指定目标桶
                .key(key) // 指定文件键
                .build();
        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(duration) // 设置 URL 有效时长
                .putObjectRequest(request)
                .build();
        return new PreUploadUrlVO(bucketName, key, s3Presigner.presignPutObject(presignRequest).url().toString());
    }


    /**
     * 获取文件元数据
     *
     * @param bucketName 桶名称
     * @param objectKey  文件在 MinIO 中的路径
     * @return 文件元数据（大小和类型）
     * @throws NoSuchKeyException 如果文件不存在
     */
    public HeadObjectResponse getFileMetadata(String bucketName, String objectKey) throws NoSuchKeyException {
        HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();
        return s3Client.headObject(headObjectRequest);
    }


    /**
     * 生成用于下载对象的预签名 URL。
     * 客户端可以使用此 URL 直接从 OSS 下载文件。
     *
     * @param bucketName 桶的名称，存储文件的桶的唯一标识符。
     * @param key        对象的键，要下载的文件的唯一标识符。
     * @param duration   预签名 URL 的有效时长，例如 Duration.ofMinutes(10)。
     * @return 返回预签名 URL 字符串。
     */
    public String generatePresignedUrlForDownload(String bucketName, String key, Duration duration) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName) // 指定目标桶
                .key(key) // 指定文件键
                .build();
        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(duration) // 设置 URL 有效时长
                .getObjectRequest(request)
                .build();
        return s3Presigner.presignGetObject(presignRequest).url().toString();
    }

    /**
     * 复制存储桶中的对象。
     * 将一个对象从源位置复制到目标位置，支持跨桶复制。
     *
     * @param sourceBucket 源桶的名称，包含源对象的桶。
     * @param sourceKey    源对象的键，要复制的文件的唯一标识符。
     * @param destBucket   目标桶的名称，复制目标的桶。
     * @param destKey      目标对象的键，复制后文件的唯一标识符。
     */
    public void copyObject(String sourceBucket, String sourceKey, String destBucket, String destKey) {
        CopyObjectRequest request = CopyObjectRequest.builder()
                .sourceBucket(sourceBucket) // 指定源桶
                .sourceKey(sourceKey) // 指定源键
                .destinationBucket(destBucket) // 指定目标桶
                .destinationKey(destKey) // 指定目标键
                .build();
        s3Client.copyObject(request);
    }

    /**
     * 设置对象的元数据并上传。
     * 支持为对象添加自定义元数据，例如内容类型或描述信息。
     *
     * @param bucketName 桶的名称，目标存储桶的唯一标识符。
     * @param key        对象的键，文件的唯一标识符。
     * @param file       要上传的文件，类型为 MultipartFile。
     * @param metadata   元数据键值对，例如 {"Content-Type": "image/jpeg", "Description": "Test image"}。
     * @throws IOException 如果上传过程中发生 I/O 错误。
     */
    public void uploadFileWithMetadata(String bucketName, String key, MultipartFile file, Map<String, String> metadata) throws IOException {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName) // 指定目标桶
                .key(key) // 指定文件键
                .metadata(metadata) // 设置自定义元数据
                .build();
        s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
    }

    /**
     * 发起分块上传。
     * 返回 uploadId，用于后续分块上传操作，适合大文件。
     *
     * @param bucketName 桶的名称，目标存储桶的唯一标识符。
     * @param key        对象的键，上传后文件的唯一标识符。
     * @return 返回上传 ID 字符串，用于跟踪分块上传。
     */
    public String initiateMultipartUpload(String bucketName, String key) {
        CreateMultipartUploadRequest request = CreateMultipartUploadRequest.builder()
                .bucket(bucketName) // 指定目标桶
                .key(key) // 指定文件键
                .build();
        CreateMultipartUploadResponse response = s3Client.createMultipartUpload(request);
        return response.uploadId();
    }

    /**
     * 生成分块上传的预签名 URL。
     * 客户端可以使用此 URL 直接上传分块。
     *
     * @param bucketName 桶的名称，目标存储桶的唯一标识符。
     * @param key        对象的键，上传后文件的唯一标识符。
     * @param uploadId   上传 ID，由 initiateMultipartUpload 返回。
     * @param partNumber 分块编号，从 1 开始递增，表示当前分块的序号。
     * @param duration   预签名 URL 的有效时长，例如 Duration.ofMinutes(10)。
     * @return 返回预签名 URL 字符串。
     */
    public String generatePresignedUrlForPartUpload(String bucketName, String key, String uploadId, int partNumber, Duration duration) {
        UploadPartRequest request = UploadPartRequest.builder()
                .bucket(bucketName) // 指定目标桶
                .key(key) // 指定文件键
                .uploadId(uploadId) // 指定上传 ID
                .partNumber(partNumber) // 指定分块编号
                .build();
        UploadPartPresignRequest presignRequest = UploadPartPresignRequest.builder()
                .signatureDuration(duration) // 设置 URL 有效时长
                .uploadPartRequest(request)
                .build();
        return s3Presigner.presignUploadPart(presignRequest).url().toString();
    }

    /**
     * 完成分块上传。
     * 将所有分块组合成完整文件，结束分块上传过程。
     *
     * @param bucketName 桶的名称，目标存储桶的唯一标识符。
     * @param key        对象的键，上传后文件的唯一标识符。
     * @param uploadId   上传 ID，由 initiateMultipartUpload 返回。
     * @param parts      分块列表，每个分块包含编号和 ETag，由客户端上传分块后返回。
     */
    public void completeMultipartUpload(String bucketName, String key, String uploadId, List<CompletedPart> parts) {
        CompleteMultipartUploadRequest request = CompleteMultipartUploadRequest.builder()
                .bucket(bucketName) // 指定目标桶
                .key(key) // 指定文件键
                .uploadId(uploadId) // 指定上传 ID
                .multipartUpload(CompletedMultipartUpload.builder().parts(parts).build()) // 设置分块列表
                .build();
        s3Client.completeMultipartUpload(request);
    }
}
