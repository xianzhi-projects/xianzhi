package io.xianzhi.code.bootstrap.server.http.factory;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.UploadPack;
import org.eclipse.jgit.transport.resolver.ServiceNotAuthorizedException;
import org.eclipse.jgit.transport.resolver.ServiceNotEnabledException;
import org.eclipse.jgit.transport.resolver.UploadPackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class XianZhiUploadPackFactory implements UploadPackFactory<HttpServletRequest> {
    /**
     * Create and configure a new UploadPack instance for a repository.
     *
     * @param req current request, in case information from the request may help
     *            configure the UploadPack instance.
     * @param db  the repository the upload would read from.
     * @return the newly configured UploadPack instance, must not be null.
     * @throws ServiceNotEnabledException    this factory refuses to create the instance because it is not
     *                                       allowed on the target repository, by any user.
     * @throws ServiceNotAuthorizedException this factory refuses to create the instance for this HTTP
     *                                       request and repository, such as due to a permission error.
     */
    @Override
    public UploadPack create(HttpServletRequest req, Repository db) throws ServiceNotEnabledException, ServiceNotAuthorizedException {
        return null;
    }
}
