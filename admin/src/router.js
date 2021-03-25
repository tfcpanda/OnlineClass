import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login.vue'
import Admin from './views/admin.vue'
import Welcome from './views/admin/welcome.vue'
import Chapter from './views/admin/chapter.vue'
import Section from './views/admin/section.vue'
import Course from './views/admin/course.vue'
import Category from './views/admin/category.vue'
import Teacher from './views/admin/teacher.vue'
import File from './views/admin/file.vue'
import User from './views/admin/user.vue'
import Resource from './views/admin/resource.vue'
import Role from './views/admin/role.vue'
import Member from './views/admin/member.vue'

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
        path: '*',
        redirect: "/login",
    }, {
        path: '',
        redirect: "/login",
    }, {
        path: '/login',
        component: Login
    }, {
        path: '/',
        name: "admin",
        component: Admin,
        meta: {
            loginRequire: true
        },
        children: [{
            path: 'welcome',
            name: "welcome",
            component: Welcome,
        }, {
            path: 'business/chapter',
            name: "business/chapter",
            component: Chapter,
        }, {
            path: 'business/section',
            name: "business/section",
            component: Section,
        }, {
            path: 'business/course',
            name: "business/course",
            component: Course,
        }, {
            path: 'business/category',
            name: "business/category",
            component: Category,
        }, {
            path: 'business/teacher',
            name: "business/teacher",
            component: Teacher,
        }, {
            path: 'business/file',
            name: "business/file",
            component: File,
        }, {
            path: 'business/user',
            name: "business/user",
            component: User,
        }, {
            path: 'business/resource',
            name: "business/resource",
            component: Resource,
        }, {
            path: 'business/role',
            name: "business/role",
            component: Role,
        }, {
            path: 'business/member',
            name: "business/member",
            component: Member,
        }]

    }]
})
