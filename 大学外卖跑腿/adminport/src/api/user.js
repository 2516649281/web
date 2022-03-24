import router from "../util/router";

//查询所有用户
export function selectAllUser(current, size) {
  return router({
    url: `/user/${current}/${size}`,
    method: "get",
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}

//查询所有用户
export function selectLikeDury(current, size, userDury) {
  return router({
    url: `/user/${current}/${size}/${userDury}`,
    method: "get",
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}

//添加用户
export function addUser(obj) {
  return router({
    url: "/user",
    method: "post",
    data: obj,
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}

//删除用户
export function delUser(obj) {
  return router({
    url: "/user",
    method: "delete",
    data: obj,
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}

//修改用户
export function updateUser(obj) {
  return router({
    url: "/user",
    method: "put",
    data: obj,
    headers: {
      token: sessionStorage.getItem("token"),
    },
  });
}
