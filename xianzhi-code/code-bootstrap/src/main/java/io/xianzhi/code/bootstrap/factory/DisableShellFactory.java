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

import org.apache.sshd.server.channel.ChannelSession;
import org.apache.sshd.server.command.Command;
import org.apache.sshd.server.shell.ShellFactory;

import java.io.IOException;

/**
 * 禁用SHELL工厂
 *
 * @author Max
 * @since 1.0.0
 */

public class DisableShellFactory implements ShellFactory {
    /**
     * @param channel The {@link ChannelSession} through which the command has been received
     * @return The {@link Command} representing the shell to be executed
     * @throws IOException If failed to create the shell
     */
    @Override
    public Command createShell(ChannelSession channel) {
        return new ErrorCommand("This server doesn't provide shell access.");
    }


}
