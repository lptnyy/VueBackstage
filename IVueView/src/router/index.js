import Vue from 'vue/dist/vue.js';
import VueRouter from 'vue-router'
import iView from 'iview'
Vue.use(VueRouter)

// 1. 定义（路由）组件。
// 可以从其他文件 import 进来
import Login from '../view/login/login.vue'
import Main from '../view/main/main.vue'
import Users from '../view/user/users.vue'
import Functions from '../view/functions/function.vue'
import Role from '../view/role/role.vue'

// 2. 定义路由
const routes = [{
		path: '/login',
		component: Login,
		name: 'login'
	},
	{
		path: '/main',
		component: Main,
		name: 'main',
		children: [{
			path: '/users',
			name: 'users',
			component: Users
		}, {
			path: '/functions',
			name: 'functions',
			component: Functions
		}, {
			path: '/role',
			name: 'role',
			component: Role
		}]
	},
	{
		path: '/**',
		component: Login
	}
]

// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
const router = new VueRouter({
	routes // （缩写）相当于 routes: routes
	,
	mode: 'history'
})

// 全局拦截
const LOGIN_PAGE_NAME = 'login'
router.beforeEach((to, from, next) => {
	const token = localStorage.getItem("token");
	if (!token && to.name !== LOGIN_PAGE_NAME) {
		next({
			name: LOGIN_PAGE_NAME // 跳转到登录页
		})
	} else if (!token && to.name === LOGIN_PAGE_NAME) {
		next() // 跳转
	} else {
		next()
	}
})
export default router
