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

package io.xianzhi.code.bootstrap.command;

import org.apache.sshd.common.util.threads.CloseableExecutorService;
import org.apache.sshd.git.AbstractGitCommand;
import org.apache.sshd.git.GitLocationResolver;
import org.apache.sshd.server.session.ServerSession;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UploadPack;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

/**
 * @author Max
 * @since 1.0.0
 */
public class GitUploadPackCommand extends AbstractGitCommand {
    private final GitLocationResolver resolver;

    public GitUploadPackCommand(GitLocationResolver resolver, String command, CloseableExecutorService executor) {
        super(resolver, command, executor);
        this.resolver = resolver;
    }

    @Override
    public void run() {
        ServerSession session = getServerSession(); // 从 AbstractGitCommand 获取会话
        if (session == null) {
            handleError("No server session available");
            return;
        }

        try {
            // 拆分命令参数
            String[] args = getCommand().split("\\s+");
            FileSystem fs = getFileSystem(); // 从 AbstractGitCommand 获取文件系统
            if (fs == null) {
                throw new IOException("No file system available");
            }

            // 使用 GitLocationResolver 解析仓库路径
            Path repoPath = resolver.resolveRootDirectory(getCommand(), args, session, fs);
            if (null == repoPath) {
                throw new IOException("没有找到仓库路径");
            }
            File repoDir = repoPath.toFile();

            if (!repoDir.exists() || !repoDir.isDirectory()) {
                throw new IOException("没有找到仓库路径: " + repoDir.getAbsolutePath());
            }

            try (Repository repository = new FileRepositoryBuilder()
                    .setGitDir(repoDir)
                    .setMustExist(true)
                    .build()) {
                UploadPack uploadPack = new UploadPack(repository);
                uploadPack.setBiDirectionalPipe(true);
                uploadPack.upload(getInputStream(), getOutputStream(), getErrorStream());
            }

            onExit(0); // 成功退出
        } catch (IOException e) {
            handleError("无法执行GIT命令 git-upload-pack: " + e.getMessage());
        }
    }

    private void handleError(String message) {
        try {
            getErrorStream().write((message + "\n").getBytes());
            getErrorStream().flush();
        } catch (IOException ignored) {
        }
        onExit(1); // 异常退出
    }
}