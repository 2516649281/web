import router from "../util/router";

//登录
export function login(obj) {
  return router({
    url: `/account/login`,
    method: "post",
    data: obj,
  });
}

//查询所有账号
export function selectAllAccount(current, size) {
  return router({
    url: `/account/${current}/${size}`,
    method: "get",
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}

//根据账号名查询
export function selectAllLikeName(current, size, accountName) {
  return router({
    url: `/account/${current}/${size}/${accountName}`,
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

//冻结账号
export function deleteAccount(obj) {
  return router({
    url: "/account",
    method: "delete",
    data: obj,
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}

//解冻账号
export function unfreeAccount(obj) {
  return router({
    url: "/account?ufz",
    method: "put",
    data: obj,
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}
