import {defineStore} from 'pinia';
import axios from 'axios';
import {ref} from 'vue';

export const useAuthStore = defineStore('auth', () => {
  const token = ref<string | null>(null);
  const error = ref<string | null>(null);
  const loading = ref<boolean>(false);
  const theme = ref<'light' | 'dark'>('light');
  const language = ref<'zh' | 'en'>('zh');

  async function login(data: any) {
    loading.value = true;
    error.value = null;
    try {
      const response = await axios.post('/api/login', data); // 请替换为真实接口
      token.value = response.data.token;
    } catch (err) {
      error.value = '登录失败，请检查输入信息';
    } finally {
      loading.value = false;
    }
  }

  function toggleTheme() {
    theme.value = theme.value === 'light' ? 'dark' : 'light';
  }

  function setLanguage(lang: 'zh' | 'en') {
    language.value = lang;
  }

  return { token, error, loading, theme, language, login, toggleTheme, setLanguage };
});
