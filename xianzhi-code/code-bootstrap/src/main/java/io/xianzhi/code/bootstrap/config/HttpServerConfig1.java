package io.xianzhi.code.bootstrap.config;

//@Slf4j
//@RequiredArgsConstructor
//@Configuration
public class HttpServerConfig1 {

//    private final CodeServerProperties codeServerProperties;
//
//    private final UploadPackFactory<HttpServletRequest> uploadPackFactory;
//    private final ReceivePackFactory<HttpServletRequest> receivePackFactory;
//
//    @Bean
//    public GitServlet gitServlet() {
//        GitServlet gitServlet = new GitServlet();
//        gitServlet.setRepositoryResolver(new DynamicRepositoryResolver(codeServerProperties.getRepositoryDir()));
//        gitServlet.setUploadPackFactory(uploadPackFactory);
//        gitServlet.setReceivePackFactory(receivePackFactory);
//        return gitServlet;
//    }
//
//    @Bean
//    public ServletRegistrationBean<HttpServlet> gitServletRegistration(GitServlet gitServlet) {
//        ServletRegistrationBean<HttpServlet> registrationBean =
//                new ServletRegistrationBean<>(gitServlet, "/gitServlet/*");
//        registrationBean.setName("XianZhiGitServlet");
//        registrationBean.setLoadOnStartup(1);
//        log.info("Registered GitServlet with name 'XianZhiGitServlet'");
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean<GitFilter> gitFilter(GitServlet gitServlet) {
//        FilterRegistrationBean<GitFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new GitFilter(gitServlet));
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setName("GitFilter");
//        registrationBean.setOrder(1);
//        log.info("Registered GitFilter for Git requests");
//        return registrationBean;
//    }
//
//    public static class GitFilter implements Filter {
//        private final GitServlet gitServlet;
//
//        public GitFilter(GitServlet gitServlet) {
//            this.gitServlet = gitServlet;
//        }
//
//        @Override
//        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//                throws IOException, ServletException {
//            HttpServletRequest req = (HttpServletRequest) request;
//            HttpServletResponse resp = (HttpServletResponse) response;
//            String pathInfo = req.getPathInfo() != null ? req.getPathInfo() : req.getServletPath();
//            log.info("Request path: {}, Servlet path: {}, Path info: {}",
//                    req.getRequestURI(), req.getServletPath(), req.getPathInfo());
//
//            if (pathInfo != null && pathInfo.endsWith(".git")) {
//                log.info("Forwarding Git request: {}", pathInfo);
//                gitServlet.service(req, resp);
//            } else {
//                log.info("Not a Git request, passing to next filter: {}", pathInfo);
//                chain.doFilter(request, response);
//            }
//        }
//    }
//
//    public static class DynamicRepositoryResolver implements RepositoryResolver<HttpServletRequest> {
//        private final String repoRootPath;
//
//        public DynamicRepositoryResolver(String repoRootPath) {
//            this.repoRootPath = repoRootPath;
//        }
//
//        @Override
//        public Repository open(HttpServletRequest req, String name)  {
//            String pathInfo = req.getPathInfo() != null ? req.getPathInfo() : req.getServletPath();
//            log.info("Entering DynamicRepositoryResolver, pathInfo: {}, name: {}", pathInfo, name);
//
//            if (pathInfo == null || !pathInfo.endsWith(".git")) {
//                log.warn("Invalid repository path: {}", pathInfo);
//                try {
//                    throw new IOException("Invalid repository path: " + pathInfo);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//            String repoPath = pathInfo.startsWith("/") ? pathInfo.substring(1) : pathInfo;
//            File repoDir = new File(repoRootPath, repoPath);
//            log.info("Resolved repository directory: {}", repoDir.getAbsolutePath());
//
//            if (!repoDir.exists()) {
//                log.error("Repository not found: {}", repoDir.getAbsolutePath());
//                try {
//                    throw new IOException("Repository not found: " + repoDir.getAbsolutePath());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//            FileRepositoryBuilder builder = new FileRepositoryBuilder();
//            try {
//                return builder.setGitDir(repoDir).readEnvironment().findGitDir().build();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}