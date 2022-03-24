<template>
  <el-container>
    <el-header>
      <el-form :inline="true" model="selectForm">
        <el-form-item>
          <el-tooltip placement="top">
            <div slot="content">0普通用户 1接单员 2跑腿员</div>
            <el-input
              v-model="selectForm.userDuty"
              placeholder="请输入职务代码"
            ></el-input> </el-tooltip
        ></el-form-item>
        <el-form-item
          ><el-button
            type="primary"
            @click="selectLikeDury(selectForm.userDuty)"
            >查询</el-button
          ></el-form-item
        >
        <el-form-item
          ><el-button type="success" @click="adddialogTableVisible = true"
            >添加</el-button
          ></el-form-item
        >
      </el-form>
    </el-header>
    <el-main>
      <el-table :data="tableData" height="485" stripe>
        <el-table-column
          fixed
          label="编号"
          prop="userId"
          width="100"
        ></el-table-column>
        <el-table-column
          label="姓名"
          prop="userName"
          width="150"
        ></el-table-column>
        <el-table-column
          label="年龄"
          prop="userAge"
          width="100"
        ></el-table-column>
        <el-table-column
          label="性别"
          prop="userSex"
          width="100"
        ></el-table-column>
        <el-table-column
          label="住址"
          prop="userAddress"
          width="150"
        ></el-table-column>
        <el-table-column
          label="手机号"
          prop="userPhone"
          width="200"
        ></el-table-column>
        <el-table-column label="职务" prop="userDuty">
          <template slot-scope="scope">
            <el-tag
              :type="
                scope.row.userDuty === 0
                  ? 'success'
                  : scope.row.userDuty === 1
                  ? 'warning'
                  : 'primary'
              "
              disable-transitions
              >{{
                scope.row.userDuty === 0
                  ? "普通用户"
                  : scope.row.userDuty === 1
                  ? "接单员"
                  : "跑腿员"
              }}</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="120">
          <template slot-scope="scope">
            <el-button
              type="success"
              icon="el-icon-edit"
              circle
              title="编辑"
              @click="edit(scope.row)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              title="删除"
              @click="delUser(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
    <el-footer
      ><el-pagination
        background
        layout="prev, pager, next"
        :total="this.pageSize"
        :page-size="this.size"
        @current-change="handleCurrentChange"
      >
      </el-pagination
    ></el-footer>
    <el-dialog
      title="添加用户"
      :visible.sync="adddialogTableVisible"
      id="adddialog"
    >
      <el-form model="addForm">
        <el-form-item>
          <el-input
            v-model="addForm.userName"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item
          ><el-input
            v-model="addForm.userAge"
            placeholder="请输入用户年龄"
          ></el-input
        ></el-form-item>
        <el-form-item
          ><el-input
            v-model="addForm.userSex"
            placeholder="请输入用户性别"
          ></el-input
        ></el-form-item>
        <el-form-item
          ><el-input
            v-model="addForm.userAddress"
            placeholder="请输入用户地址"
          ></el-input
        ></el-form-item>
        <el-form-item
          ><el-input
            v-model="addForm.userPhone"
            placeholder="请输入用户手机"
          ></el-input
        ></el-form-item>
        <el-form-item>
          <el-input
            v-model="addForm.userDuty"
            placeholder="请输入用户职务"
          ></el-input
        ></el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addUser(addForm)">提交</el-button>
          <el-button @click="adddialogTableVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog
      title="修改用户"
      :visible.sync="editdialogTableVisible"
      id="editdialog"
    >
      <el-form model="editForm">
        <el-form-item>
          <el-input
            v-model="editForm.userName"
            placeholder="请输入新用户名"
          ></el-input>
        </el-form-item>
        <el-form-item
          ><el-input
            v-model="editForm.userAge"
            placeholder="请输入新用户年龄"
          ></el-input
        ></el-form-item>
        <el-form-item
          ><el-input
            v-model="editForm.userSex"
            placeholder="请输入新用户性别"
          ></el-input
        ></el-form-item>
        <el-form-item
          ><el-input
            v-model="editForm.userAddress"
            placeholder="请输入新用户地址"
          ></el-input
        ></el-form-item>
        <el-form-item
          ><el-input
            v-model="editForm.userPhone"
            placeholder="请输入新用户手机"
          ></el-input
        ></el-form-item>
        <el-form-item>
          <el-tooltip placement="top">
            <div slot="content">0普通用户 1接单员 2跑腿员</div>
            <el-input
              v-model="editForm.userDuty"
              placeholder="请输入新用户职务"
            ></el-input>
          </el-tooltip>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateUser(editForm)"
            >提交</el-button
          >
          <el-button @click="editdialogTableVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </el-container>
