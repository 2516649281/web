//作者:春风能解释
//时间:2022/2/21 9:00
//教师端检查该班级所有学生作业完成情况
<template>
  <el-contianer width="100%">
    <!-- 查询表单 -->
    <el-header>
      <p>请教师检查当前班级作业的完成情况</p>
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
          <el-form-item prop="workId">
            <el-input
              class="inline-input"
              placeholder="请输入作业编号"
              v-model="formInline.workId"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              @click="
                selectWorkOKByClassName(
                  current,
                  size,
                  formInline.className,
                  formInline.workId
                )
              "
              >查询</el-button
            >
          </el-form-item>
        </el-form>
      </p>
    </el-header>
    <!-- 表格显示区域 -->
    <el-main>
      <el-table :data="tableData" style="width: 100%" height="500">
        <el-table-column type="expand" width="25%">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <p>
                <el-form-item label="学生作业编号:">
                  <span>{{ props.row.studentWorks[0].studentWorkId }}</span>
                </el-form-item>
              </p>
              <p>
                <el-form-item label="学生作业:">
                  <span>{{ props.row.studentWorks[0].studentWork }}</span>
                </el-form-item>
              </p>
              <p>
                <el-form-item label="学生成绩:">
                  <span>{{ props.row.studentWorks[0].studentSource }}</span>
                </el-form-item>
                <el-form-item>
                  <el-button
                    size="mini"
                    @click="editConfig(props.row.studentWorks[0])"
                    icon="el-icon-edit"
                  ></el-button>
                </el-form-item>
              </p>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="学生编号" prop="studentId"> </el-table-column>
        <el-table-column label="学生姓名" prop="studentName"> </el-table-column>
        <el-table-column label="学生性别" prop="studentSex"> </el-table-column>
        <el-teble-column
          label="学生地址"
          prop="studentAddress"
        ></el-teble-column>
        <el-table-column
          prop="workSituation"
          label="完成情况"
          width="100"
          :filters="[
            { text: '已完成', value: '已完成' },
            { text: '未完成', value: '未完成' },
          ]"
          :filter-method="filterTag"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="
                scope.row.workSituation === '已完成' ? 'success' : 'primary'
              "
              disable-transitions
              >{{ scope.row.workSituation }}</el-tag
            >
          </template>
        </el-table-column>
      </el-table>
      <!-- 批改框 -->
      <el-dialog title="批改作业" :visible.sync="editdialogFormVisible">
        <el-form :model="editSourceFrom">
          <p>注意:当前学生作业编号{{ editSourceFrom.studentWorkId }}</p>
          <el-form-item
            label="请输入你觉得合适的分数(可含小数点)"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="editSourceFrom.studentSource"
              autocomplete="off"
            ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="editdialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="editSource(editSourceFrom)"
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
        :current-page.sync="currentPage3"
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
  selectWorkOKByClassName,
  selectClassName,
  editSource,
} from "../../api/teacher";
export default {
  data() {
    return {
      //查询结果数量
      pageSize: 0,
      //表格填充数据
      tableData: [],
      //响应状态
      status: false,
      //上一次的输入
      classNameUp: "",
      //表单提交
      formInline: {
        //班级名
        className: "",
        //作业id
        workId: "",
      },
      //页码
      current: 1,
      //每页显示数
      size: 8,
      //班级提示文本
      className: [],
      //批改作业数据
      editSourceFrom: {
        studentWorkId: "",
        studentSource: "",
      },
      //编辑框状态
      editdialogFormVisible: false,
      //登录数据
      loginFrom: {},
    };
  },
  created() {
    this.getLoginSource();
    this.selectAllClassName(1, 200);
  },
  methods: {
    //教师端查询班级作业完成情况
    selectWorkOKByClassName(current, size, className, workId) {
      selectWorkOKByClassName(current, size, className, workId).then((res) => {
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

    //批改作业
    editSource(Source) {
      console.log(Source);
      editSource(Source).then((req) => {
        if (req.data.data) {
          this.$message({
            message: "批改作业成功",
            type: "success",
          });
          this.selectWorkOKByClassName(
            this.current,
            this.size,
            this.classNameUp
          );
        } else {
          this.$message({
            message: "批改作业失败",
            type: "error",
          });
        }
        this.editdialogFormVisible = false;
      });
    },

    //编辑操作
    editConfig(Source) {
      this.editSourceFrom = Source; //传入数据
      this.editdialogFormVisible = true;
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
      this.current = val;
      this.selectWorkOKByClassName(
        this.current,
        this.size,
        this.formInline.className,
        this.formInline.workId
      );
    },
    //标签筛选
    filterTag(value, row) {
      return row.workSituation === value;
    },
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    }, //获取中间页发送的数据
    getLoginSource() {
      this.loginFrom = this.$route.params.login;
    },
  },
};
</script>

<style>
</style>