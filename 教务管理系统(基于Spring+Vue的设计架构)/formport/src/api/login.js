//用户
import router from "../util/router";

//登录
export function login(obj) {
  return router({
    url: `/user?login`,
    method: "POST",
    data: obj
  });
}
//注册
export function adduser(obj) {
  return router({
    url: `/user?register`,
    method: "POST",
    data: obj
  });
}

//校验学生
export function selectAllStudent(studentName, studentClass) {
  return router({
    url: `user/${studentName}/${studentClass}?userStudent`,
    method: "GET"
  });
}

//校验教师
export function selectAllTeacher(teachetName, teacherAddress) {
  return router({
    url: `user/${teachetName}/${teacherAddress}?userTeacher`,
    method: "GET"
  });
}

//修改用户
export function updateUser(obj) {
  return router({
    url: "/user",
    method: "PUT",
    data: obj
  });
}
