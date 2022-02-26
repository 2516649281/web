//登录
<template>
  <el-container class="main">
    <el-header>
      <h1 style="text-align: center">欢迎使用教务管理系统</h1>
    </el-header>
    <el-main
      ><el-form :model="loginFrom">
        <el-form-item label="用户名" class="use-name">
          <el-input v-model="loginFrom.userName"></el-input>
        </el-form-item>
        <el-form-item label="密  码" class="passwd">
          <el-input v-model="loginFrom.userPassword" show-password></el-input>
        </el-form-item>
        <Vcode :show="isShow" @success="onSuccess()" @close="onClose()" />
        <el-form-item class="btns">
          <el-button class="btn" @click="register()">注册</el-button>
          <el-button type="primary" class="btn" @click="login(loginFrom)"
            >登录</el-button
          >
        </el-form-item></el-form
      ></el-main
    >
    <el-footer></el-footer>
  </el-container>
</template>

<script>
import Vcode from "vue-puzzle-vcode";
import { login, adduser } from "../../api/login";
export default {
  data() {
    return {
      //人机验证
      isShow: false,
      //登录表单
      loginFrom: {
        //用户名
        userName: "",
        //用户密码
        userPassword: "",
        //用户身份
        userIdentity: 2,
        //用户权限
        userAdmin: 2,
        //身份id
        userIdentityId: "",
        //用户真实姓名
        userRealName: "",
        //用户班级
        userClassName: "",
      },
    };
  },
  components: {
    Vcode,
  },
  methods: {
    //登录校验
    login(user) {
      if ((user.userName != "") & (user.userPassword != "")) {
        this.loginFrom = user;
        this.isShow = true;
      } else {
        this.$message({
          message: "账号和密码不能为空！",
          type: "warning",
        });
      }
    },
    //注册
    register() {
      this.$router.push({ name: "userIdentity" });
    },
    //滑块验证成功
    onSuccess() {
      this.isShow = false;
      login(this.loginFrom).then((req) => {
        this.loginFrom = req.data.data;
        if (req.data.data != null) {
          this.$message({
            message: `欢迎你${req.data.data.userRealName},你的用户名为${req.data.data.userName},你的身份是${req.data.data.identity},你的权限为${req.data.data.admin}`,
            type: "success",
          });
          sessionStorage.setItem("isLogin", this.loginFrom); //添加至会话层
          if (this.loginFrom.userAdmin === 0) {
            //非管理员
            if (this.loginFrom.userIdentity === 0) {
              //学生
              this.$router.push({
                name: "student",
                params: { login: this.loginFrom },
              });
            } else {
              //教师
              this.$router.push({
                name: "teacher",
                params: { login: this.loginFrom },
              });
            }
          } else {
            //管理员
            this.$router.push({
              name: "admin",
              params: { login: this.loginFrom },
            });
          }
        } else {
          this.$message({
            message: `用户名或密码错误`,
            type: "error",
          });
        }
      });
    },
    // 关闭滑块验证后提示用户取消验证
    onClose() {
      this.isShow = false;
      this.$message({
        message: "请验证！",
        type: "error",
      });
    },
  },
};
</script>

<style>
body {
  /* background-image: linear-gradient(to left, #654ea3, #eaafc8); */
  text-align: center;
  /* color: #fff; */
}
.main {
  width: 80%;
  margin: 0 auto;
  /* border: 1px black solid; */
}
.use-name,
.passwd {
  margin: 0 auto;
  text-align: center;
  width: 40%;
}
.btns {
  width: 40%;
  margin: 5% auto;
}
</style>