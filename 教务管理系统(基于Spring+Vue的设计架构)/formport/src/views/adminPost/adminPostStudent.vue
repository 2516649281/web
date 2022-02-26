//管理端管理学生
//时间：2022/2/24 11:30
<template>
  <el-container>
    <el-header>
      <el-form :inline="true" ref="formInline" class="demo-form-inline">
        <el-form-item prop="className">
          <el-autocomplete
            class="inline-input"
            :fetch-suggestions="querySearch"
            placeholder="请输入班级名"
            v-model="formInline.className"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            @click="
              selectAllStudentByClassName(current, size, formInline.className)
            "
            >查询</el-button
          >
          <el-button type="success" @click="adddialogFormVisible = true"
            >添加学生</el-button
          ></el-form-item
        ></el-form
      >
    </el-header>
    <el-main
      ><el-table :data="tableData" height="500">
        <el-table-column label="学生编号" prop="studentId"></el-table-column>
        <el-table-column label="学生姓名" prop="studentName"></el-table-column>
        <el-table-column label="学生班级" prop="className"></el-table-column>
        <el-table-column label="学生性别" prop="studentSex"></el-table-column>
        <el-table-column label="学生年龄" prop="studentAge"></el-table-column>
        <el-table-column
          label="学生地址"
          prop="studentAddress"
        ></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              icon="el-icon-edit"
              size="mini"
              @click="editStudent(scope.row)"
              circle
            ></el-button>
            <el-button
              icon="el-icon-delete"
              size="mini"
              type="danger"
              @click="delStudentById(scope.row.studentId)"
              circle
            ></el-button>
          </template>
        </el-table-column> </el-table
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
      </el-pagination
    ></el-footer>
    <!-- 添加学生 -->
    <el-dialog title="添加学生" :visible.sync="adddialogFormVisible">
      <el-form :model="addForm">
        <el-form-item label="请输入学生姓名:" :label-width="formLabelWidth">
          <el-input v-model="addForm.studentName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请输入学生班级:" :label-width="formLabelWidth">
          <el-autocomplete
            class="inline-input"
            :fetch-suggestions="querySearch"
            v-model="addForm.className"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="请输入学生年龄:" :label-width="formLabelWidth">
          <el-input v-model="addForm.studentAge" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请选择学生性别:" :label-width="formLabelWidth">
          <el-radio v-model="addForm.studentSex" label="男"
            ><i class="el-icon-male"></i
          ></el-radio>
          <el-radio v-model="addForm.studentSex" label="女"
            ><i class="el-icon-female"></i
          ></el-radio>
        </el-form-item>
        <el-form-item label="请输入学生家庭地址:" :label-width="formLabelWidth">
          <el-input
            v-model="addForm.studentAddress"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="adddialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addStudent(addForm)">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 修改学生 -->
    <el-dialog title="修改学生" :visible.sync="editdialogFormVisible">
      <el-form :model="editForm">
        <h1>你选择了{{ editForm.studentId }}号学生</h1>
        <el-form-item label="请输入学生新姓名:" :label-width="formLabelWidth">
          <el-input
            v-model="editForm.studentName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="请输入学生新班级:" :label-width="formLabelWidth">
          <el-autocomplete
            class="inline-input"
            :fetch-suggestions="querySearch"
            v-model="editForm.className"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="请输入学生新年龄:" :label-width="formLabelWidth">
          <el-input v-model="editForm.studentAge" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请选择学生新性别:" :label-width="formLabelWidth">
          <el-radio v-model="editForm.studentSex" label="男"
            ><i class="el-icon-male"></i
          ></el-radio>
          <el-radio v-model="editForm.studentSex" label="女"
            ><i class="el-icon-female"></i
          ></el-radio>
        </el-form-item>
        <el-form-item
          label="请输入学生新家庭地址:"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="editForm.studentAddress"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editdialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editStudentById(editForm)"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </el-container>
</template>

