<template>
  <el-container>
    <el-header>
      <el-form :inline="true" label-position="left" model="selectForm">
        <el-form-item
          ><el-input
            placeholder="账号名"
            v-model="selectForm.accountName"
          ></el-input
        ></el-form-item>
        <el-form-item
          ><el-button
            type="primary"
            @click="selectAllLikeName(selectForm.accountName)"
            >查询</el-button
          ></el-form-item
        >
      </el-form>
    </el-header>
    <el-main>
      <el-table :data="tableData" height="485" stripe>
        <el-table-column
          fixed
          label="编号"
          prop="accountId"
          width="80"
        ></el-table-column>
        <el-table-column
          label="账号名"
          prop="accountName"
          width="200"
        ></el-table-column>
        <el-table-column
          label="创建时间"
          prop="accountCreateTime"
          width="250"
        ></el-table-column>
        <el-table-column
          label="修改时间"
          prop="accountUpdateTime"
          width="250"
        ></el-table-column>
        <el-table-column
          prop="accountDeleted"
          label="状态"
          width="100"
          :filter-method="filterTag"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.accountDeleted === 0 ? 'success' : 'danger'"
              disable-transitions
              >{{ scope.row.accountDeleted === 0 ? "正常" : "冻结中" }}</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="120">
          <template slot-scope="scope">
            <el-button
              circle
              icon="el-icon-warning-outline"
              type="danger"
              title="冻结"
              @click="deleteAccount(scope.row)"
            ></el-button>
            <el-button
              circle
              icon="el-icon-refresh-right"
              type="success"
              title="恢复"
              @click="unfreeAccount(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
    <el-footer
      ><el-pagination
        background
        layout="prev, pager, next"
        :page-size="this.size"
        :total="this.pageSize"
        @current-change="handleCurrentChange"
      >
      </el-pagination
    ></el-footer>
  </el-container>
</template>

<script>
import {
  selectAllAccount,
  selectAllLikeName,
  deleteAccount,
  unfreeAccount,
} from "../../api/account";
export default {
  data() {
    return {
      //表格初始数据
      tableData: [],
      //页码
      current: 1,
      //页长
      size: 10,
      //总页数
      pageSize: "",
      //查询的数据
      selectForm: {
        //账号名
        accountName: "",
      },
    };
  },
  created() {
    this.selectAllAccount(1, 10);
  },
  methods: {
    //查询所有账号
    selectAllAccount(current, size) {
      selectAllAccount(current, size).then((res) => {
        this.tableData = res.data.data;
        this.pageSize = res.data.pageSize;
      });
    },
    //根据账号名查询账号
    selectAllLikeName(accountName) {
      selectAllLikeName(this.current, this.size, accountName).then((res) => {
        this.tableData = res.data.data;
      });
    },
    //冻结账号
    deleteAccount(obj) {
      this.$confirm("此操作将冻结该账号, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteAccount(obj).then((res) => {
          if (res.data.data & res.data.msg) {
            this.$message({
              type: "success",
              message: "冻结成功!",
              showClose: true,
            });
            this.selectAllLikeName(this.selectForm.accountName);
          } else {
            this.$message({
              type: "error",
              message: "冻结失败!",
              showClose: true,
            });
          }
        });
      });
    },
    //恢复账号
    unfreeAccount(obj) {
      this.$confirm("此操作将恢复已冻结账号, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        unfreeAccount(obj).then((res) => {
          if (res.data.data & res.data.msg) {
            this.$message({
              type: "success",
              message: "恢复成功!",
              showClose: true,
            });
            this.selectAllLikeName(this.selectForm.accountName);
          } else {
            this.$message({
              type: "error",
              message: "恢复失败!",
              showClose: true,
            });
          }
        });
      });
    },
    // 分页
    handleCurrentChange(val) {
      this.current = val;
      this.selectAllLikeName(this.selectForm.accountName);
    },
  },
};
</script>

<style>
</style>