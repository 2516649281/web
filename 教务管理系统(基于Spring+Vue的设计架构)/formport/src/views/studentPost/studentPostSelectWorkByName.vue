<template>
  <el-container>
    <el-header></el-header>
    <el-main>
      <el-table :data="tableData" height="300">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="我的作业">
                <span>{{ props.row.studentWork }}</span>
                <span
                  ><el-button
                    size="mini"
                    icon="el-icon-edit"
                    @click="editWorks(props.row)"
                  ></el-button
                ></span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="学生作业提交编号" prop="studentWorkId">
        </el-table-column>
        <el-table-column
          label="我的成绩"
          prop="studentSource"
        ></el-table-column>
        <el-table-column label="作业编号" prop="workId"> </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="props">
            <el-button
              type="danger"
              icon="el-icon-delete"
              @click="delWork(props.row.studentWorkId)"
            ></el-button> </template
        ></el-table-column> </el-table
      ><el-button
        type="success"
        @click="addWorks"
        icon="el-icon-plus"
        circle
      ></el-button
    ></el-main>
    <el-footer>
      <el-pagination
        @current-change="handleCurrentChange"
        :page-size="size"
        layout="prev, pager, next, jumper"
        :total="pageSize"
      >
      </el-pagination>
    </el-footer>
    <el-dialog title="修改作业" :visible.sync="editdialogFormVisible">
      <el-form :model="editForm">
        <h1>当前选择学生作业提交编号{{ editForm.studentWorkId }}</h1>
        <el-form-item label="请输入新作业内容" :label-width="formLabelWidth">
          <el-input
            v-model="editForm.studentWork"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="请输入作业编号(可填)">
          <el-input v-model="editForm.workId"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="editdialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="editWork(editForm)">
            提交</el-button
          >
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="添加作业" :visible.sync="adddialogFormVisible">
      <el-form :model="addForm">
        <h1>你的学生编号是{{ addForm.studentId }}</h1>
        <el-form-item label="请输入作业内容" :label-width="formLabelWidth">
          <el-input v-model="addForm.studentWork" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请输入作业编号">
          <el-input v-model="addForm.workId"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="adddialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="addWork(addForm)"> 提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </el-container>
</template>

<script>
import {
  selectWorkByName,
  addWork,
  editWork,
  delWork,
} from "../../api/student";
export default {
  data() {
    return {
      //页码
      current: 1,
      //每页显示数
      size: 5,
      //表格数据
      tableData: [],
      //查询总数
      pageSize: "",
      //学生名
      studentId: 0,
      //登录页数据
      loginFrom: {},
      //添加作业
      addForm: {
        //学生编号
        studentId: "",
        //学生作业
        studentWork: "",
        //作业编号
        workId: "",
      },
      //修改作业
      editForm: {
        //学生作业编号
        studentWorkId: 0,
        //学生作业
        studentWork: "",
        //作业编号
        workId: "",
      },
      //删除作业
      delForm: {
        //学生作业编号
        studentWorkId: "",
      },
      //编辑框显示
      editdialogFormVisible: false,
      //添加框显示
      adddialogFormVisible: false,
    };
  },
  created() {
    this.getLoginSource();
    this.selectWorkByName(this.loginFrom.userIdentityId);
  },
  methods: {
    //获取中间页发送的数据
    getLoginSource() {
      this.loginFrom = this.$route.params.login;
    },
    //学生根据自己的姓名查询自己已经完成的作业，此过程已自动实现
    selectWorkByName(studentId) {
      this.studentId = studentId;
      selectWorkByName(this.current, this.size, this.studentId).then((res) => {
        this.pageSize = res.data.size;
        this.tableData = res.data.data[0].studentWorks;
      });
    },
    //按下编辑按钮时触发的事件
    editWorks(Source) {
      this.editdialogFormVisible = true;
      this.editForm = Source;
    },
    //修改作业
    editWork(Source) {
      editWork(Source).then((req) => {
        if (req.data.status & req.data.data) {
          this.$message({
            message: `修改作业成功！`,
            type: "success",
          });
          this.editdialogFormVisible = false;
          this.selectWorkByName(this.studentId);
        } else {
          this.$message({
            message: "修改作业失败！",
            type: "success",
          });
        }
      });
    },
    //删除作业
    delWork(workId) {
      this.delForm.studentWorkId = workId;
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          delWork(this.delForm).then((req) => {
            if (req.data.data & req.data.status) {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              this.selectWorkByName(this.studentId);
            } else {
              this.$message({
                type: "error",
                message: "删除失败!",
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    //添加作业
    addWork(Source) {
      addWork(Source).then((req) => {
        if (req.data.data & req.data.status) {
          this.$message({
            message: "添加作业成功！",
            type: "success",
          });
          this.selectWorkByName(this.studentId);
          this.adddialogFormVisible = false;
        } else {
          this.$message({
            message: "添加作业失败！",
            type: "error",
          });
        }
      });
    },
    //添加按钮触发的事件
    addWorks() {
      this.addForm.studentId = this.studentId;
      this.adddialogFormVisible = true;
    }, // 分页
    handleCurrentChange(val) {
      this.current = val;
      this.selectWorkByName(this.studentId);
    },
  },
};
</script>

<style>
</style>