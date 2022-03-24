<template>
  <el-container id="main">
    <el-main><router-view /></el-main>
    <el-footer
      ><el-menu class="el-menu-demo" mode="horizontal">
        <el-menu-item index="1" @click="getOrder">订单</el-menu-item>
        <el-menu-item index="2" @click="getUser">我的</el-menu-item>
        <el-menu-item index="3" @click="exitLogin">退出登录</el-menu-item>
      </el-menu></el-footer
    >
  </el-container>
</template>

<script>
export default {
  data() {
    return {};
  },
  created() {},
  methods: {
    //订单
    getOrder() {
      this.$router.push({
        name: "order",
      });
    },
    //我的
    getUser() {
      this.$router.push({
        name: "user",
      });
    },
    exitLogin() {
      this.$confirm("你真的要退出吗?退出后需要重新登录", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        sessionStorage.clear();
        this.$router.push({ name: "login" });
      });
    },
  },
};
</script>

<style>
#main {
  width: 100%;
  height: 100%;
}
.el-menu-demo {
  width: 100%;
  height: 100%;
  margin: 15% 15%;
}
</style>