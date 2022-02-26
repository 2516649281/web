<template>
  <div>
    <el-container>
      <el-header> </el-header>
      <el-main>
        <div v-if="userPasswordOk == true">
          <h1>请验证密码</h1>
          <el-form model="userLoginFrom" class="form">
            <el-form-item label="输入原始密码">
              <el-input
                v-model="userLoginFrom.userLoginUserPassWord"
                show-password
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="settingOne(userLoginFrom)">
                下一步</el-button
              >
            </el-form-item>
          </el-form>
        </div>
        <div v-else>
          <h1>请牢记密码，如有遗失，请联系管理员重置密码</h1>
          <el-form model="settingFrom" class="form">
            <el-form-item label="请输入新账号">
              <el-input v-model="settingFrom.newUserName"></el-input>
            </el-form-item>
            <el-form-item label="请输入新密码">
              <el-input
                v-model="settingFrom.newUserPassword"
                show-password
              ></el-input>
            </el-form-item>
            <el-form-item label="请再次输入新密码">
              <el-input
                v-model="settingFrom.userPasswordAgain"
                show-password
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="exit()">上一步</el-button>
              <el-button type="primary" @click="settingTwo(settingFrom)">
                提交</el-button
              >
            </el-form-item>
          </el-form>
        </div>
      </el-main>
      <el-footer></el-footer>
    </el-container>
  </div>
</template>

<script>
import { updateUser } from "../../api/login";
export default {
  data() {
    return {
      //登录信息
      loginFrom: {},
      //用户设置提交表单数据
      settingFrom: {
        //原始账号
        userUser: "",
        //原始密码
        userPassword: "",
        //新账号
        newUserName: "",
        //新密码
        newUserPassword: "",
        //密码二次输入
        userPasswordAgain: "",
      },
      //用户登录的信息
      userLoginFrom: {
        //用户输入的密码
        userLoginUserPassWord: "",
      },
      userPasswordOk: true,
    };
  },
  created() {
    this.getLoginSource();
  },
  methods: {
    //获取中间页发送的数据
    getLoginSource() {
      this.loginFrom = this.$route.params.login;
    },
    //第一次提交
    settingOne(Source) {
      if (Source.userLoginUserPassWord == this.loginFrom.userPassword) {
        this.settingFrom = this.loginFrom;
        console.log(this.settingFrom);
        this.userPasswordOk = false;
      }
    },
    //第二次提交
    settingTwo(Source) {
      if (Source.newUserPassword === Source.userPasswordAgain) {
        updateUser(Source).then((req) => {
          if ((req.data.data != null) & req.data.status) {
            this.$message({
              message: "修改成功，请重新登录",
              type: "success",
            });
            this.settingFrom = false;
            this.$router.push({ name: "login" });
          } else {
            this.$message({
              message: "修改失败，此用户名可能已经存在",
              type: "error",
            });
          }
        });
      } else {
        this.$message({
          message: "两次输入的密码不一致!",
          type: "warning",
        });
      }
    },
    exit() {
      this.userPasswordOk = true;
    },
  },
};
</script>
<style>
.form {
  width: 50%;
  margin: 0 auto;
  text-align: center;
}
</style>