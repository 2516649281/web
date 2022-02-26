<template>
  <el-container>
    <el-aside
      ><h1>欢迎你，{{ loginFrom.userName }}</h1>
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
      >
        <el-menu-item index="1" @click="studentViews">
          <i class="el-icon-school"></i>
          <span slot="title">学生管理</span>
        </el-menu-item>
        <el-menu-item index="2" @click="teacherViews">
          <i class="el-icon-s-custom"></i>
          <span slot="title">教师管理</span>
        </el-menu-item>
        <el-menu-item index="3" @click="userViews">
          <i class="el-icon-user"></i>
          <span slot="title">用户管理</span>
        </el-menu-item>
        <el-menu-item index="4" @click="userSetting">
          <i class="el-icon-setting"></i>
          <span slot="title">账号设置</span>
        </el-menu-item>
      </el-menu></el-aside
    >
    <el-container>
      <el-main> <router-view /></el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      //登录信息
      loginFrom: {},
    };
  },
  created() {
    this.getLoginSource();
  },
  methods: {
    //获取登录页发送的数据
    getLoginSource() {
      this.loginFrom = this.$route.params.login;
    },
    //学生管理
    studentViews() {
      this.$router.push({
        name: "adminStudent",
        params: { login: this.loginFrom },
      });
    },
    //教师管理
    teacherViews() {
      this.$router.push({
        name: "adminTeacher",
        params: { login: this.loginFrom },
      });
    },
    //用户管理
    userViews() {
      this.$router.push({
        name: "adminUser",
        params: { login: this.loginFrom },
      });
    },
    //账号设置
    userSetting() {
      this.$router.push({
        name: "adminUserSetting",
        params: { login: this.loginFrom },
      });
    },
  },
};
</script>

<style>
</style>