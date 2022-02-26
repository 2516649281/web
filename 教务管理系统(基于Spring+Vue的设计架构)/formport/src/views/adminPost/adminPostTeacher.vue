//管理端管理教师
//时间:2022/2/24 13:32
<template>
  <el-container>
    <el-header
      ><el-form :inline="true" ref="formInline" class="demo-form-inline">
        <el-form-item prop="className">
          <el-input
            class="inline-input"
            placeholder="请输入教师地址中的关键字"
            v-model="formInline.teacherAddress"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            @click="
              selectAllTeacherByAddress(
                current,
                size,
                formInline.teacherAddress
              )
            "
            >查询</el-button
          >
          <el-button type="success" @click="adddialogFormVisible = true"
            >添加教师</el-button
          ></el-form-item
        ></el-form
      ></el-header
    >
    <el-main>
      <el-table :data="tableData" height="500"
        ><el-table-column label="教师编号" prop="teacherId"></el-table-column>
        <el-table-column label="教师姓名" prop="teacherName"></el-table-column>
        <el-table-column label="教师年龄" prop="teacherAge"></el-table-column>
        <el-table-column label="教师性别" prop="teacherSex"></el-table-column>
        <el-table-column
          label="教师家庭地址"
          prop="teacherAddress"
        ></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              icon="el-icon-edit"
              size="mini"
              @click="editTeacher(scope.row)"
              circle
            ></el-button>
            <el-button
              icon="el-icon-delete"
              size="mini"
              type="danger"
              @click="delTeacherById(scope.row.teacherId)"
              circle
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
    <el-footer
      ><el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage3"
        :page-size="size"
        layout="prev, pager, next, jumper"
        :total="pageSize"
      >
      </el-pagination
    ></el-footer>
    <!-- 添加框 -->
    <el-dialog title="添加教师" :visible.sync="adddialogFormVisible">
      <el-form :model="addForm">
        <el-form-item label="请输入教师姓名:" :label-width="formLabelWidth">
          <el-input v-model="addForm.teacherName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请输入教师年龄:" :label-width="formLabelWidth">
          <el-input v-model="addForm.teacherAge" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请选择教师性别:" :label-width="formLabelWidth">
          <el-radio v-model="addForm.teacherSex" label="男"
            ><i class="el-icon-male"></i
          ></el-radio>
          <el-radio v-model="addForm.teacherSex" label="女"
            ><i class="el-icon-female"></i
          ></el-radio>
        </el-form-item>
        <el-form-item label="请输入教师家庭地址:" :label-width="formLabelWidth">
          <el-input
            v-model="addForm.teacherAddress"
            autocomplete="off"
          ></el-input> </el-form-item
      ></el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="adddialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addTeacher(addForm)">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 修改框 -->
    <el-dialog title="修改教师" :visible.sync="editdialogFormVisible">
      <el-form :model="editForm">
        <el-form-item label="请输入教师新姓名:" :label-width="formLabelWidth">
          <el-input
            v-model="editForm.teacherName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="请输入教师新年龄:" :label-width="formLabelWidth">
          <el-input v-model="editForm.teacherAge" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请选择教师新性别:" :label-width="formLabelWidth">
          <el-radio v-model="editForm.teacherSex" label="男"
            ><i class="el-icon-male"></i
          ></el-radio>
          <el-radio v-model="editForm.teacherSex" label="女"
            ><i class="el-icon-female"></i
          ></el-radio>
        </el-form-item>
        <el-form-item
          label="请输入教师新家庭地址:"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="editForm.teacherAddress"
            autocomplete="off"
          ></el-input> </el-form-item
      ></el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editdialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editTeacherById(editForm)"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </el-container>
</template>

<script>
import {
  selectAllTeacher,
  selectAllTeacherByAddress,
  addTeacher,
  delTeacherById,
  editTeacherById,
} from "../../api/admin";
export default {
  data() {
    return {
      //上一次的输入
      addressUp: "",
      //页码
      current: 1,
      //每页显示数
      size: 10,
      //表格数据
      tableData: [],
      //查询总数
      pageSize: "",
      //查询数据
      formInline: {
        //教师地址
        teacherAddress: "",
      },
      //添加数据
      addForm: {
        //教师姓名
        teacherName: "",
        //教师年龄
        teacherAge: "",
        //教师性别
        teacherSex: "",
        //教师地址
        teacherAddress: "",
      },
      //编辑数据
      editForm: {
        //教师编号
        teacherId: "",
        //教师姓名
        teacherName: "",
        //教师年龄
        teacherAge: "",
        //教师性别
        teacherSex: "",
        //教师地址
        teacherAddress: "",
      },
      //删除数据
      delForm: {
        //教师编号
        teacherId: "",
      },
      //添加对话框
      adddialogFormVisible: false,
      //编辑对话框
      editdialogFormVisible: false,
    };
  },
  created() {
    this.selectAllTeacher(this.current, this.size);
  },
  methods: {
    //查询所有教师
    selectAllTeacher(current, size) {
      selectAllTeacher(current, size).then((res) => {
        this.tableData = res.data.data;
        this.pageSize = res.data.size;
      });
    },
    //根据地址模糊查询
    selectAllTeacherByAddress(current, size, address) {
      selectAllTeacherByAddress(current, size, address).then((req) => {
        this.tableData = req.data.data;
        this.pageSize = req.data.size;
        if (!(address === this.addressUp)) {
          if (req.data.status) {
            this.$message({
              message: `已查询到${this.pageSize}条数据`,
              type: "success",
            });
          } else {
            this.$message({
              message: `查询失败，请检查地址字段是否输入正确`,
              type: "error",
            });
          }
        }
        this.addressUp = address;
      });
    },
    //添加教师
    addTeacher(Source) {
      addTeacher(Source).then((req) => {
        if (req.data.data & req.data.status) {
          this.$message({
            message: `添加教师成功`,
            type: "success",
          });
          this.selectAllTeacherByAddress(
            this.current,
            this.size,
            this.addressUp
          );
          this.adddialogFormVisible = false;
        } else {
          this.$message({
            message: `添加教师失败`,
            type: "error",
          });
        }
      });
    },
    //修改教师
    editTeacherById(Source) {
      editTeacherById(Source).then((req) => {
        if (req.data.data & req.data.status) {
          this.$message({
            message: `修改教师成功`,
            type: "success",
          });
          this.selectAllTeacherByAddress(
            this.current,
            this.size,
            this.addressUp
          );
          this.editdialogFormVisible = false;
        } else {
          this.$message({
            message: `修改教师失败`,
            type: "error",
          });
        }
      });
    },
    //删除教师
    delTeacherById(teacherId) {
      this.delForm.teacherId = teacherId;
      this.$confirm("此操作将永久删除该教师, 是否继续?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          delTeacherById(this.delForm).then((req) => {
            if (req.data.data & req.data.status) {
              this.$message({
                message: `删除教师成功`,
                type: "success",
              });
              this.selectAllTeacherByAddress(
                this.current,
                this.size,
                this.addressUp
              );
            } else {
              this.$message({
                message: `删除教师失败`,
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
    //修改按钮事件
    editTeacher(Source) {
      this.editForm = Source;
      this.editdialogFormVisible = true;
    },
    // 分页
    handleCurrentChange(val) {
      this.current = val;
      this.selectAllTeacherByAddress(
        this.current,
        this.size,
        this.formInline.teacherAddress
      );
    },
  },
};
</script>

<style>
</style>