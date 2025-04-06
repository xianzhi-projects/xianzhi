<!-- src/components/NewRepository.vue -->
<template>
  <el-dialog v-model="projectEditFlag" :close-on-click-modal="false" title="添加用户" width="1000">
    <div class="new-repository">
      <div class="sidebar">
        <p>新建代码库后 可享受以下服务</p>
      </div>
      <div class="main-content">
        <div class="header">
          <span class="tab active">仓库新建</span>
        </div>
        <div class="tips">Tips: 建议使用项目的缩写或多个相关代码库</div>
        <el-form label-position="left" label-width="120px">
          <el-form-item label="代码库名称" required>
            <el-input v-model="repoName" placeholder="请输入代码库名称" />
            <div class="char-counter">{{ repoName.length }}/100</div>
          </el-form-item>
          <el-form-item label="代码库路径" required>
            <div style="display: flex;">
              <el-select v-model="group" placeholder="请选择分组" style="width: 150px; margin-right: 10px;">
                <el-option v-for="item in groupOptions" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
              <el-input v-model="repoSlug" placeholder="请输入代码库路径" style="flex: 1;" />
            </div>
          </el-form-item>
          <el-form-item label="访问级别">
            <el-select v-model="visibility">
              <el-option v-for="item in visibilityOptions" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
          </el-form-item>
          <el-form-item label="代码库描述">
            <el-input v-model="description" placeholder="请输入描述" />
          </el-form-item>
          <el-form-item>
            <el-checkbox v-model="encryptRepo">启用仓库加密</el-checkbox>
          </el-form-item>
          <el-form-item>
            <el-checkbox v-model="createReadme">创建 README.md</el-checkbox>
            <el-select v-if="createReadme" v-model="readmeTemplate" placeholder="请选择模板" style="margin-left: 10px;">
              <el-option v-for="item in readmeTemplateOptions" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-checkbox v-model="createGitignore">创建 .gitignore</el-checkbox>
            <el-select v-if="createGitignore" v-model="gitignoreTemplate" placeholder="请选择模板" style="margin-left: 10px;">
              <el-option v-for="item in gitignoreTemplateOptions" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-checkbox v-model="createMergeTemplate">创建合并请求模板</el-checkbox>
            <el-select v-if="createMergeTemplate" v-model="mergeTemplate" placeholder="请选择模板" style="margin-left: 10px;">
              <el-option v-for="item in mergeTemplateOptions" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleConfirm">确定</el-button>
            <el-button @click="handleCancel">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>

  </el-dialog>
</template>

<script lang="ts" setup>
import {ref} from 'vue';
import {ElButton, ElCheckbox, ElForm, ElFormItem, ElInput, ElOption, ElSelect} from 'element-plus';
import {projectEditFlag} from "@/views/project/index.ts";

// Form data
const repoName = ref('');
const group = ref('不选择分组');
const repoSlug = ref('');
const visibility = ref('私有');
const description = ref('');
const encryptRepo = ref(false);
const createReadme = ref(false);
const readmeTemplate = ref('内置新手引导');
const createGitignore = ref(false);
const gitignoreTemplate = ref('请选择模板');
const createMergeTemplate = ref(false);
const mergeTemplate = ref('请选择模板');

// Options for dropdowns
const groupOptions = [
  { label: '不选择分组', value: '不选择分组' },
];
const visibilityOptions = [
  { label: '私有', value: '私有' },
  { label: '公开', value: '公开' },
];
const readmeTemplateOptions = [
  { label: '内置新手引导', value: '内置新手引导' },
];
const gitignoreTemplateOptions = [
  { label: '请选择模板', value: '请选择模板' },
];
const mergeTemplateOptions = [
  { label: '请选择模板', value: '请选择模板' },
];

// Methods
const handleConfirm = () => {
  const formData = {
    repoName: repoName.value,
    group: group.value,
    repoSlug: repoSlug.value,
    visibility: visibility.value,
    description: description.value,
    encryptRepo: encryptRepo.value,
    createReadme: createReadme.value,
    readmeTemplate: readmeTemplate.value,
    createGitignore: createGitignore.value,
    gitignoreTemplate: gitignoreTemplate.value,
    createMergeTemplate: createMergeTemplate.value,
    mergeTemplate: mergeTemplate.value,
  };
  console.log('Form Data:', formData);
};

const handleCancel = () => {
  console.log('Cancel');
};
</script>

<style scoped>
.new-repository {
  display: flex;
}

.sidebar {
  width: 30%;
  background-color: #e6f7ff;
  padding: 20px;
  box-sizing: border-box;
}

.main-content {
  width: 70%;
  background-color: white;
  padding: 20px;
  box-sizing: border-box;
}

.header {
  margin-bottom: 20px;
}

.header .tab {
  padding: 10px 20px;
  cursor: pointer;
}

.header .tab.active {
  color: blue;
  border-bottom: 2px solid blue;
}

.tips {
  margin-bottom: 20px;
  color: #666;
}

.char-counter {
  font-size: 12px;
  color: #999;
  margin-top: 5px;
  text-align: right;
}
</style>
