<template>
  <el-container>
    <el-header>
      <p>
        欢迎你,{{ loginFrom.userRealName }},你的班级为{{
          loginFrom.className
        }},学生编号为{{ loginFrom.userIdentityId }}
      </p>
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        router
      >
        <el-menu-item index="1" @click="meWork"
          ><i class="el-icon-edit-outline"></i><span>我的作业</span>
        </el-menu-item>
        <el-menu-item index="2" @click="classWork"
          ><i class="el-icon-s-grid"></i><span>我的班级</span>
        </el-menu-item>
        <el-menu-item index="4" @click="userSetting">
          <i class="el-icon-setting"></i>
          <span slot="title">账号设置</span>
        </el-menu-item>
      </el-menu>
    </el-header>
    <el-main><router-view></router-view></el-main>
    <el-footer></el-footer>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      //登录页数据
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
    //我的作业菜单跳转并传参
    meWork() {
      this.$router.push({
        name: "studentSelectWorkByName",
        params: { login: this.loginFrom },
      });
    },
    //班级作业菜单跳转并传参
    classWork() {
      this.$router.push({
        name: "studentSelectWorkByCLass",
        params: { login: this.loginFrom },
      });
    },
    //获取登录页发送的数据
    getLoginSource() {
      this.loginFrom = this.$route.params.login;
    },
    //账号设置
    userSetting() {
      this.$router.push({
        name: "studentUserSetting",
        params: { login: this.loginFrom },
      });
    },
  },
};
</script>

<style>
</style>