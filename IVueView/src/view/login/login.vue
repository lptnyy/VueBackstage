<style lang="less">
	@import './login.less';
</style>
<template>
	<div class="login">
		<div class="login-con">
			<Card icon="log-in" title="欢迎登录" :bordered="false">
				<div class="form-con">
					<login-form v-on:handleSubmit="handleSubmit"></login-form>
					<p class="login-tip">输入任意用户名和密码即可</p>
				</div>
			</Card>
		</div>
	</div>
</template>
<script>
	import LoginForm from '../../components/login-form'
	import router from '../../router'
	import {
		login
	} from '@/api/login'
	export default {
		components: {
			LoginForm
		},
		methods: {
			// 接受登录组件返回的参数
			handleSubmit({
				userName,
				password
			}) {
				var self = this;
				login(userName, password, function(result) {
					if (result.result) {
						var user = result.obj;
						localStorage.setItem("token", user.token)
						localStorage.setItem("userId",user.id)
						router.push({
							name: "main"
						})
					} else {
						self.$Message.error(result.msg);
					}
				});
			}
		}
	}
</script>
