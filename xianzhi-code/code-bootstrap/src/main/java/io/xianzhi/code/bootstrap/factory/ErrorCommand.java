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

import lombok.Getter;
import org.apache.sshd.server.Environment;
import org.apache.sshd.server.ExitCallback;
import org.apache.sshd.server.channel.ChannelSession;
import org.apache.sshd.server.command.Command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 错误消息命令
 *
 * @author Max
 * @since 1.0.0
 */
public class ErrorCommand implements Command {


    @Getter
    private final String errorMessage;
    /**
     * 输入流
     */
    private InputStream in;
    /**
     * 输出流
     */
    private OutputStream out;
    /**
     * 错误流
     */
    private OutputStream err;
    /**
     * 退出回调
     */
    private ExitCallback callback;


    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    /**
     * Starts the command execution. All streams must have been set <U>before</U> calling this method. The command
     * should implement {@link Runnable}, and this method should spawn a new thread like:
     *
     * <pre>
     * {@code Thread(this).start(); }
     * </pre>
     *
     * @param channel The {@link ChannelSession} through which the command has been received
     * @param env     The {@link Environment}
     * @throws IOException If failed to start
     */
    @Override
    public void start(ChannelSession channel, Environment env) throws IOException {
        err.write(errorMessage.getBytes());
        err.flush();
        callback.onExit(0);
        in.close();
        out.close();
        err.close();
    }


    /**
     * Set the callback that the shell has to call when it is closed.
     *
     * @param callback The {@link ExitCallback} to call when shell is closed
     */
    @Override
    public void setExitCallback(ExitCallback callback) {
        this.callback = callback;
    }

    /**
     * Set the error stream that can be used by the shell to write its errors.
     *
     * @param err The {@link OutputStream} used by the shell to write its errors
     */
    @Override
    public void setErrorStream(OutputStream err) {
        this.err = err;
    }

    /**
     * Set the input stream that can be used by the shell to read input.
     *
     * @param in The {@link InputStream} used by the shell to read input.
     */
    @Override
    public void setInputStream(InputStream in) {
        this.in = in;
    }

    /**
     * Set the output stream that can be used by the shell to write its output.
     *
     * @param out The {@link OutputStream} used by the shell to write its output
     */
    @Override
    public void setOutputStream(OutputStream out) {
        this.out = out;
    }


    /**
     * This method is called by the SSH server to destroy the command because the client has disconnected somehow.
     *
     * @param channel The {@link ChannelSession} through which the command has been received
     * @throws Exception if failed to destroy
     */
    @Override
    public void destroy(ChannelSession channel) throws Exception {
        channel.close();
    }
}
