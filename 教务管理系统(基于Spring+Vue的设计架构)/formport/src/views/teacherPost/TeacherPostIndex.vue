<template>
  <div>
    <el-container>
      <!-- 左侧菜单栏 -->
      <el-aside width="20%">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          router
        >
          <!-- <h1>hellop</h1> -->
          <h1>
            欢迎你,{{ loginFrom.userRealName }},你的教师编号为{{
              loginFrom.userIdentityId
            }}
          </h1>
          <el-menu-item index="1" @click="getSelectAll">
            <i class="el-icon-chat-line-round"></i>
            <span slot="title">查看作业</span>
          </el-menu-item>
          <el-menu-item index="2" @click="getSelectAllOk">
            <i class="el-icon-edit"></i>
            <span>检查作业</span>
          </el-menu-item>
          <el-menu-item index="3" @click="getAnalyze"
            ><i class="el-icon-s-marketing"></i>班级作业分析报告</el-menu-item
          >
          <el-menu-item index="4" @click="userSetting">
            <i class="el-icon-setting"></i>
            <span slot="title">账号设置</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container width="80%">
        <el-main><router-view></router-view></el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      loginFrom: {},
    };
  },
  created() {
    this.getLoginSource();
  },
  methods: {
    getLoginSource() {
      this.loginFrom = this.$route.params.login;
    },
    getSelectAll() {
      this.$router.push({
        name: "teacherSelectWork",
        params: { login: this.loginFrom },
      });
    },
    getSelectAllOk() {
      this.$router.push({
        name: "teacherSelectOkWork",
        params: { login: this.loginFrom },
      });
    },
    //班级分析
    getAnalyze() {
      this.$router.push({
        name: "teacherClassWorkAnalyze",
        params: { login: this.loginFrom },
      });
    },
    //账号设置
    userSetting() {
      this.$router.push({
        name: "teacherUserSetting",
        params: { login: this.loginFrom },
      });
    },
  },
};
</script>

<style>
</style>