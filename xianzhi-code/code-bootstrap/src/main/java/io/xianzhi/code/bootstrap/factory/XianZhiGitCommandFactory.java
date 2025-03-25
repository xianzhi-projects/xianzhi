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

package io.xianzhi.code.bootstrap.factory;

import io.xianzhi.code.bootstrap.command.GitUploadPackCommand;
import lombok.Getter;
import org.apache.sshd.common.util.threads.CloseableExecutorService;
import org.apache.sshd.common.util.threads.ThreadUtils;
import org.apache.sshd.git.AbstractGitCommand;
import org.apache.sshd.git.AbstractGitCommandFactory;
import org.apache.sshd.git.GitLocationResolver;

/**
 * 命令工厂
 *
 * @author Max
 * @since 1.0.0
 */

public class XianZhiGitCommandFactory extends AbstractGitCommandFactory {

    private static final String GIT_FACTORY_NAME = "git-pgm";
    private static final String GIT_COMMAND_PREFIX = "git ";
    @Getter
    private final GitLocationResolver gitLocationResolver;
    CloseableExecutorService executorService = ThreadUtils.newFixedThreadPool("git-executor",2);


    public XianZhiGitCommandFactory(GitLocationResolver gitLocationResolver) {
        super(GIT_FACTORY_NAME, GIT_COMMAND_PREFIX);
        if (gitLocationResolver == null) {
            throw new IllegalArgumentException("GitLocationResolver cannot be null");
        }
        this.gitLocationResolver = gitLocationResolver;
        withGitLocationResolver(gitLocationResolver);
    }

    @Override
    protected AbstractGitCommand createGitCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            throw new IllegalArgumentException("Git command cannot be null or empty");
        }

        String trimmedCommand = command.trim().startsWith(GIT_COMMAND_PREFIX)
                ? command.substring(GIT_COMMAND_PREFIX.length()).trim()
                : command.trim();
        String[] parts = trimmedCommand.split("\\s+", 2);
        String commandType = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        if ("git-upload-pack".equals(commandType)) {
            return new GitUploadPackCommand(gitLocationResolver, arguments, executorService);
        }

        throw new IllegalArgumentException("Unsupported Git command: " + commandType);
    }
}
