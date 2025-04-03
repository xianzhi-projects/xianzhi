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

package io.xianzhi.code.bootstrap.resolver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.git.GitLocationResolver;
import org.apache.sshd.server.session.ServerSession;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

/**
 * Git仓库加载
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class XianZhiGitLocationResolver implements GitLocationResolver {
    /**
     * @param command The complete received command
     * @param args    The command split into arguments - {@code args[0]} is the &quot;pure&quot; command itself
     *                without any other arguments. <B>Note:</B> changing the content of the arguments array may
     *                affect command execution in undetermined ways, due to invocation code changes without prior
     *                notice, so <U>highly recommended to avoid it</U>.
     * @param session The {@link ServerSession} through which the command was received
     * @param fs      The {@link FileSystem} associated with the server session
     * @return The local GIT repository root path
     * @throws IOException If failed to resolve
     */
    @Override
    public Path resolveRootDirectory(String command, String[] args, ServerSession session, FileSystem fs) throws IOException {
        return null;
    }
}