<script>
import {
  addStudent,
  editStudentById,
  selectAllStudent,
  delStudentById,
  selectAllStudentByClassName,
  selectAllClass,
} from "../../api/admin";
export default {
  data() {
    return {
      //上一次的输入
      classNameUp: "",
      //表格数据
      tableData: [],
      //查询总数
      pageSize: "",
      //页码
      current: 1,
      //每页显示数
      size: 10,
      //登录数据
      loginFrom: {},
      //查询框数据
      formInline: {
        className: "",
      },
      //添加学生对话框数据
      addForm: {
        //学生姓名
        studentName: "",
        //班级名
        className: "",
        //学生年龄
        studentAge: "",
        //学生性别
        studentSex: "",
        //学生地址
        studentAddress: "",
      },
      //删除学生数据
      delForm: {
        studentId: "",
      },
      //修改学生数据
      editForm: {
        //学生编号
        studentId: "",
        //学生姓名
        studentName: "",
        //班级名
        className: "",
        //学生年龄
        studentAge: "",
        //学生性别
        studentSex: "",
        //学生地址
        studentAddress: "",
      },
      //添加框显示
      adddialogFormVisible: false,
      //修改框显示
      editdialogFormVisible: false,
    };
  },
  created() {
    this.getLoginSource();
    this.selectAllClassName(1, 200);
    this.selectAllStudent(this.current, this.size);
  },
  methods: {
    //获取中间页发送的数据
    getLoginSource() {
      this.loginFrom = this.$route.params.login;
    },
    //查询所有学生
    selectAllStudent(current, size) {
      selectAllStudent(current, size).then((res) => {
        this.pageSize = res.data.size;
        this.tableData = res.data.data;
      });
    },
    //根据班级名查询学生
    selectAllStudentByClassName(current, size, className) {
      selectAllStudentByClassName(current, size, className).then((res) => {
        this.pageSize = res.data.size;
        try {
          this.tableData = res.data.data;
        } catch (error) {
          this.tableData = null;
          this.$message({
            message: `班级输入有误`,
            type: "warning",
          });
          return;
        }
        if (!(this.classNameUp === className)) {
          if (res.data.status) {
            this.$message({
              message: `已查询到${this.pageSize}条数据`,
              type: "success",
            });
          } else {
            this.$message({
              message: "查询失败，请检查班级字段是否输入正确",
              type: "error",
            });
          }
        }
        this.classNameUp = className;
      });
    },
    //查询所有班级用于输入框提示
    selectAllClassName(current, size) {
      selectAllClass(current, size).then((res) => {
        this.className = res.data.data;
      });
    },
    //添加学生
    addStudent(Source) {
      addStudent(Source).then((req) => {
        if (req.data.data & req.data.status) {
          this.$message({
            message: "添加学生成功",
            type: "success",
          });
          this.selectAllStudentByClassName(
            this.current,
            this.size,
            this.classNameUp
          );
          this.adddialogFormVisible = false;
        } else {
          this.$message({
            message: "添加学生失败",
            type: "error",
          });
        }
      });
    },
    //删除学生
    delStudentById(studentId) {
      this.delForm.studentId = studentId;
      this.$confirm("此操作将永久删除该学生, 是否继续?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          delStudentById(this.delForm).then((req) => {
            if (req.data.data & req.data.status) {
              this.$message({
                message: `已删除${studentId}号学生`,
                type: "success",
              });
              this.selectAllStudentByClassName(
                this.current,
                this.size,
                this.classNameUp
              );
            } else {
              this.$message({
                message: `删除失败`,
                type: "error",
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
    //修改学生
    editStudentById(Source) {
      editStudentById(Source).then((req) => {
        if (req.data.data & req.data.status) {
          this.$message({
            message: "修改学生成功",
            type: "success",
          });
          this.selectAllStudentByClassName(
            this.current,
            this.size,
            this.classNameUp
          );
          this.editdialogFormVisible = false;
        } else {
          this.$message({
            message: "修改学生失败",
            type: "error",
          });
        }
      });
    },
    //修改按钮事件
    editStudent(Source) {
      this.editForm = Source;
      this.editdialogFormVisible = true;
    },
    //查询提示
    querySearch(queryString, cb) {
      var restaurants = this.className;
      // 解决element建议搜索框无法显示内容的数据处理
      for (var i = 0; i < restaurants.length; i++) {
        restaurants[i].value = restaurants[i].className;
      }
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (
          restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) ===
          0
        );
      };
    },
    // 分页
    handleCurrentChange(val) {
      this.current = val;
      this.selectAllStudentByClassName(
        this.current,
        this.size,
        this.formInline.className
      );
    },
  },
};
</script>

<style>
</style>