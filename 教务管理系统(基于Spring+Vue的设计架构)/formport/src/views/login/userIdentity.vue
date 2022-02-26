//验证身份信息，如果数据库中存在此人，则继续注册，为了安全考虑！
<template>
  <div class="form">
    <h1>我们需要收集您的一点信息，以便于下一步操作</h1>
    <el-form :label-position="labelPosition" :model="formLabelAlign">
      <el-form-item label="请输入你的真实姓名">
        <el-input v-model="formLabelAlign.userRealName"></el-input>
      </el-form-item>
      <el-form-item label="请选择你的身份">
        <div>
          <el-radio v-model="formLabelAlign.userIdentity" label="1" border
            >教师</el-radio
          >
          <el-radio v-model="formLabelAlign.userIdentity" label="0" border
            >学生</el-radio
          >
        </div>
      </el-form-item>
      <div v-if="formLabelAlign.userIdentity == 0">
        <el-form-item label="请输入班级">
          <el-input v-model="formLabelAlign.className"></el-input>
        </el-form-item>
      </div>
      <div v-else>
        <el-form-item label="请输入地址">
          <el-input v-model="formLabelAlign.address"></el-input>
        </el-form-item>
      </div>
      <el-form-item>
        <el-button @click="exit">取消</el-button>
        <el-button type="primary" @click="onSubmit(formLabelAlign)"
          >下一步</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { selectAllStudent, selectAllTeacher } from "../../api/login";
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
  methods: {
    onSubmit(Source) {
      if (Source.userIdentity === "0") {
        //如果是学生
        selectAllStudent(Source.userRealName, Source.className).then((res) => {
          if ((res.data.data != null) & res.data.status) {
            //用户是否存在
            if (res.data.data.userId == null) {
              //判断账号是否存在
              this.$message({
                message: "欢迎注册",
                type: "success",
              });
              this.$router.push({
                name: "register",
                params: { login: this.formLabelAlign },
              });
            } else {
              this.$message({
                message:
                  "您已存在账号，请登录账号！如密码遗失，请联系管理员重置密码！",
                type: "warning",
              });
            }
          } else {
            this.$message({
              message: "对不起，您的信息不属于该系统，请联系管理员添加信息！",
              type: "error",
            });
          }
        });
      } else {
        //如果是教师
        selectAllTeacher(Source.userRealName, Source.address).then((res) => {
          if ((res.data.data != null) & res.data.status) {
            //用户是否存在
            if (res.data.data.userId == null) {
              //判断账号是否存在
              this.$message({
                message: "欢迎注册",
                type: "success",
              });
              //教师
              this.$router.push({
                name: "register",
                params: { login: this.formLabelAlign },
              });
            } else {
              this.$message({
                message:
                  "您已存在账号，请登录账号！如密码遗失，请联系管理员重置密码！",
                type: "warning",
              });
            }
          } else {
            this.$message({
              message: "对不起，您的信息不属于该系统，请联系管理员添加信息！",
              type: "error",
            });
          }
        });
      }
    },
    exit() {
      this.$router.push({ name: "login" });
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