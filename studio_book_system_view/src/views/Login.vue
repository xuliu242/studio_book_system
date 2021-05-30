<template>
  <div class="login_container">
    <!--    登陆区域-->
    <div class="login_box">
      <!--      头像-->
      <div class="avatar_box">
        <!-- <img src="../assets/img/qimeng.jpg" /> -->
        <img src="../assets/img/login.jpg" />
      </div>
      <!--      表单-->
      <el-form
        :model="loginForm"
        :rules="loginRules"
        ref="loginForm"
        label-width="100px"
        class="login_form"
      >
        <el-form-item label="账号" prop="username">
          <el-input
            v-model="loginForm.username"
            prefix-icon="el-icon-user"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="loginForm.password"
            prefix-icon="el-icon-lock"
            type="password"
          ></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 0px">
          <el-button type="primary" @click="submitForm('loginForm')"
            >登录</el-button
          >
          <el-button @click="resetForm('loginForm')">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { doLogin, getUserInfo } from "@/api/api";
export default {
  data() {
    return {
      loginForm: {
        username: "",
        password: ""
      },
      loginRules: {
        username: [{ required: true, message: "请输入账号", trigger: "blur" }],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 20, message: "长度在 6 到 20 个字符", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    mounted() {
      sessionStorage.removeItem("UserLoginName");
      // LocalStorage.clearAll();
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          doLogin({
            syhUserNumber: this.loginForm.username,
            syhPassword: this.loginForm.password
          }).then(res => {
            if (res.data.code == 200) {
              this.$message({
                message: res.data.message,
                type: "success"
              });
              console.log("UserLoginName", res.data.data.userInfo.syhUserId);
              sessionStorage.setItem(
                "UserLoginName",
                res.data.data.userInfo.syhUserId
              );
              this.$router.push("/main");
              // LocalStorage.set(ACCESS_TOKEN, res.data.data.token);
              // this.getUserInfo();
            } else {
              this.$message.error(res.data.message);
            }
            console.log("res", res);
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    getUserInfo() {
      getUserInfo().then(res => {
        if (res.data.code == 200) {
          this.$store.commit("setUserInfo", res.data.data);
          this.$router.push("/main");
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style lang="less" scoped>
.login_container {
  height: 100%;
  background: #f0f2f5 url(~@/assets/mainBg2.jpg) no-repeat 50%;
}
// 为背景图片加透明
.login_box::after {
  content: "";
  background: #f0f2f5 url(~@/assets/background.svg) no-repeat 50%;
  opacity: 0.7;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  position: absolute;
  z-index: -1;
}
.login_box {
  width: 450px;
  height: 340px;
  background-size: 100%;

  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  .avatar_box {
    width: 130px;
    height: 130px;
    border: 1px solid #eeeeee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    margin: -65px auto;
    background-color: #ffffff;
  }
  img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
  }
  .login_form {
    position: absolute;
    width: 100%;
    box-sizing: border-box;
    padding: 0px 20px;
    bottom: 0px;
  }
}
</style>
