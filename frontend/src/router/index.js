import Vue from 'vue'
import VueRouter from 'vue-router'


import login from "@/components/views/login" //登陆
import register from "@/components/views/register"; //注册
import homepage from "@/components/views/homepage" //主页 
import recommend from "@/components/views/recommend" //推荐
import result from "@/components/views/result" //结果
import temp from "@/components/views/temp" //试验
import school from "@/components/views/school" //学校页面

Vue.use(VueRouter)

const routes = [{
        path: '/login',
        name: 'login',
        component: login
    },
    {
        path: '/register',
        name: 'register',
        component: register
    }, {
        path: '/homepage',
        name: 'homepage',
        component: homepage
    }, {
        path: '/recommend',
        name: 'recommend',
        component: recommend
    },{
        path: '/result',
        name: 'result',
        component: result
    },
	,{
	    path: '/temp',
	    name: 'temp',
	    component: temp
	},{
	    path: '/school',
	    name: 'school',
	    component: school
	},
	
	
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router

