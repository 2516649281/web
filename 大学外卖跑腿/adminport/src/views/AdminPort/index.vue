<template>
  <div id="app">
    <el-container class="container">
      <el-aside>
        <el-menu default-active="2" class="el-menu-vertical-demo" id="menu">
          <h5>欢迎使用校园跑腿后台系统</h5>
          <el-menu-item index="1" @click="getAccount">
            <i class="el-icon-postcard"></i>
            <span slot="title">账号管理</span>
          </el-menu-item>
          <el-menu-item index="2" @click="getUser">
            <i class="el-icon-user"></i>
            <span slot="title">用户管理</span>
          </el-menu-item>
          <el-menu-item index="3" @click="getOrder">
            <i class="el-icon-s-order"></i>
            <span slot="title">账单管理</span>
          </el-menu-item>
          <el-menu-item index="4" @click="getSetting">
            <i class="el-icon-setting"></i>
            <span slot="title">账号设置</span>
          </el-menu-item>
          <el-menu-item index="5" @click="exitLogin">
            <i class="el-icon-switch-button"></i>
            <span slot="title">退出登录</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container id="main">
        <el-main><router-view /></el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },
  created() {},
  methods: {
    //账户管理
    getAccount() {
      this.$router.push({
        name: "account",
      });
    },
    //订单管理
    getOrder() {
      this.$router.push({
        name: "order",
      });
    },
    //用户管理
    getUser() {
      this.$router.push({
        name: "user",
      });
    },
    //系统设置
    getSetting() {
      this.$router.push({
        name: "adminSetting",
      });
    },
    //退出登录
    exitLogin() {
      this.$confirm("你真的要退出码?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        sessionStorage.clear();
        this.$router.push({
          name: "login",
        });
      });
    },
  },
};
</script>

<style>
#app {
  width: 100%;
  height: 100%;
  /* background: #545c64; */
}
#menu {
  height: 100%;
  width: 100%;
}
#main {
  width: 100%;
  height: 100%;
}
h5 {
  /* color: white; */
  text-align: center;
}
</style>