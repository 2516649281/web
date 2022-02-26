//管理端
import router from '../util/router'

//查询所有学生
export function selectAllStudent(current,size) {
    return router({
        url:`/adminPost/${current}/${size}?student`,
        method:'GET'
    })
}

//根据班级名查询学生
export function selectAllStudentByClassName(current,size,className) {
    return router({
        url:`/adminPost/${current}/${size}/${className}?student`,
        method:'GET'
    })
}

//添加学生
export function addStudent(obj) {
    return router({
        url:`/adminPost?student`,
        method:'POST',
        data:obj
    })
}

//修改学生
export function editStudentById(obj) {
    return router({
        url:`/adminPost?student`,
        method:'PUT',
        data:obj
    })
}

//删除学生
export function delStudentById(obj) {
    return router({
        url:`/adminPost?student`,
        method:'DELETE',
        data:obj
    })
}

//查询所有教师
export function selectAllTeacher(current,size) {
    return router({
        url:`/adminPost/${current}/${size}?teacher`,
        method:'GET'
    })
}

//根据地址查询教师
export function selectAllTeacherByAddress(current,size,address) {
    return router({
        url:`/adminPost/${current}/${size}/${address}?teacher`,
        method:'GET'
    })
}

//添加教师信息
export function addTeacher(obj){
    return router({
        url:'/adminPost?teacher',
        method:'POST',
        data:obj
    })
}

//修改教师
export function editTeacherById(obj) {
    return router({
        url:`/adminPost?teacher`,
        method:'PUT',
        data:obj
    })
}

//删除教师
export function delTeacherById(obj) {
    return router({
        url:`/adminPost?teacher`,
        method:'DELETE',
        data:obj
    })
}

//查询所有正常用户
export function selectAllOkUser(current,size) {
    return router({
        url:`/adminPost/${current}/${size}?user`,
        method:'GET'
    })
}

export function selectAllUser(current,size) {
    return router({
        url:`/adminPost/${current}/${size}?allUser`,
        method:'GET'
    })
}

//根据模糊用户名查询用户
export function selectAllUserLikeUserName(current,size,userName) {
    return router({
        url:`/adminPost/${current}/${size}/${userName}?allUser`,
        method:'GET'
    })
}

//冻结账号
export function delUser(obj) {
    return router({
        url:`/adminPost?user`,
        method:'DELETE',
        data:obj
    })
}

//解冻账号
export function unfreeUser(obj) {
    return router({
        url:`/adminPost?user`,
        method:'PUT',
        data:obj
    })
}

//授予或撤销管理员权限
export function addOrDelAdmin(obj) {
    return router({
        url:`/adminPost?admin`,
        method:'PUT',
        data:obj
    })
}

//查询所有班级
export function selectAllClass(current,size) {
    return router({
        url:`/adminPost/${current}/${size}?class`,
        method:'GET'
    })
}

//根据班级名模糊查询班级
export function selectAllClassLikeClassName(current,size,className) {
    return router({
        url:`/adminPost/${current}/${size}/${className}?class`,
        method:'GET'
    })
}

//添加班级
export function addClass(obj) {
    return router({
        url:`/adminPost?class`,
        method:'POST',
        data:obj
    })
}

//修改班级
export function editClassById(obj) {
    return router({
        url:`/adminPost?class`,
        method:'PUT',
        data:obj
    })
}

//删除班级
export function delClassById(obj) {
    return router({
        url:`adminPost?class`,
        method:'DELETE',
        data:obj
    })
}


