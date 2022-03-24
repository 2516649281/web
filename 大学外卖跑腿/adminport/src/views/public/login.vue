<template>
  <el-container class="container" id="main">
    <el-header>
      <h5>欢迎使用校园跑腿系统(管理端)</h5>
    </el-header>
    <el-main>
      <el-form :model="account">
        <el-form-item
          ><el-input v-model="account.accountName" placeholder="请输入账号">
          </el-input
        ></el-form-item>
        <el-form-item
          ><el-input
            v-model="account.accountPassword"
            show-password
            placeholder="请输入密码"
          >
          </el-input
        ></el-form-item>
        <el-form-item>
          <el-button class="but">注册</el-button>
          <el-button type="primary" @click="submit(account)" class="but"
            >登录</el-button
          ></el-form-item
        >
      </el-form>
    </el-main>
    <el-footer
      ><Vcode :show="isShow" @success="success" @close="close"
    /></el-footer>
  </el-container>
</template>

<script>
import Vcode from "vue-puzzle-vcode";
import { login } from "../../api/account";
import jwtDecode from "jwt-decode";
export default {
  data() {
    return {
      account: {
        accountName: "", //账号
        accountPassword: "", //密码
      },
      isShow: false, // 验证码模态框
    };
  },
  components: {
    Vcode,
  },
  methods: {
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
        this.account = obj;
      }
    },
    login(obj) {
      login(obj).then((req) => {
        if (req.data.msg & (req.data.data.token != null)) {
          var userContent = jwtDecode(req.data.data.token).user.user;
          if (userContent.userDuty === 1) {
            this.$message({
              message: "登录成功",
              type: "success",
              showClose: true,
            });
            sessionStorage.setItem("token", req.data.data.token);
            this.$router.push({
              name: "adminIndex",
              params: userContent,
            });
          } else {
            this.$message({
              message: "请使用管理员账号登录!",
              type: "error",
              showClose: true,
            });
          }
        } else {
          this.$message({
            message: "账号或密码错误!",
            type: "error",
          });
        }
      });
    },
    // 通过验证
    success(msg) {
      this.isShow = false;
      this.login(this.account);
    },
    // 取消验证
    close() {
      this.isShow = false;
    },
  },
};
</script>
<style>
#main {
  width: 50%;
}
h5 {
  text-align: center;
}
.but {
  width: 20%;
  height: 10%;
  margin: 0 18%;
}
</style>
