//路由核心
import Vue from "vue";
import Router from "vue-router";
//登录
import login from "../views/login/login";
//判断用户身份
import userIdentity from "../views/login/userIdentity";
//注册
import register from "../views/login/register";
//教师端的主页
import teacherIndex from "../views/teacherPost/teacherPostIndex";
//教师端通过班级名查询所有作业，包含学生未完成的
import teacherSelectWork from "../views/teacherPost/teacherPostSelectWork";
//教师端痛过班级名查询所有已完成的作业信息
import teacherSelectOkWork from "../views/teacherPost/teacherPostSelectOkWork";
//教师可视化界面
import studentClassWorkAnalyze from "../views/teacherPost/classWorkAnalyze";
//学生端主页
import studentIndex from "../views/studentPost/studentPostIndex";
//学生端提交作业
import studentSelectWorkByName from "../views/studentPost/studentPostSelectWorkByName";
//学生端根据班级名接收作业
import studentSelectWorkByClass from "../views/studentPost/studentPostSelectWorkByClass";
//管理端主页
import adminIndex from "../views/adminPost/adminPostIndex";
//管理端学生管理
import adminStudent from "../views/adminPost/adminPostStudent";
//管理端教师管理
import adminTeacher from "../views/adminPost/adminPostTeacher";
//管理端用户管理
import adminUser from "../views/adminPost/adminPostUser";
//用户设置
import setting from "../views/public/userSetting";

Vue.use(Router);

const router = new Router({
  routes: [
    //登录
    {
      path: "/",
      name: "login",
      component: login
    },
    //注册
    {
      path: "/register",
      name: "register",
      component: register
    },
    //身份验证
    {
      path: "/userIdentity",
      name: "userIdentity",
      component: userIdentity
    },
    //教师端
    {
      path: "/teacher",
      name: "teacher",
      component: teacherIndex,
      redirect: { name: "teacherSelectWork" },
      //新增meta配置
      meta: {
        //需要登录后才能访问
        requiresAuth: true
      },
      children: [
        {
          path: "/selectAll",
          name: "teacherSelectWork",
          component: teacherSelectWork
        },
        {
          path: "/selectAllOk",
          name: "teacherSelectOkWork",
          component: teacherSelectOkWork
        },
        {
          path: "/teacherAnalyzeClassName",
          name: "teacherClassWorkAnalyze",
          component: studentClassWorkAnalyze
        }, //用户设置
        {
          path: "/teacherUserSetting",
          name: "teacherUserSetting",
          component: setting,
          meta: {
            //需要登录后才能访问
            requiresAuth: true
          }
        }
      ]
    },
    //学生端
    {
      path: "/student",
      name: "student",
      component: studentIndex,
      redirect: { name: "studentSelectWorkByName" },
      //新增meta配置
      meta: {
        //需要登录后才能访问
        requiresAuth: true
      },
      children: [
        {
          path: "/selectWorkByName",
          name: "studentSelectWorkByName",
          component: studentSelectWorkByName
        },
        {
          path: "/selectWorkByClass",
          name: "studentSelectWorkByCLass",
          component: studentSelectWorkByClass
        }, //用户设置
        {
          path: "/studentUserSetting",
          name: "studentUserSetting",
          component: setting,
          meta: {
            //需要登录后才能访问
            requiresAuth: true
          }
        }
      ]
    },
    //管理端
    {
      path: "/admin",
      name: "admin",
      component: adminIndex,
      redirect: { name: "adminStudent" },
      //新增meta配置
      meta: {
        //需要登录后才能访问
        requiresAuth: true
      },
      children: [
        {
          path: "/student",
          name: "adminStudent",
          component: adminStudent
        },
        {
          path: "/teacher",
          name: "adminTeacher",
          component: adminTeacher
        },
        {
          path: "/user",
          name: "adminUser",
          component: adminUser
        }, //用户设置
        {
          path: "/adminUserSetting",
          name: "adminUserSetting",
          component: setting,
          meta: {
            //需要登录后才能访问
            requiresAuth: true
          }
        }
      ]
    }
  ]
});

// //路由拦截
// router.beforeEach((to, from, next) => {
//   //判断是否是登录保护路由
//   if (to.matched.some(record => record.meta.requiresAuth)) {
//     //验证浏览器本地存储是否有token（或从vuex、Cookie、localStorage中获取，依据自己的存储），用于判断是否登录
//     if (!sessionStorage.token) {
//       //没有token，表示未登录，则重定向到login
//       next({
//         //这里是你登录页面的路由名称，或直接使用 path:'/login'
//         name: "login",
//         query: { redirect: to.fullPath }
//       });
//     } else {
//       next();
//     }
//   } else {
//     // 确保一定要调用 next()
//     next();
//   }
// });

export default router;
