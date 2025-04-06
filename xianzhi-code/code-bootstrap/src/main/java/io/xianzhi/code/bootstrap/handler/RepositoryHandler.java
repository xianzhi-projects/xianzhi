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

package io.xianzhi.code.bootstrap.handler;

import io.xianzhi.code.bootstrap.properties.CodeServerProperties;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 仓库信息处理
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RepositoryHandler {

    /**
     * 代码服务配置
     */
    private final CodeServerProperties codeServerProperties;

    /**
     * 创建项目仓库
     *
     * @param projectPath   项目路径，不能为空
     * @param groupId       项目组ID，不能为空
     * @param initBranch    是否初始化分支
     * @param branchList    分支列表，当initBranch为true时生效
     * @param defaultBranch 默认分支名称，当initBranch为true时生效
     * @param addReadmeFile 是否添加README文件
     * @throws BusinessException 如果输入参数无效
     * @throws RuntimeException  如果IO操作失败
     */
    public void createRepository(String projectPath,
                                 String groupId,
                                 boolean initBranch,
                                 List<String> branchList,
                                 String defaultBranch,
                                 boolean addReadmeFile) {
        // 验证输入参数
        if (!StringUtils.hasText(projectPath)) {
            throw new BusinessException(CommonCode.ERROR.getCode(), "项目路径不能为空");
        }
        if (!StringUtils.hasText(groupId)) {
            throw new BusinessException(CommonCode.ERROR.getCode(), "项目组ID不能为空");
        }

        // 构造仓库完整路径
        String repositoryPath = codeServerProperties.getRepositoryDir() + "/" + groupId + "/" + projectPath;
        File repoDir = new File(repositoryPath);

        try {
            // 创建仓库目录
            if (!repoDir.exists() && !repoDir.mkdirs()) {
                throw new RuntimeException("无法创建仓库目录: " + repositoryPath);
            }

            // 使用JGit初始化Git仓库
            try (Git git = Git.init().setDirectory(repoDir).setBare(true).call()) {
                System.out.println("仓库创建成功: " + git.getRepository().getDirectory());

                // 如果需要初始化分支
                if (initBranch) {
                    // 设置默认分支名称，如果未提供则使用"main"
                    String defaultBranchName = StringUtils.hasText(defaultBranch) ? defaultBranch : "main";

                    // 创建工作目录用于初始化提交
                    File workDir = new File(repositoryPath + "_work");
                    try (Git workGit = Git.cloneRepository()
                            .setURI(git.getRepository().getDirectory().toURI().toString())
                            .setDirectory(workDir)
                            .call()) {

                        // 如果需要添加README文件
                        if (addReadmeFile) {
                            File readmeFile = new File(workDir, "README.md");
                            try (FileWriter writer = new FileWriter(readmeFile)) {
                                writer.write("# " + projectPath + "\n\n项目初始化README文件");
                            }
                            // 添加并提交README文件
                            workGit.add().addFilepattern("README.md").call();
                            workGit.commit().setMessage("Initial commit with README").call();
                            // 推送到远程
                            workGit.push().call();
                        }

                        // 创建其他分支
                        if (branchList != null && !branchList.isEmpty()) {
                            for (String branch : branchList) {
                                if (!branch.equals(defaultBranchName)) {
                                    workGit.checkout()
                                            .setCreateBranch(true)
                                            .setName(branch)
                                            .call();
                                    workGit.push().call();
                                }
                            }
                            // 切换回默认分支
                            workGit.checkout()
                                    .setName(defaultBranchName)
                                    .call();
                        }
                    } finally {
                        // 清理临时工作目录
                        FileUtils.deleteDirectory(workDir);
                    }

                    // 设置默认分支
                    git.getRepository().getConfig()
                            .setString("init", null, "defaultBranch", defaultBranchName);
                    git.getRepository().getConfig().save();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("创建仓库时发生IO错误: " + e.getMessage(), e);
        } catch (GitAPIException e) {
            throw new RuntimeException("Git操作失败: " + e.getMessage(), e);
        }
    }


}
