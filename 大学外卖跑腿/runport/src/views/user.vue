<template>
  <el-container id="main">
    <el-main>
      <el-descriptions title="账号信息">
        <el-descriptions-item label="用户名">{{
          account.accountName
        }}</el-descriptions-item>
        <el-descriptions-item label="密码">
          {{ this.passwordVisible ? account.accountPassword : "*******" }}
          <i
            class="el-icon-view"
            @click="passwordVisible = !passwordVisible"
          ></i>
        </el-descriptions-item>
      </el-descriptions>
      <el-divider></el-divider>
      <el-descriptions title="身份信息" border>
        <el-descriptions-item label="真实姓名">{{
          account.user.userName
        }}</el-descriptions-item>
        <el-descriptions-item label="年龄">
          {{ account.user.userAge }}
        </el-descriptions-item>
        <el-descriptions-item label="性别">
          {{ account.user.userSex }}
        </el-descriptions-item>
        <el-descriptions-item label="手机号">{{
          account.user.userPhone
        }}</el-descriptions-item> 
        <el-descriptions-item label="联系地址">{{
          account.user.userAddress
        }}</el-descriptions-item>
        <el-descriptions-item label="职务">{{
          account.user.userDuty == 0
            ? "无"
            : account.user.userDuty === 1
            ? "管理员"
            : "跑腿员"
        }}</el-descriptions-item>
      </el-descriptions>
      <el-divider></el-divider>
      <el-form inline="true" id="form">
        <el-form-item>
          <el-button type="primary" @click="edit(account)"
            >修改账号信息</el-button
          >
        </el-form-item>
      </el-form>
    </el-main>
    <el-dialog
      title="请输入原始账号信息，以确定是你本人"
      :visible.sync="verifyUser"
    >
      <el-form :model="verifyForm">
        <el-form-item label="账号" :label-width="formLabelWidth">
          <el-input
            v-model="verifyForm.accountName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input
            v-model="verifyForm.accountPassword"
            show-password
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="verifyUser = false">取 消</el-button>
        <el-button type="primary" @click="verify(verifyForm)">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改账号信息" :visible.sync="editdialogFormVisible">
      <el-form :model="editForm">
        <el-form-item label="输入新账号" :label-width="formLabelWidth">
          <el-input
            v-model="editForm.accountName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="请输入新密码" :label-width="formLabelWidth">
          <el-input v-model="editForm.accountPassword" show-password></el-input>
        </el-form-item>
        <el-form-item label="再次输入新密码" :label-width="formLabelWidth">
          <el-input
            v-model="editForm.againaccountPassword"
            show-password
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editdialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateAccount(editForm)"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </el-container>
</template>

<script>
import jwtDecode from "jwt-decode";
import { updateAccount } from "../api/index";
export default {
  data() {
    return {
      //用户信息
      account: {
        //身份
        user: {},
      },
      //编辑数据
      editForm: {
        //账号
        accountName: "",
        //密码
        accountPassword: "",
        againaccountPassword: "",
      },
      //原始数据
      verifyForm: {
        //账号
        accountName: "",
        //密码
        accountPassword: "",
      },
      //密码显示
      passwordVisible: false,
      //编辑框
      editdialogFormVisible: false,
      //验证用户信息
      verifyUser: false,
    };
  },
  created() {
    this.account = jwtDecode(sessionStorage.getItem("token")).user;
  },
  methods: {
    //修改
    updateAccount(obj) {
      if (obj.accountPassword === obj.againaccountPassword) {
        updateAccount(obj).then((res) => {
          if (res.data.data & res.data.msg) {
            this.$message({
              message: "修改账号成功!请重新登录",
              type: "success",
              onClose: true,
            });
            this.editdialogFormVisible = false;
            sessionStorage.setItem("token", null);
            this.$router.push({ name: "login" });
          } else {
            this.$message({
              message: "修改账号失败!",
              type: "error",
              onClose: true,
            });
          }
        });
      } else {
        this.$message({
          message: "两次输入的密码不一致!",
          type: "warning",
        });
      }
    },
    //校验
    verify(obj) {
      if (
        (obj.accountName === this.account.accountName) &
        (obj.accountPassword === this.account.accountPassword)
      ) {
        this.verifyUser = false;
        this.editdialogFormVisible = true;
        this.$message({
          message: "验证成功!",
          type: "success",
        });
      } else {
        this.$message({
          message: "验证失败!",
          type: "error",
        });
      }
    },
    edit(obj) {
      this.editForm = obj;
      this.verifyUser = true;
    },
  },
};
</script>

<style>
#main {
  width: 100%;
  height: 100%;
}
#form {
  text-align: center;
}
</style>