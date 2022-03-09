<template>
  <el-container id="main">
    <el-header height="40%">
      <el-form :inline="true" :model="formData" class="demo-form-inline">
        <el-form-item>
          <el-input
            v-model="formData.materialCategory"
            placeholder="类别"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            @click="selectAllByClass(current, size, formData.materialCategory)"
            >查询</el-button
          >
        </el-form-item>
        <el-form-item
          ><el-button type="success" @click="adddialogFormVisible = true"
            >入库</el-button
          ></el-form-item
        >
        <el-progress
          :text-inside="true"
          :stroke-width="27"
          :percentage="tage"
          :color="colors"
        ></el-progress>
      </el-form>
    </el-header>
    <el-main>
      <el-table :data="tableData" height="500" id="table">
        <el-table-column prop="id" label="编号" width="80" fixed="left">
        </el-table-column>
        <el-table-column prop="materialName" label="物资名" width="180">
        </el-table-column>
        <el-table-column prop="materialCategory" label="物资类别" width="180">
        </el-table-column>
        <el-table-column prop="materialPrice" label="物资价格" width="180">
        </el-table-column>
        <el-table-column prop="materialAddress" label="来源地址" width="200">
        </el-table-column>
        <el-table-column prop="newInTime" label="入库时间" width="200">
        </el-table-column>
        <el-table-column prop="newOutTime" label="出库时间" width="200">
        </el-table-column>
        <el-table-column
          prop="materialStatus"
          label="物资状态"
          width="100"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.materialStatus === 0 ? 'success' : 'danger'"
              disable-transitions
              >{{ scope.row.materialStatus == 0 ? "未出库" : "已出库" }}</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column label="操作" width="170" fixed="right">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="editClick(scope.row)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-s-unfold"
              circle
              @click="deleteMaterial(scope.row)"
            ></el-button>
            <el-button
              type="success"
              icon="el-icon-s-fold"
              circle
              @click="updateStatus(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
    <!-- 分页插件 -->
    <el-footer>
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage3"
        :page-size="size"
        layout="prev, pager, next, jumper"
        :total="pageSize"
      >
      </el-pagination>
    </el-footer>
    <!-- 修改 -->
    <el-dialog title="修改物资信息" :visible.sync="editdialogFormVisible">
      <el-form :model="editform">
        <h4 class="title">注意:你选择了{{ editform.id }}号物资</h4>
        <el-form-item label="物资名称" :label-width="formLabelWidth">
          <el-input
            v-model="editform.materialName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="物资类别" :label-width="formLabelWidth">
          <el-input
            v-model="editform.materialCategory"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="物资价格" :label-width="formLabelWidth">
          <el-input
            v-model="editform.materialPrice"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="来源地址" :label-width="formLabelWidth">
          <el-input
            v-model="editform.materialAddress"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editdialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editMaterial(editform)"
          >确 定</el-button
        >
      </div>
    </el-dialog>
    <!-- 添加 -->
    <el-dialog title="添加物资" :visible.sync="adddialogFormVisible">
      <el-form :model="addform">
        <el-form-item label="物资名称" :label-width="formLabelWidth">
          <el-input
            v-model="addform.materialName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="物资类别" :label-width="formLabelWidth">
          <el-input
            v-model="addform.materialCategory"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="物资价格" :label-width="formLabelWidth">
          <el-input
            v-model="addform.materialPrice"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="物资来源" :label-width="formLabelWidth">
          <el-input
            v-model="addform.materialAddress"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="adddialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addMaterial(addform)"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </el-container>
</template>

