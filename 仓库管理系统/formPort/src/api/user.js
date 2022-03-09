import router from "../util/router";

//登录
export function login(obj) {
  return router({
    url: "/user?login",
    method: "POST",
    data: obj
  });
}
