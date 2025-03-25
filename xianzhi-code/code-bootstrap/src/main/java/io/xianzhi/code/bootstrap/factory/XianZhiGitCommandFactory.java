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

    public static final String GIT_FACTORY_NAME = "git-pgm";
    public static final String GIT_COMMAND_PREFIX = "git ";


    public XianZhiGitCommandFactory(GitLocationResolver gitLocationResolver) {
        super(GIT_FACTORY_NAME, GIT_COMMAND_PREFIX);
        withGitLocationResolver(gitLocationResolver);
    }

    @Override
    protected AbstractGitCommand createGitCommand(String s) {
        return null;
    }
}
