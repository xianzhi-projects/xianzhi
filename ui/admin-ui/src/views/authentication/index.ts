import type {Rule} from 'ant-design-vue/es/form'

const loginRules: Record<string, Rule[]> = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'change' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'change' },

  ],
  captcha: [
    { required: true, message: '请输入验证码', trigger: 'change' },
  ],
}
export default loginRules
