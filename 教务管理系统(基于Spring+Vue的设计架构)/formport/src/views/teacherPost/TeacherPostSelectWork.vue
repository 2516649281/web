// 作者：春风能解释
// 时间：2022/2/20 22:30
//  教师端根据班级名查询所有已布置(包含学生端未提交的作业)的作业
<template>
  <el-contianer width="100%">
    <!-- 查询表单 -->
    <el-header>
      <p>请教师查看指定班级的所有科目作业</p>
      <p>
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
                selectWorkByClassName(current, size, formInline.className)
              "
              >查询</el-button
            >
            <el-button type="success" @click="addWorkAndOpenForm"
              >添加作业</el-button
            ></el-form-item
          ></el-form
        >
      </p>
    </el-header>
    <!-- 内容主要区域 -->
    <el-main>
      <el-table :data="tableData" height="500" style="width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <p>
                <el-form-item label="作业要求:">
                  <span>{{ props.row.workContent }}</span>
                </el-form-item>
              </p>
              <p>
                <el-form-item label="布置人:">
                  <span>{{ props.row.teacherName }}</span>
                </el-form-item>
              </p>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          prop="workId"
          label="作业编号(如批改作业请牢记此号码)"
          align="center"
        ></el-table-column>
        <el-table-column prop="workName" label="作业名称" align="center">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              icon="el-icon-edit"
              size="mini"
              @click="editWorkAndOpenForm(scope.row)"
            ></el-button>
            <el-button
              icon="el-icon-delete"
              size="mini"
              type="danger"
              @click="deleteWork(scope.row.workId)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 添加弹出表单 -->
      <el-dialog title="添加作业" :visible.sync="adddialogFormVisible">
        <el-form :model="addform">
          <el-form-item
            label="你要给哪个班级布置作业"
            :label-width="formLabelWidth"
          >
            <el-autocomplete
              :fetch-suggestions="querySearch"
              v-model="formInline.className"
            ></el-autocomplete>
          </el-form-item>
          <el-form-item label="你的作业名称" :label-width="formLabelWidth">
            <el-input v-model="addform.workName"></el-input>
          </el-form-item>
          <el-form-item label="你的教师编号" :label-width="formLabelWidth">
            <el-input v-model="addform.teacherId"></el-input>
          </el-form-item>
          <el-form-item label="请为作业指定要求" :label-width="formLabelWidth">
            <el-input
              v-model="addform.workContent"
              placeholder="可填"
            ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="adddialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="addWork(addform)">确 定</el-button>
        </div>
      </el-dialog>
      <el-dialog title="修改作业" :visible.sync="editdialogFormVisible">
        <p>注意:你选择了{{ editform.workId }}号作业</p>
        <el-form :model="editform">
          <el-form-item label="新班级" :label-width="formLabelWidth">
            <el-input
              v-model="editform.className"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="新作业名称" :label-width="formLabelWidth">
            <el-input v-model="editform.workName"></el-input>
          </el-form-item>
          <el-form-item label="新教师编号" :label-width="formLabelWidth">
            <el-input v-model="editform.teacherId"></el-input>
          </el-form-item>
          <el-form-item label="新要求" :label-width="formLabelWidth">
            <el-input
              v-model="editform.workContent"
              placeholder="可填"
            ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="editdialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="editWork(editform)"
            >确 定</el-button
          >
        </div>
      </el-dialog>
    </el-main>
    <!-- 分页插件 -->
    <el-footer>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :page-size="size"
        layout="prev, pager, next, jumper"
        :total="pageSize"
      >
      </el-pagination>
    </el-footer>
  </el-contianer>
</template>

