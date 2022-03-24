import router from "../util/router";

//查询所有账单
export function selectAll(current, size) {
  return router({
    url: `/order/${current}/${size}`,
    method: "get",
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}

//按订单名查询账单
export function selectAllLikeName(current, size, orderName) {
  return router({
    url: `/order/${current}/${size}/${orderName}?order`,
    method: "get",
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}

//添加订单
export function addOrder(obj) {
  return router({
    url: "/order",
    method: "post",
    data: obj,
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}

//修改订单
export function updateOrder(obj) {
  return router({
    url: "/order",
    method: "put",
    data: obj,
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}

//删除订单
export function delOrder(obj) {
  return router({
    url: "/order",
    method: "delete",
    data: obj,
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}
