package io.xianzhi.code.bootstrap.server.http;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.ServiceMayNotContinueException;
import org.eclipse.jgit.transport.resolver.RepositoryResolver;
import org.eclipse.jgit.transport.resolver.ServiceNotAuthorizedException;
import org.eclipse.jgit.transport.resolver.ServiceNotEnabledException;
import org.springframework.stereotype.Component;

/**
 * 仓库加载
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
public class HttpRepositoryResolver implements RepositoryResolver<HttpServletRequest> {
    /**
     * Locate and open a reference to a {@link Repository}.
     * <p>
     * The caller is responsible for closing the returned Repository.
     *
     * @param req  the current request, may be used to inspect session state
     *             including cookies or user authentication.
     * @param name name of the repository, as parsed out of the URL.
     * @return the opened repository instance, never null.
     * @throws RepositoryNotFoundException    the repository does not exist or the name is incorrectly
     *                                        formatted as a repository name.
     * @throws ServiceNotAuthorizedException  the repository may exist, but HTTP access is not allowed
     *                                        without authentication, i.e. this corresponds to an HTTP 401
     *                                        Unauthorized.
     * @throws ServiceNotEnabledException     the repository may exist, but HTTP access is not allowed on the
     *                                        target repository, for the current user.
     * @throws ServiceMayNotContinueException the repository may exist, but HTTP access is not allowed for
     *                                        the current request. The exception message contains a detailed
     *                                        message that should be shown to the user.
     */
    @Override
    public Repository open(HttpServletRequest req, String name) throws RepositoryNotFoundException, ServiceNotAuthorizedException, ServiceNotEnabledException, ServiceMayNotContinueException {
        return null;
    }
}
