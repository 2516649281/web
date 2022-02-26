//注册页面
<template>
  <div class="form">
    <h1>欢迎注册</h1>
    <el-form :label-position="labelPosition" :model="formLabelAlign">
      <el-form-item label="请输入账号">
        <el-input v-model="formLabelAlign.userName"></el-input>
      </el-form-item>
      <el-form-item label="请输入密码">
        <el-input
          v-model="formLabelAlign.userPassword"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item label="请再次输入密码">
        <el-input
          v-model="formLabelAlign.userPasswordAgain"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="exit()">取消</el-button>
        <el-button type="primary" @click="adduser(formLabelAlign)"
          >注册</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { adduser } from "../../api/login";
export default {
  data() {
    return {
      //注册信息
      formLabelAlign: {
        //用户名
        userName: "",
        //用户密码
        userPassword: "",
        //密码验证
        userPasswordAgain: "",
        //用户真实姓名
        userRealName: "",
        //用户真实身份
        userIdentity: "",
        //班级名(学生)
        className: "",
        //地址(教师)
        address: "",
      },
    };
  },
  created() {
    this.getLoginSource();
  },
  methods: {
    //注册
    adduser(Source) {
      // console.log(Source);
      if (Source.userPassword === Source.userPasswordAgain) {
        adduser(Source).then((req) => {
          if ((req.data.data != null) & req.data.status) {
            this.$confirm("注册成功，是否登录?", "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "success",
            })
              .then(() => {
                this.$message({
                  type: "success",
                  message: "请牢记密码，如有遗失，请联系管理员重置密码",
                });
                this.$router.push({ name: "login" });
              })
              .catch(() => {
                this.$message({
                  type: "info",
                  message: "已取消登录",
                });
              });
          } else {
            this.$message({
              type: "error",
              message: "注册失败，请联系管理员处理",
            });
          }
        });
      } else {
        this.$message({
          type: "error",
          message: "两次输入的密码不一致！",
        });
      }
    },
    //退出
    exit() {
      this.$confirm("确定退出注册?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$router.push({ name: "login" });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消注册",
          });
        });
    },
    //获取登录页发送的数据
    getLoginSource() {
      this.formLabelAlign = this.$route.params.login;
    },
  },
};
</script>

<style>
.form {
  width: 60%;
  margin: 0 auto;
  text-align: center;
}
</style>