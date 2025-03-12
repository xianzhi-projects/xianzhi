<template>
  <div class="form_container">
    <div class="logo_container"></div>
    <div class="title_container">
      <p class="title">Login to your Account</p>
      <span class="subtitle">Get started with our app, just create an account and enjoy the experience.</span>
    </div>
    <a-form :model="form" @finish="onFinish">
      <div class="input_container">
        <label class="input_label" for="email_field">Email</label>
        <a-form-item :rules="[{ required: true, message: 'Please input your email!' }]" name="email">
          <a-input id="email_field" v-model:value="form.email" placeholder="name@mail.com">
            <template #prefix>
              <MailOutlined class="icon" />
            </template>
          </a-input>
        </a-form-item>
      </div>
      <div class="input_container">
        <label class="input_label" for="password_field">Password</label>
        <a-form-item :rules="[{ required: true, message: 'Please input your password!' }]" name="password">
          <a-input-password id="password_field" v-model:value="form.password" placeholder="Password">
            <template #prefix>
              <LockOutlined class="icon" />
            </template>
          </a-input-password>
        </a-form-item>
      </div>
      <a-form-item>
        <a-button class="sign-in_btn" html-type="submit" type="primary">Sign In</a-button>
      </a-form-item>
    </a-form>
    <div class="separator">
      <a-divider>Or</a-divider>
    </div>
    <a-button class="sign-in_ggl">
      <template #icon>
        <svg height="18" viewBox="0 0 32 32" width="18" xmlns="http://www.w3.org/2000/svg">
          <g transform="matrix(.727273 0 0 .727273 -.954545 -1.45455)">
            <path d="M0 37V11l17 13z" fill="#fbbc05"></path>
            <path d="M0 11l17 13 7-6.1L48 14V0H0z" fill="#ea4335"></path>
            <path d="M0 37l30-23 7.9 1L48 0v48H0z" fill="#34a853"></path>
            <path d="M48 48L17 24l-4-3 35-10z" fill="#4285f4"></path>
          </g>
        </svg>
      </template>
      Sign In with Google
    </a-button>
    <a-button class="sign-in_apl">
      <template #icon>
        <svg height="20px" preserveAspectRatio="xMidYMid" viewBox="0 0 256 315" width="16px" xmlns="http://www.w3.org/2000/svg">
          <path d="M213.803394,167.030943 C214.2452,214.609646 255.542482,230.442639 256,230.644727 C255.650812,231.761357 249.401383,253.208293 234.24263,275.361446 C221.138555,294.513969 207.538253,313.596333 186.113759,313.991545 C165.062051,314.379442 158.292752,301.507828 134.22469,301.507828 C110.163898,301.507828 102.642899,313.596301 82.7151126,314.379442 C62.0350407,315.16201 46.2873831,293.668525 33.0744079,274.586162 C6.07529317,235.552544 -14.5576169,164.286328 13.147166,116.18047 C26.9103111,92.2909053 51.5060917,77.1630356 78.2026125,76.7751096 C98.5099145,76.3877456 117.677594,90.4371851 130.091705,90.4371851 C142.497945,90.4371851 165.790755,73.5415029 190.277627,76.0228474 C200.528668,76.4495055 229.303509,80.1636878 247.780625,107.209389 C246.291825,108.132333 213.44635,127.253405 213.803394,167.030988 M174.239142,50.1987033 C185.218331,36.9088319 192.607958,18.4081019 190.591988,0 C174.766312,0.636050225 155.629514,10.5457909 144.278109,23.8283506 C134.10507,35.5906758 125.195775,54.4170275 127.599657,72.4607932 C145.239231,73.8255433 163.259413,63.4970262 174.239142,50.1987249" fill="#ffffff"></path>
        </svg>
      </template>
      Sign In with Apple
    </a-button>
    <p class="note">Terms of use & Conditions</p>
  </div>
</template>
<script lang="ts">
import {defineComponent, reactive} from 'vue';
import {Button, Divider, Form, Input} from 'ant-design-vue';
import {LockOutlined, MailOutlined} from '@ant-design/icons-vue';

export default defineComponent({
  name: 'LoginForm',
  components: {
    AForm: Form,
    AInput: Input,
    AInputPassword: Input.Password,
    AButton: Button,
    ADivider: Divider,
    MailOutlined,
    LockOutlined,
  },
  setup() {
    // Define reactive form data
    const form = reactive({
      email: '',
      password: '',
    });

    // Handle form submission
    const onFinish = (values: any) => {
      console.log('Form submitted successfully:', values);
      // Add your login logic here (e.g., API call)
    };

    return {
      form,
      onFinish,
    };
  },
});
</script>

<style scoped>
.form_container {
  width: fit-content;
  height: fit-content;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 15px;
  padding: 50px 40px 20px 40px;
  background-color: #ffffff;
  box-shadow: 0px 106px 42px rgba(0, 0, 0, 0.01),
  0px 59px 36px rgba(0, 0, 0, 0.05), 0px 26px 26px rgba(0, 0, 0, 0.09),
  0px 7px 15px rgba(0, 0, 0, 0.1), 0px 0px 0px rgba(0, 0, 0, 0.1);
  border-radius: 11px;
  font-family: "Inter", sans-serif;
}

.logo_container {
  box-sizing: border-box;
  width: 80px;
  height: 80px;
  background: linear-gradient(180deg, rgba(248, 248, 248, 0) 50%, #F8F8F888 100%);
  border: 1px solid #F7F7F8;
  filter: drop-shadow(0px 0.5px 0.5px #EFEFEF) drop-shadow(0px 1px 0.5px rgba(239, 239, 239, 0.5));
  border-radius: 11px;
}

.title_container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 10px;
}

.title {
  margin: 0;
  font-size: 1.25rem;
  font-weight: 700;
  color: #212121;
}

.subtitle {
  font-size: 0.725rem;
  max-width: 80%;
  text-align: center;
  line-height: 1.1rem;
  color: #8B8E98;
}

.input_container {
  width: 100%;
  height: fit-content;
  position: relative;
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.input_label {
  font-size: 0.75rem;
  color: #8B8E98;
  font-weight: 600;
}

.icon {
  color: #8B8E98;
}

.sign-in_btn {
  width: 100%;
}

.sign-in_ggl,
.sign-in_apl {
  width: 100%;
  margin-top: 10px;
}

.separator {
  width: 100%;
}

.note {
  font-size: 0.75rem;
  color: #8B8E98;
  text-decoration: underline;
}
</style>
