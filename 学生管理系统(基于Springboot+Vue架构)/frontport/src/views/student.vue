<template>
  <div>
    <!--查询模块-->
    <el-header>
      <el-row>
        <el-col :span="12">
          <el-button @click="getPage">手动刷新</el-button>
        </el-col>
        <el-col :span="12">
          <el-form :inline="true" :model="StudentForm">
            <el-form-item>
              <el-autocomplete
                  placeholder="请输入正确的班级名称"
                  v-model="StudentForm.className"
                  :fetch-suggestions="querySearch"
                  clearable>
              </el-autocomplete>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="selectByClassName(1,StudentForm)">查询</el-button>
            </el-form-item>
            <el-button type="primary" @click="dialogVisibleAdd = true">添加学生</el-button>
          </el-form>
        </el-col>
      </el-row>
    </el-header>

    <!--    主要展示模块-->
    <el-main>
      <!--主表-->
      <el-row>
        <el-table
            v-loading="loading"
            :data="table">
          <el-table-column label="编号" prop="id">
          </el-table-column>
          <el-table-column label="学生姓名" prop="studentName"></el-table-column>
          <el-table-column label="学生班级" prop="className"></el-table-column>
          <el-table-column label="学生年龄" prop="studentAge"></el-table-column>
          <el-table-column label="学生性别" prop="studentSex"></el-table-column>
          <el-table-column label="学生地址" prop="studentAddress"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  @click="dialogVisibleEdit=true;StudentForm=scope.row"><i class="el-icon-edit"></i>
              </el-button>
              <el-button
                  size="mini"
                  type="danger"
                  @click="delStudent(scope.row.id)"><i class="el-icon-delete"></i>
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <!--添加学生对话框-->
      <el-dialog
          title="添加学生"
          :visible.sync="dialogVisibleAdd"
          width="30%">
        <el-form :model="addForm">
          <p>
            <el-input v-model="addForm.studentName" placeholder="请输入学生姓名" slot="reference" @blur=""></el-input>
          </p>
          <p>
            <el-input v-model="addForm.className" placeholder="请输入学生班级"></el-input>
          </p>
          <p>
            <el-input v-model="addForm.studentAge" placeholder="请输入学生年龄"></el-input>
          </p>
          <p>
            <el-input v-model="addForm.studentSex" placeholder="请输入学生性别"></el-input>
          </p>
          <p>
            <el-input v-model="addForm.studentAddress" placeholder="请输入学生地址"></el-input>
          </p>
          <p>
          <span slot="footer" class="dialog-footer">
            <el-row>
              <el-col span="12">
                <el-button @click="closeDialog">取 消</el-button>
              </el-col>
              <el-col span="12">
                <el-button type="primary" @click="addStudent(addForm)">提 交</el-button>
              </el-col>
            </el-row>
          </span>
          </p>
        </el-form>
      </el-dialog>

      <!--修改学生对话框-->
      <el-dialog
          title="修改学生"
          :visible.sync="dialogVisibleEdit"
          width="30%">
        <h4>你选择了{{ StudentForm.id }}号学生</h4>
        <el-form :model="StudentForm">
          <p>
            <span> <el-input v-model="StudentForm.studentName">{{ StudentForm.studentName }}</el-input></span>
          </p>
          <p>
            <el-input v-model="StudentForm.className">{{ StudentForm.studentName }}</el-input>
          </p>
          <p>
            <el-input v-model="StudentForm.studentAge">{{ StudentForm.studentName }}</el-input>
          </p>
          <p>
            <el-input v-model="StudentForm.studentSex">{{ StudentForm.studentName }}</el-input>
          </p>
          <p>
            <el-input v-model="StudentForm.studentAddress">{{ StudentForm.studentName }}</el-input>
          </p>
          <p>
          <span slot="footer" class="dialog-footer">
            <el-row>
              <el-col span="12">
                <el-button @click="closeDialog">取 消</el-button>
              </el-col>
              <el-col span="12">
                <el-button type="primary" @click="editStudent(StudentForm)">提 交</el-button>
              </el-col>
            </el-row></span>
          </p>
        </el-form>
      </el-dialog>
    </el-main>

    <!--    底部分页模块-->
    <el-footer>
      <!--分页查询例外操作-->
      <div v-if="!dialogVisiblePage">
        <el-pagination
            @current-change="getAll"
            :page-size="size"
            :pager-count="11"
            layout="prev, pager,total, next ,jumper"
            :total="table[0].size">
        </el-pagination>
      </div>
      <div v-else>
        <el-pagination
            @current-change="onSubmit"
            :page-size="size"
            :pager-count="11"
            layout="prev, pager,total, next ,jumper"
            :total="table[0].size">
        </el-pagination>
      </div>
    </el-footer>
  </div>
</template>
<script>
//调用api
import {addStudent, delStudent, selectAllStudentPage, editStudent} from '@/api/student';
import {selectAllClass} from '@/api/class'

