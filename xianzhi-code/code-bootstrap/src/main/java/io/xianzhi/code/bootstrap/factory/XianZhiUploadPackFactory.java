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
