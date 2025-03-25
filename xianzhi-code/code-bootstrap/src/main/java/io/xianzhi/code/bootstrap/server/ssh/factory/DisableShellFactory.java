package io.xianzhi.code.bootstrap.server.ssh.factory;

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
