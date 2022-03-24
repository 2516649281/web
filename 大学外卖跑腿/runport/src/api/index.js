import router from "../util/index";

//登录
export function login(obj) {
  return router({
    url: "/account/login",
    method: "post",
    data: obj,
  });
}

//查询所有订单
export function selectAllOrder(current, size) {
  return router({
    url: `/order/${current}/${size}`,
    method: "get",
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}

//按照名称查询所有订单
export function selectAllOrderByName(current, size, runId) {
  return router({
    url: `/order/${current}/${size}/${runId}?run`,
    method: "get",
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}

//修改账号
export function updateAccount(obj) {
  return router({
    url: "/account?ud",
    method: "put",
    data: obj,
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}
