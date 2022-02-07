import Vue from 'vue'
import VueRouter from 'vue-router'
import Student from '../views/student'
import axios from 'axios'

Vue.use(VueRouter);
//配置路由规则
const router = new VueRouter({
    routes: [{
        //学生管理
        path: "/",
        name: 'student',
        component: Student
    }
    ]
});

export default router
