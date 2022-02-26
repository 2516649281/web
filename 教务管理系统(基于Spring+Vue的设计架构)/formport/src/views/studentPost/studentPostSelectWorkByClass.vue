<template>
  <el-container>
    <el-header> </el-header>
    <el-main>
      <el-table :data="tableData" height="300">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="作业要求:">
                <span
                  ><code>{{ props.row.workContent }}</code></span
                >
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="作业编号" prop="workId"> </el-table-column>
        <el-table-column label="作业名称" prop="workName"></el-table-column>
        <el-table-column
          label="布置人"
          prop="teacherName"
        ></el-table-column></el-table
    ></el-main>
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
  </el-container>
</template>

<script>
import { selectWorkByClass } from "../../api/student";
export default {
  data() {
    return {
      //页码
      current: 1,
      //每页显示数
      size: 5,
      //查询总数
      pageSize: "",
      //表格数据
      tableData: [],
      //登录页数据
      loginFrom: {},
    };
  },
  created() {
    this.getLoginSource();
    this.selectWorkByClass(this.current, this.size, this.loginFrom.className);
  },
  methods: {
    //学生端查看当前所有班级作业
    selectWorkByClass(current, size, className) {
      selectWorkByClass(current, size, className).then((res) => {
        this.pageSize = res.data.size;
        this.tableData = res.data.data;
      });
    },
    //获取中间页发送的数据
    getLoginSource() {
      this.loginFrom = this.$route.params.login;
    }, // 分页
    handleCurrentChange(val) {
      this.current = val;
      this.selectWorkByClass(this.current, this.size, this.loginFrom.className);
    },
  },
};
</script>

<style>
</style>