</template>

<script>
import {
  selectAllUser,
  selectLikeDury,
  addUser,
  updateUser,
  delUser,
} from "../../api/user";
export default {
  data() {
    return {
      //表格的初始数据
      tableData: [],
      //页码
      current: 1,
      //页长
      size: 10,
      //总页数
      pageSize: "",
      //查询
      selectForm: {
        //用户职务
        userDuty: "",
      },
      //添加数据
      addForm: {
        //用户姓名
        userName: "",
        //用户年龄
        userAge: "",
        //用户性别
        userSex: "",
        //用户地址
        userAddress: "",
        //用户电话
        userPhone: "",
        //用户职务
        userDuty: "",
      },
      //编辑数据
      editForm: {
        //用户姓名
        userName: "",
        //用户年龄
        userAge: "",
        //用户性别
        userSex: "",
        //用户地址
        userAddress: "",
        //用户电话
        userPhone: "",
        //用户职务
        userDuty: "",
      },
      //添加模态框
      adddialogTableVisible: false,
      //编辑模态框
      editdialogTableVisible: false,
    };
  },
  created() {
    this.selectAllUser(1, 10);
  },
  methods: {
    //查询所有用户
    selectAllUser(current, size) {
      selectAllUser(current, size).then((res) => {
        this.tableData = res.data.data;
        this.pageSize = res.data.pageSize;
      });
      console.log(this.tableData);
    },
    //根据职务查询
    selectLikeDury(userDuty) {
      selectLikeDury(this.current, this.size, userDuty).then((res) => {
        this.tableData = res.data.data;
        this.pageSize = res.data.pageSize;
      });
    },
    //增加用户
    addUser(obj) {
      addUser(obj).then((res) => {
        if (res.data.data & res.data.msg) {
          this.$message({
            message: "添加用户成功!",
            type: "success",
            showClose: true,
          });
          this.selectLikeDury(this.selectForm.userDuty);
          this.adddialogTableVisible = false;
        } else {
          this.$message({
            message: "添加用户失败!",
            type: "error",
            showClose: true,
          });
        }
      });
    },
    //修改用户
    updateUser(obj) {
      updateUser(obj).then((res) => {
        if (res.data.data & res.data.msg) {
          this.$message({
            message: "修改用户成功!",
            type: "success",
            showClose: true,
          });
          this.selectLikeDury(this.selectForm.userDuty);
          this.editdialogTableVisible = false;
        } else {
          this.$message({
            message: "修改用户失败!",
            type: "error",
            showClose: true,
          });
        }
      });
    },
    //删除用户
    delUser(obj) {
      this.$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        delUser(obj).then((res) => {
          if (res.data.data & res.data.msg) {
            this.$message({
              type: "success",
              message: "删除成功!",
              showClose: true,
            });
          } else {
            this.$message({
              type: "error",
              message: "删除失败!",
              showClose: true,
            });
          }
          this.selectLikeDury(this.selectForm.userDuty);
        });
      });
    },
    //编辑
    edit(obj) {
      this.editdialogTableVisible = true;
      this.editForm = obj;
    },
    // 分页
    handleCurrentChange(val) {
      this.current = val;
      this.selectLikeDury(this.selectForm.userDuty);
    },
  },
};
</script>

<style>
#adddialog,
#editdialog {
  text-align: center;
}
</style>