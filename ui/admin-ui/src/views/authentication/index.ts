import type { Rule } from 'ant-design-vue/es/form'

const loginRules: Record<string, Rule[]> = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'change' },
    {
      pattern:
        /^(?:[a-zA-Z]+[a-zA-Z0-9]*|[0-9]{11}|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,})$/,
      message: '请输入正确的用户名。邮箱、工号、手机号码',
      trigger: 'change',
    },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'change' },
    {
      pattern: /^(?=.*[A-Za-z\d])(?=.*[!@#$%^&*(),.?":{}|<>])[A-Za-z\d!@#$%^&*(),.?":{}|<>]{6,20}$/,
      message: '密码必须为6到20位，包含字母或数字以及特殊符号',
      trigger: 'change',
    },
  ],
  captcha: [
    { required: true, message: '请输入验证码', trigger: 'change' },
    {
      pattern: /^[A-Za-z0-9]{6}$/,
      message: '请输入正确的验证码',
      trigger: 'change',
    },
  ],
}
export default loginRules
