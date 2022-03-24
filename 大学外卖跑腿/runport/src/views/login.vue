<template>
  <el-container id="main">
    <el-main>
      <h6>欢迎登录校园跑腿系统(跑腿端)</h6>
      <el-form :model="account">
        <el-form-item>
          <el-input
            placeholder="请输入账号"
            v-model="account.accountName"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input
            placeholder="请输入密码"
            v-model="account.accountPassword"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="btn">注册</el-button>
          <el-button type="primary" class="btn" @click="submit(account)"
            >登录</el-button
          >
        </el-form-item>
      </el-form>
    </el-main>
    <el-footer
      ><Vcode :show="isShow" @success="success" @close="close"
    /></el-footer>
  </el-container>
</template>

<script>
import Vcode from "vue-puzzle-vcode";
import { login } from "../api/index";
import jwtDecode from "jwt-decode";
export default {
  data() {
    return {
      //账号
      account: {
        //账号
        accountName: "",
        //密码
        accountPassword: "",
      },
      //验证码
      isShow: false,
    };
  },
  components: {
    Vcode,
  },
  methods: {
    //登录
    submit(obj) {
      if (obj.accountName === "") {
        this.$message({
          message: "账号不能为空!",
          type: "warning",
          showClose: true,
        });
      } else if (obj.accountPassword === "") {
        this.$message({
          message: "密码不能为空!",
          type: "warning",
          showClose: true,
        });
      } else {
        this.isShow = true;
      }
    },
    //登录
    login(obj) {
      login(obj).then((res) => {
        if ((res.data.data.token != null) & res.data.msg) {
          var token = jwtDecode(res.data.data.token);
          sessionStorage.setItem("token", res.data.data.token);
          if (token.user.user.userDuty === 2) {
            this.$message({
              message: "登录成功!",
              type: "success",
              onclose: true,
            });
            this.$router.push({ name: "index" });
          } else {
            this.$message({
              message: "该账号不是跑腿员账户!",
              type: "warning",
              onClose: true,
            });
          }
        } else {
          this.$message({
            message: "登录失败!请检查账号或密码是否正确",
            type: "error",
            onclose: true,
          });
        }
      });
    },
    // 用户通过了验证
    success(msg) {
      this.isShow = false; // 通过验证后，需要手动隐藏模态框
      this.login(this.account);
    },
    // 用户点击遮罩层，应该关闭模态框
    close() {
      this.isShow = false;
    },
  },
};
</script>

<style>
#main {
  width: 100%;
  height: 100%;
  margin: 15% 0;
}
h6 {
  text-align: center;
  margin: 20% 0;
}
.btn {
  width: 20%;
  height: 10%;
  text-align: center;
  margin: 0 15%;
}
</style>