<script>
import {
  selectAllByClass,
  editMaterial,
  deleteMaterial,
  addMaterial,
  updateStatus,
} from "../api/material";
export default {
  data() {
    return {
      //历史搜索
      topData: "",
      //百分比
      tage: 0,
      //表格数据
      tableData: [],
      //页码
      current: 1,
      //每页显示数
      size: 10,
      //总数
      pageSize: 0,
      //进度条颜色
      colors: [
        { color: "#f56c6c", tage: 20 },
        { color: "#e6a23c", tage: 40 },
        { color: "#5cb87a", tage: 60 },
        { color: "#9fee00", tage: 80 },
        { color: "#00cc00", tage: 100 },
      ],
      //查询数据
      formData: {
        //入库时间
        inTime: "",
        //入库时间(新)
        newInTime: "",
        //出库时间(新)
        newOutTime: "",
        //出库时间
        outTime: "",
        //物资类别
        materialCategory: "",
      },
      //编辑数据
      editform: {},
      //添加数据
      addform: {},
      //编辑框显示
      editdialogFormVisible: false,
      //添加框显示
      adddialogFormVisible: false,
    };
  },
  created() {
    this.selectAllByClass(this.current, this.size, "");
  },
  methods: {
    //根据类别查询
    selectAllByClass(current, size, materialCategory) {
      selectAllByClass(current, size, materialCategory).then((req) => {
        this.pageSize = req.data.size;
        this.tableData = req.data.data;
        this.editDate();
        this.tage = (100 - (req.data.outSize / this.pageSize) * 100).toFixed(2); //获取出库百分比
        console.log(this.tage);
        if (this.topData != materialCategory) {
          if (req.data.status & (req.data.data != null)) {
            this.$message({
              message: `已查询到${this.pageSize}条数据`,
              type: "success",
              showClose: true,
            });
          } else {
            this.$message({
              message: `查询失败`,
              type: "error",
              showClose: true,
            });
          }
        }
        this.topData = materialCategory;
      });
    },
    //修改
    editMaterial(Source) {
      editMaterial(Source).then((req) => {
        if (req.data.data & req.data.status) {
          this.$message({
            message: "修改成功",
            type: "success",
            showClose: true,
          });
          this.editdialogFormVisible = false;
        } else {
          this.$message({
            message: "修改失败",
            type: "error",
            showClose: true,
          });
        }
        this.selectAllByClass(
          this.current,
          this.size,
          this.formData.materialCategory
        );
      });
    },
    //入库
    addMaterial(Source) {
      addMaterial(Source).then((req) => {
        if (req.data.data & req.data.status) {
          this.$message({
            message: "入库成功",
            type: "success",
            showClose: true,
          });
          this.adddialogFormVisible = false;
        } else {
          this.$message({
            message: "入库失败",
            type: "error",
            showClose: true,
          });
        }
        this.selectAllByClass(
          this.current,
          this.size,
          this.formData.materialCategory
        );
      });
    },
    //出库
    deleteMaterial(Source) {
      this.$confirm(
        `此操作会将${Source.id}号物资移除仓库但仍能恢复, 是否继续?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          if (Source.materialStatus === 1) {
            this.$message({
              message: "该物资已出库!",
              type: "warning",
              showClose: true,
            });
          } else {
            deleteMaterial(Source).then((req) => {
              if (req.data.data & req.data.status) {
                this.$message({
                  message: "出库成功",
                  type: "success",
                  showClose: true,
                });
              } else {
                this.$message({
                  message: "出库失败",
                  type: "error",
                  showClose: true,
                });
              }
              this.selectAllByClass(
                this.current,
                this.size,
                this.formData.materialCategory
              );
            });
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消出库",
            showClose: true,
          });
        });
    },
    //恢复
    updateStatus(Source) {
      this.$confirm(
        `此操作会将${Source.id}号物资恢复至仓库, 是否继续?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).then(() => {
        if (Source.materialStatus === 0) {
          this.$message({
            message: "该物资已在库中!",
            type: "warning",
            showClose: true,
          });
        } else {
          updateStatus(Source).then((req) => {
            if (req.data.data & req.data.status) {
              this.$message({
                message: "恢复成功",
                type: "success",
                showClose: true,
              });
            } else {
              this.$message({
                message: "恢复失败",
                type: "error",
                showClose: true,
              });
            }
            this.selectAllByClass(
              this.current,
              this.size,
              this.formData.materialCategory
            );
          });
        }
      });
    },
    //修改事件
    editClick(Source) {
      this.editform = Source;
      this.editdialogFormVisible = true;
    },
    // 分页
    handleCurrentChange(val) {
      this.current = val;
      this.selectAllByClass(
        this.current,
        this.size,
        this.formData.materialCategory
      );
    },
    //时间处理函数
    getDate(data) {
      var arr = data.split("T");
      var day = arr[0];
      var time = arr[1].split(".")[0];
      return day + " " + time;
    },
    //数据初始化函数
    editDate() {
      var arr = this.tableData;
      for (let index = 0; index < arr.length; index++) {
        arr[index].newInTime = this.getDate(arr[index].inTime); //转换入库时间
        if (arr[index].materialStatus === 1) {
          //已出库则显示
          arr[index].newOutTime = this.getDate(arr[index].outTime); //转换出库时间
        } else {
          arr[index].newOutTime = "-----------------------";
        }
      }
      this.tableData = arr;
    },
  },
};
</script>

<style>
#main {
  width: 100%;
  height: 100%;
  margin: 0 auto;
  text-align: center;
}
#table {
  width: 100%;
  height: 100%;
}
#table th,
#table tr td {
  text-align: center;
}
.title {
  color: red;
}
</style>