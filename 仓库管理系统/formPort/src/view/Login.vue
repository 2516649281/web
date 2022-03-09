<template>
  <el-container id="main">
    <el-header><h2>欢迎登录</h2></el-header>
    <el-main>
      <el-form :model="loginForm">
        <el-form-item label="账号">
          <el-input v-model="loginForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="loginForm.userPassword" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="success"
            round
            class="login"
            @click="login(loginForm)"
            >登录</el-button
          >
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
import { login } from "../api/user";
export default {
  data() {
    return {
      loginForm: {
        userName: "",
        userPassworld: "",
      },
    };
  },
  methods: {
    login(Source) {
      console.log(Source);
      login(Source).then((res) => {
        if (res.data.status & (res.data.data.length > 0)) {
          this.$message({
            message: "登录成功",
            type: "success",
          });
          this.$router.push({ name: "BaseSelect" });
        } else {
          this.$message({
            message: "登录失败",
            type: "error",
          });
        }
      });
    },
  },
};
</script>

<style>
#main {
  width: 50%;
  height: 100%;
  margin: 0 auto;
  text-align: center;
}
.login {
  width: 30%;
  /* height: 30%; */
}
</style>
