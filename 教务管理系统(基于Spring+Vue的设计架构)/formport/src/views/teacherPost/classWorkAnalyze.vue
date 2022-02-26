//教师端可视化分析页面
//时间:2022/2/23
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
    </el-header>
    <el-main>
      <div class="AnalyzeClass">
        <el-progress
          type="dashboard"
          :percentage="percentage"
          :color="colors"
        ></el-progress>
        <p>班级完成进度</p>
      </div>
      <div class="AnalyzeSource">
        <p>
          <el-progress
            :percentage="SourceA"
            color="#5cb87a"
            :text-inside="true"
            stroke-width="20"
          ></el-progress
          >80分以上
        </p>
        <p>
          <el-progress
            :percentage="SourceB"
            :text-inside="true"
            stroke-width="20"
          ></el-progress
          >60-79
        </p>
        <p>
          <el-progress
            :percentage="SourceC"
            color="#f56c6c"
            :text-inside="true"
            stroke-width="20"
          ></el-progress
          >小于60
        </p>
        <p>成绩分布如上</p>
      </div>
    </el-main>
    <el-footer></el-footer>
  </el-container>
</template>

<script>
import { selectWorkOKByClassName, selectClassName } from "../../api/teacher";
export default {
  data() {
    return {
      //班级提示文本
      className: [],
      //页码
      current: 1,
      //每页显示数
      size: 800,
      //班级完成进度
      percentage: 0,
      //表单提交
      formInline: {
        //班级名
        className: "",
        //作业id
        workId: "",
      },
      //颜色
      colors: [
        { color: "#f56c6c", percentage: 20 },
        { color: "#e6a23c", percentage: 40 },
        { color: "#5cb87a", percentage: 60 },
        { color: "#1989fa", percentage: 80 },
        { color: "#6f7ad3", percentage: 100 },
      ],
      SourceA: 0,
      SourceB: 0,
      SourceC: 0,
    };
  },
  created() {
    this.selectClassName(this.current, this.size);
  },
  methods: {
    //教师端查询班级作业完成情况
    selectWorkOKByClassName(current, size, className, workId) {
      selectWorkOKByClassName(current, size, className, workId).then((res) => {
        var table = new Array();
        table = res.data.data;
        this.pageSize = res.data.size;
        var size = 0;
        var sizeA = 0;
        var sizeB = 0;
        var sizeC = 0; // console.log(table);
        for (let i = 0; i < table.length; i++) {
          if (table[i].studentWorks.length > 0) {
            size++;
            if (
              //80以上
              (table[i].studentWorks[0].studentSource >= 80) &
              (table[i].studentWorks[0].studentSource <= 100)
            ) {
              sizeA++;
            } else if (
              //60-80之间
              (table[i].studentWorks[0].studentSource >= 60) &
              (table[i].studentWorks[0].studentSource < 70)
            ) {
              sizeB++; //60以下
            } else if (table[i].studentWorks[0].studentSource < 60) {
              sizeC++;
            }
          } else {
            size = 0;
            sizeA = 0;
            sizeB = 0;
            sizeC = 0;
          }
        }
        this.percentage = ((size / this.pageSize) * 100).toFixed(2); //完成率
        if (size != 0) {
          //当完成总数不为0时正常计算，如果为0，为避免分母为0，必须先判断
          this.SourceA = ((sizeA / size) * 100).toFixed(2); //80分以上
          this.SourceB = ((sizeB / size) * 100).toFixed(2); //60-70
          this.SourceC = ((sizeC / size) * 100).toFixed(2); //60分以下
        } else {
          this.SourceA = 0; //80分以上
          this.SourceB = 0; //60-70
          this.SourceC = 0; //60分以下
        }
      });
    },
    //查询所有班级
    selectClassName(current, size) {
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
  },
};
</script>
<style>
.AnalyzeClass,
.AnalyzeSource {
  width: 80%;
  height: 100%;
  margin: 0 auto;
  /* border: 1px black solid; */
  font-size: 15px;
}
</style>