<script>
import {
  selectWorkByClassName,
  selectClassName,
  addWork,
  deleteWork,
  editWork,
} from "../../api/teacher";
export default {
  data() {
    return {
      //响应状态
      status: false,
      //页码
      current: 1,
      //每页显示数
      size: 8,
      //查询结果数量
      pageSize: 0,
      //表格数据
      tableData: [],
      //班级提示文本
      className: [],
      //上一次的输入
      classNameUp: "",
      //表单数据对象
      formInline: {
        className: "",
      },
      //添加窗状态
      adddialogFormVisible: false,
      //修改窗状态
      editdialogFormVisible: false,
      //添加的表单数据
      addform: {
        //班级名
        className: "",
        //作业名
        workName: "",
        //教师编号
        teacherId: "",
        //作业要求
        workContent: "",
      },
      //修改的表单数据
      editform: {
        workId: 0,
        //班级名
        className: "",
        //作业名
        workName: "",
        //教师编号
        teacherId: "",
        //作业要求
        workContent: "",
      },
      //删除作业
      delfrom: {
        workId: 0,
      },
      //登录数据
      loginFrom: {},
    };
  },
  created() {
    this.getLoginSource();
    this.selectAllClassName(1, 200);
  },
  methods: {
    //根据班级名查询学生的作业信息
    selectWorkByClassName(current, size, className) {
      selectWorkByClassName(current, size, className).then((res) => {
        try {
          this.tableData = res.data.data[0].classWorks;
        } catch (error) {
          this.tableData = null;
          this.$message({
            message: `班级输入有误或未给该班级布置作业`,
            type: "warning",
          });
          return;
        }
        this.status = res.data.status;
        this.pageSize = res.data.size;
        if (!(this.classNameUp === className)) {
          //比较上一次的输入
          if (this.status) {
            this.$message({
              message: `已查询到${this.pageSize}条数据`,
              type: "success",
            });
          } else {
            this.$message({
              message: "查询失败",
              type: "error",
            });
          }
          this.classNameUp = className; //赋值
        }
      });
    },
    //添加作业
    addWork(work) {
      addWork(work).then((req) => {
        if (req.data.data & req.data.status) {
          this.$message({
            message: `添加作业成功`,
            type: "success",
          });
          this.selectWorkByClassName(this.current, this.size, this.classNameUp);
          this.adddialogFormVisible = false;
        } else {
          this.$message({
            message: `添加作业失败`,
            type: "error",
          });
        }
      });
    },

    //删除作业
    deleteWork(workId) {
      this.delfrom.workId = workId;
      this.$confirm("此操作将永久删除该作业, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteWork(this.delfrom).then((req) => {
            if (req.data.data & req.data.status) {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              this.selectWorkByClassName(
                this.current,
                this.size,
                this.classNameUp
              );
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

    //修改作业
    editWork(source) {
      editWork(source).then((req) => {
        if (req.data.data & req.data.status) {
          this.$message({
            type: "success",
            message: "修改成功!",
          });
          this.selectWorkByClassName(this.current, this.size, this.classNameUp);
        } else {
          this.$message({
            type: "error",
            message: "修改失败!",
          });
        }
      });
      this.editdialogFormVisible = false;
    },

    //用于编辑操作
    editWorkAndOpenForm(source) {
      this.editform = source; //将数据装入对象
      this.editform.className = this.classNameUp;
      this.editdialogFormVisible = true;
    },

    //用于添加操作
    addWorkAndOpenForm() {
      this.addform.className = this.classNameUp;
      this.addform.teacherId = this.loginFrom.userIdentityId;
      this.adddialogFormVisible = true;
    },

    //查询所有班级用于输入框提示
    selectAllClassName(current, size) {
      selectClassName(current, size).then((res) => {
        this.className = res.data.data;
      });
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
      this.pageYN++;
      this.current = val;
      this.selectWorkByClassName(
        this.current,
        this.size,
        this.formInline.className
      );
    },
    //获取中间页发来的数据
    getLoginSource() {
      this.loginFrom = this.$route.params.login;
      console.log(this.loginFrom);
    },
  },
};
</script>
<style>
</style>