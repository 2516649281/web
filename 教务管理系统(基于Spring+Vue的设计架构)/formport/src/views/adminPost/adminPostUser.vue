//管理端管理用户
//时间:2022/2/24
<template>
  <el-container>
    <el-header
      ><el-form :inline="true" ref="formInline" class="demo-form-inline">
        <el-form-item prop="userName">
          <el-input
            class="inline-input"
            placeholder="请输入用户名关键字"
            v-model="formInline.userName"
          ></el-input> </el-form-item
        ><el-form-item>
          <el-button
            type="primary"
            @click="
              selectAllUserLikeUserName(current, size, formInline.userName)
            "
            >查询</el-button
          >
        </el-form-item>
      </el-form></el-header
    >
    <el-main
      ><el-table :data="tableData" height="500" style="width: 100%">
        <el-table-column
          label="用户编号"
          prop="userId"
          width="140"
        ></el-table-column>
        <el-table-column
          label="用户名"
          prop="userName"
          width="140"
        ></el-table-column>
        <el-table-column
          prop="userStatus"
          label="用户状态"
          width="140"
          :filters="[
            { text: '正常', value: '正常' },
            { text: '冻结中', value: '冻结中' },
          ]"
          :filter-method="filterTag"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.userStatus === '正常' ? 'success' : 'danger'"
              disable-transitions
              >{{ scope.row.userStatus }}</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column label="管理员权限" prop="admin" width="180">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.admin"
              active-color="#13ce66"
              inactive-color="#ff4949"
              inactive-text="普通用户"
              active-text="管理员"
            >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column
          label="创建时间"
          prop="userCreateTime"
          width="240"
        ></el-table-column>
        <el-table-column
          label="最后修改时间"
          prop="userUpdateTime"
          width="240"
        ></el-table-column>
        <el-table-column fixed="right" label="操作" width="140">
          <template slot-scope="scope">
            <el-button
              icon="el-icon-lock"
              size="mini"
              type="danger"
              @click="delUser(scope.row.userId)"
              circle
            ></el-button>
            <el-button
              icon="el-icon-unlock"
              size="mini"
              type="success"
              @click="unfreeUser(scope.row.userId)"
              circle
            ></el-button>
            <el-button
              icon="el-icon-star-off"
              size="mini"
              type="warning"
              @click="addOrDelAdmin(scope.row)"
              circle
            ></el-button>
          </template> </el-table-column></el-table
    ></el-main>
    <el-footer>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage3"
        :page-size="size"
        layout="prev, pager, next, jumper"
        :total="pageSize"
      >
      </el-pagination>
    </el-footer>
  </el-container>
</template>

<script>
import {
  selectAllUser,
  selectAllUserLikeUserName,
  delUser,
  unfreeUser,
  addOrDelAdmin,
} from "../../api/admin";
export default {
  data() {
    return {
      //页码
      current: 1,
      //上一次的输入
      userNameUp: "",
      //每页显示数
      size: 8,
      //查询总数
      pageSize: "",
      //表格数据
      tableData: [
        {
          //用户编号
          userId: "",
          //用户名
          userName: "",
          //用户状态
          userDeleted: "",
          //创建时间
          userCreateTime: "",
          //最后修改时间
          userUpdateTime: "",
          //状态
          userStatus: "",
          //管理员权限
          admin: "",
          //用户权限码
          userAdmin: "",
        },
      ],
      //查询数据
      formInline: {
        //用户名
        userName: "",
      },
      //冻结数据
      delForm: {
        userId: "",
      },
      //解冻数据
      addForm: {
        userId: "",
      },
      //授权数据
      addAdminFrom: {
        userId: "",
        userAdmin: "",
        admin: "",
      },
    };
  },
  created() {
    this.selectAllUser(this.current, this.size);
  },
  methods: {
    //查询所有用户
    selectAllUser(current, size) {
      selectAllUser(current, size).then((res) => {
        this.tableData = res.data.data;
        this.pageSize = res.data.size;
        for (var i = 0; i < this.tableData.length; i++) {
          this.tableData[i].userStatus =
            this.tableData[i].userDeleted === 1 ? "冻结中" : "正常";
          this.tableData[i].admin =
            this.tableData[i].userAdmin === 1 ? true : false;
        }
      });
    },
    //按用户名模糊查询
    selectAllUserLikeUserName(current, size, userName) {
      selectAllUserLikeUserName(current, size, userName).then((res) => {
        this.tableData = res.data.data;
        this.pageSize = res.data.size;
        for (var i = 0; i < this.tableData.length; i++) {
          this.tableData[i].userStatus =
            this.tableData[i].userDeleted === 1 ? "冻结中" : "正常";
          this.tableData[i].admin =
            this.tableData[i].userAdmin === 1 ? true : false;
        }
        if (!(this.userNameUp === userName)) {
          if (res.data.status) {
            this.$message({
              message: `已查询到${this.pageSize}条数据`,
              type: "success",
            });
          } else {
            this.$message({
              message: `查询失败`,
              type: "error",
            });
          }
        }
        this.userNameUp = userName;
      });
    },
    //冻结账号
    delUser(userId) {
      this.delForm.userId = userId;
      this.$confirm("此操作将冻结并重置该账号, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          delUser(this.delForm).then((req) => {
            if (req.data.data & req.data.status) {
              this.$message({
                message: `已冻结${userId}号用户`,
                type: "success",
              });
              this.selectAllUserLikeUserName(
                this.current,
                this.size,
                this.userNameUp
              );
            } else {
              this.$message({
                message: `操作失败`,
                type: "error",
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消冻结",
          });
        });
    },
    //解冻账号
    unfreeUser(userId) {
      this.addForm.userId = userId;
      this.$confirm("此操作将解冻该账号, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          unfreeUser(this.addForm).then((req) => {
            if (req.data.data & req.data.status) {
              this.$message({
                message: `已解冻${userId}号用户`,
                type: "success",
              });
              this.selectAllUserLikeUserName(
                this.current,
                this.size,
                this.userNameUp
              );
            } else {
              this.$message({
                message: `操作失败`,
                type: "error",
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消解冻",
          });
        });
    },
    //授权
    addOrDelAdmin(Source) {
      this.addAdminFrom = Source;
      this.addAdminFrom.userAdmin = this.addAdminFrom.admin ? 0 : 1;
      console.log(this.addAdminFrom);
      this.$confirm(
        `你真的要更改${this.addAdminFrom.userId}号用户的管理员权限吗?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          addOrDelAdmin(this.addAdminFrom).then((req) => {
            if (req.data.data & req.data.status) {
              this.$message({
                message: `已更改${this.addAdminFrom.userId}号用户管理员权限`,
                type: "success",
              });
              this.selectAllUserLikeUserName(
                this.current,
                this.size,
                this.userNameUp
              );
            } else {
              this.$message({
                message: `授权失败`,
                type: "error",
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消授权",
          });
        });
    },
    //标签筛选
    filterTag(value, row) {
      return row.userStatus === value;
    },
    filterHandler(value, row, column) {
      const property = column["success"];
      return row[property] === value;
    },
    // 分页
    handleCurrentChange(val) {
      this.current = val;
      this.selectAllUserLikeUserName(
        this.current,
        this.size,
        this.formInline.userName
      );
    },
  },
};
</script>

<style>
</style>