import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  //登录
  {
    path: "/",
    name: "login",
    component: () => import("../views/login"),
  },
  //导航
  {
    path: "/index",
    name: "index",
    redirect: "/order",
    component: () => import("../views/index"),
    children: [
      //订单
      {
        path: "/order",
        name: "order",
        component: () => import("../views/order"),
      },
      //用户
      {
        path: "/user",
        name: "user",
        component: () => import("../views/user"),
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
