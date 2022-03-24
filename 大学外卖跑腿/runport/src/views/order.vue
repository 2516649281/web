<template>
  <el-container id="main">
    <el-main
      ><el-table :data="tableData" height="441" id="table">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="业务需求:">
                <span>{{ props.row.orderName }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          label="编号"
          prop="orderId"
          width="50"
        ></el-table-column>
        <el-table-column label="订单发起人(用户)" prop="user">
          <el-table-column
            label="姓名"
            prop="user.userName"
            width="120"
          ></el-table-column>
          <el-table-column
            label="联系电话"
            prop="user.userPhone"
            width="150"
          ></el-table-column>
        </el-table-column>
        <el-table-column label="接单员" prop="admin">
          <el-table-column
            label="姓名"
            prop="admin.userName"
            width="120"
          ></el-table-column>
          <el-table-column
            label="联系电话"
            prop="admin.userPhone"
            width="150"
          ></el-table-column>
        </el-table-column>
        <el-table-column label="跑腿员" prop="run">
          <el-table-column
            label="姓名"
            prop="run.userName"
            width="120"
          ></el-table-column>
          <el-table-column
            label="联系电话"
            prop="run.userPhone"
            width="150"
          ></el-table-column>
        </el-table-column>
        <el-table-column
          label="创建时间"
          prop="orderCreateTime"
          width="250"
        ></el-table-column>
        <el-table-column
          label="最后修改时间"
          prop="orderUpdateTime"
          width="250"
        ></el-table-column>
      </el-table>
    </el-main>
    <el-footer>
      <el-pagination
        background
        layout="prev, pager, next"
        :total="this.pageSize"
        :page-size="this.size"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </el-footer>
  </el-container>
</template>

<script>
import jwtDecode from "jwt-decode";
import { selectAllOrderByName } from "../api/index";
export default {
  data() {
    return {
      //解析后的token数据
      token: {},
      //表格数据
      tableData: [],
      //页码
      current: 1,
      //每页显示数
      size: 10,
      //总页数
      pageSize: "",
    };
  },
  created() {
    var user = jwtDecode(sessionStorage.getItem("token"));
    this.token = user;
    this.selectAllOrderByName(this.current, this.size, user.user.user.userId);
  },
  methods: {
    //查询当前用户账单
    selectAllOrderByName(current, size, runId) {
      selectAllOrderByName(current, size, runId).then((res) => {
        this.tableData = res.data.data;
        this.pageSize = res.data.pageSize;
      });
    },
    // 分页
    handleCurrentChange(val) {
      this.current = val;
      this.selectAllOrderByName(
        this.current,
        this.size,
        this.token.user.user.userId
      );
    },
  },
};
</script>

<style>
#main {
  width: 100%;
  height: 100%;
}
#table {
  width: 100%;
}
</style>