package io.xianzhi.code.bootstrap.server.ssh.factory;

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
