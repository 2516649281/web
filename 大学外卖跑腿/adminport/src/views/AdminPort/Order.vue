<template>
  <el-container>
    <el-header>
      <el-form :inline="true" label-position="left" model="selectForm">
        <el-form-item
          ><el-input
            placeholder="订单名"
            v-model="selectForm.orderName"
          ></el-input
        ></el-form-item>
        <el-form-item
          ><el-button
            type="primary"
            @click="selectAllLikeName(selectForm.orderName)"
            >查询</el-button
          ></el-form-item
        >
      </el-form>
    </el-header>
    <el-main>
      <el-table :data="tableData" height="485" id="table" stripe>
        <el-table-column type="expand" fixed>
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="订单内容:">
                <span>{{ props.row.orderName }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          label="编号"
          prop="orderId"
          width="100"
          fixed
        ></el-table-column>
        <el-table-column label="订单发起人" prop="user">
          <el-table-column
            label="姓名"
            prop="user.userName"
            width="100"
          ></el-table-column>
          <el-table-column
            label="联系电话"
            prop="user.userPhone"
            width="150"
          ></el-table-column>
        </el-table-column>
        <el-table-column label="接单员" prop="admin" width="200">
          <el-table-column
            label="姓名"
            prop="admin.userName"
            width="100"
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
            width="100"
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
        <el-table-column label="操作" fixed="right" width="115"
          ><template slot-scope="props">
            <el-button
              type="primary"
              icon="el-icon-s-claim"
              circle
              @click="edit(props.row)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="delOrder(props.row)"
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
      title="接单后接单员不可更改!"
      :visible.sync="editdialogFormVisible"
    >
      <el-form :model="editForm">
        <h3>已选择{{ editForm.orderId }}号订单</h3>
        <p>你的接单员编号:{{ editForm.adminId }}</p>
        <div>请接单员指定跑腿员送达</div>
        <el-input
          v-model="editForm.runId"
          placeholder="输入跑腿员编号"
        ></el-input>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editdialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateOrder(editForm)"
          >接 单</el-button
        >
      </div>
    </el-dialog>
  </el-container>
</template>

<script>
import jwtDecode from "jwt-decode";
import {
  selectAll,
  selectAllLikeName,
  updateOrder,
  delOrder,
} from "../../api/order";
export default {
  data() {
    return {
      //用户信息
      user: {
        userId: "",
        userName: "",
      },
      //查询结果
      tableData: [],
      //页码
      current: 1,
      //页长
      size: 10,
      //总页数
      pageSize: "",
      //查询的订单
      selectForm: {
        //订单名
        orderName: "",
      },
      //编辑数据
      editForm: {
        //订单号
        orderId: "",
        //用户编号
        userId: "",
        //管理员编号
        adminId: "",
        //跑腿员
        runId: "",
      },
      editdialogFormVisible: false,
    };
  },
  created() {
    this.user = jwtDecode(sessionStorage.getItem("token")).user.user;
    this.selectAll(this.current, this.size);
  },
  methods: {
    //查询所有
    selectAll(current, size) {
      selectAll(current, size).then((res) => {
        this.tableData = res.data.data;
        this.pageSize = res.data.pageSize;
      });
    },
    //根据名称查询
    selectAllLikeName(orderName) {
      selectAllLikeName(this.current, this.size, orderName).then((res) => {
        this.tableData = res.data.data;
        this.pageSize = res.data.pageSize;
      });
    },
    //修改订单
    updateOrder(obj) {
      updateOrder(obj).then((res) => {
        if (res.data.data & res.data.msg) {
          this.$message({
            message: "接单成功!",
            type: "success",
            showClose: true,
          });
          this.selectAllLikeName(this.selectForm.orderName);
          this.editdialogFormVisible = false;
        } else {
          this.$message({
            message: "接单失败!",
            type: "error",
            showClose: true,
          });
        }
      });
    },
    //删除订单
    delOrder(obj) {
      this.$confirm("此操作将永久删除该订单, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        delOrder(obj).then((res) => {
          if (res.data.data & res.data.msg) {
            this.$message({
              type: "success",
              message: "删除成功!",
              showClose: true,
            });
            this.selectAllLikeName(this.selectForm.orderName);
          } else {
            this.$message({
              type: "error",
              message: "删除失败!",
              showClose: true,
            });
          }
        });
      });
    },
    //编辑操作
    edit(obj) {
      this.editdialogFormVisible = true;
      this.editForm = obj;
      this.editForm.adminId = this.user.userId;
    },
    // 分页
    handleCurrentChange(val) {
      this.current = val;
      this.selectAllLikeName(this.selectForm.orderName);
    },
  },
};
</script>

<style>
#table tr,
th {
  text-align: center;
}
</style>