export default {
  //预定义变量
  data() {
    return {
      //状态值
      status: false,
      //弹窗执行次数
      FromNum: 0,
      //消息框执行时间
      MessageTime: 1000,
      //表格数据
      table: [],
      //每页显示数
      size: 9,
      //条件查询数据
      StudentForm: {
        //班级名称
        className: ''
      },
      //班级名提示
      Class: [],
      restaurants: [],
      //添加数据
      addForm: {
        //id
        id: null,
        //学生姓名
        studentName: null,
        //班级名称
        className: null,
        //学生年龄
        studentAge: null,
        //学生性别
        studentSex: null,
        //学生地址
        studentAddress: null
      },
      //添加对话框
      dialogVisibleAdd: false,
      //编辑对话框
      dialogVisibleEdit: false,
      //查询时显示的分页插件
      dialogVisiblePage: false
    }
  },

  //预加载函数
  created() {
    this.getAll(1);
    this.selectAllClass();
  },

  //主要操作函数
  methods: {
    //分页所有查询学生
    getAll(current) {
      selectAllStudentPage(current, this.size, '').then(res => {
        this.table = res.data.data;
        this.status = res.data.status;
        if (!this.status) {
          this.$message({
            message: '数据库异常',
            type: 'error',
            duration: this.MessageTime
          })
        }
      })
    },

    //条件查询
    onSubmit(current, Source) {
      // //设置分页显示
      // this.dialogVisiblePage = true;
      // if (Source != null) {
      //   this.StudentForm = Source;
      // }
      selectAllStudentPage(current, this.size, Source.className).then(res => {
        this.status = res.data.status;
        this.table = res.data.data;
        //解决每次点击下一页跳出的弹窗警告
        if (this.FromNum === 0) {
          //判断是否存在数据
          if (this.table != null) {
            this.$message({
              message: '已查询到' + this.table[0].size + '条数据',
              type: 'success',
              duration: this.MessageTime
            })
          } else {
            //报错时显示
            if (!this.status) {
              this.$message({
                message: '没有该班级',
                type: 'error',
                duration: this.MessageTime
              })
              //班级无人时显示
            } else {
              this.$message({
                message: '抱歉!没有查到任何数据',
                type: 'warning',
                duration: this.MessageTime
              })
            }
          }
        }
        this.FromNum++;
      })
    },

    //点击查询按钮时触发的事件
    selectByClassName(current, Source) {
      //重置弹窗次数
      this.FromNum = 0;
      this.onSubmit(current, Source)
    },

    //增加学生
    addStudent(Source) {
      addStudent(Source).then(req => {
        if (req.data.data) {
          this.$message({
            message: '添加学生成功',
            type: 'success',
            duration: this.MessageTime
          })
          this.getAll(1);
        } else {
          if (!req.data.status) {
            this.$message({
              message: '数据库异常',
              type: 'error',
              duration: this.MessageTime
            })
          } else {
            this.$message({
              message: '添加学生失败',
              type: 'error',
              duration: this.MessageTime
            })
          }
        }
      })

    },

    //删除学生
    delStudent(index) {
      this.$confirm('此操作将永久删除该学生信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delStudent(index).then(req => {
          if (req.data.data) {
            this.$message({
              message: '已删除' + index + '号学生信息',
              type: 'success',
              duration: this.MessageTime
            })
            this.getAll(1)
          } else {
            if (!req.data.status) {
              this.$message({
                message: '数据库异常',
                type: 'error',
                duration: this.MessageTime
              })
            } else {
              this.$message({
                message: '删除学生失败',
                type: 'error',
                duration: this.MessageTime
              })
            }
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除',
            duration: this.MessageTime
          });
        });
      })
    },

    //编辑学生
    editStudent(Source) {
      editStudent(Source).then(req => {
        if (req.data.data) {
          this.$message({
            message: '修改学生成功',
            type: 'success',
            duration: this.MessageTime
          })
          this.getAll(1)
        } else {
          if (!req.data.status) {
            this.$message({
              message: '数据库异常',
              type: 'error',
              duration: this.MessageTime
            })
          } else {
            this.$message({
              message: '修改学生失败',
              type: 'error',
              duration: this.MessageTime
            })
          }
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消修改',
          duration: this.MessageTime
        });
      })
    },
    //查询全部按钮
    getPage() {
      this.dialogVisiblePage = false;
      this.getAll(1)
    },
    //关闭弹框的事件
    closeDialog() {
      if (this.dialogVisibleAdd) {
        this.dialogVisibleAdd = false
      } else if (this.dialogVisibleEdit) {
        this.dialogVisibleEdit = false
      } else if (this.dialogVisiblePage) {
        this.dialogVisiblePage = false
      }
      //解决点击编辑，查询数据消失的bug
      //this.onSubmit(1, this.StudentForm)
    },
    //查询所有班级
    selectAllClass() {
      selectAllClass().then(res => {
        this.Class = res.data.data
      })
    },
    //查询提示
    querySearch(queryString, cb) {
      var restaurants = this.Class;
      // 解决element建议搜索框无法显示内容的数据处理
      for (var i = 0; i < restaurants.length; i++) {
        restaurants[i].value = restaurants[i].className;
      }
      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
  },
}
</script>
<style>

</style>