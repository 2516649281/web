import Vue from "vue";
import VueRouter from "vue-router";
//账号设置界面
import Setting from "../views/public/Setting";

Vue.use(VueRouter);
const routes = [
  //登录
  {
    path: "/",
    name: "login",
    component: () => import("../views/public/login"),
  },
  {
    //管理员
    path: "/adminIndex",
    name: "adminIndex",
    redirect: "/account",
    component: () => import("../views/AdminPort/index"),
    children: [
      //账号管理
      {
        path: "/account",
        name: "account",
        component: () => import("../views/AdminPort/Account"),
      },
      //订单管理
      {
        path: "/order",
        name: "order",
        component: () => import("../views/AdminPort/Order"),
      },
      //用户管理
      {
        path: "/user",
        name: "user",
        component: () => import("../views/AdminPort/User"),
      },
      //系统设置
      {
        path: "/adminSetting",
        name: "adminSetting",
        component: Setting,
      },
    ],
  },
];
const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});
// 登录拦截器
router.beforeEach((to, from, next) => {
  var token = sessionStorage.getItem("token");
  if (to.name === "login") {
    next();
  } else {
    if (token === null) {
      next({ name: "login" });
    } else {
      next();
    }
  }
});

export default router;
