<template>
	<div class="loginbox">
		<!-- 左侧登录盒子 -->
		<div class="login">
			<el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-position="left" label-width="0px"
				class="login-form">
				<h3 class="wel-title">欢迎使用</h3>
				<el-form-item prop="username">
					<el-input v-model="loginForm.username" prefix-icon="el-icon-user" type="text" auto-complete="off"
						placeholder="用户名" class="inputbox">
					</el-input>
				</el-form-item>
				<el-form-item prop="password">
					<el-input v-model="loginForm.password" prefix-icon="el-icon-lock" type="password"
						auto-complete="off" placeholder="密码" show-password @keyup.enter.native="handleLogin"
						class="inputbox">
					</el-input>
				</el-form-item>

				<!-- <el-checkbox v-model="loginForm.rememberMe" class="rememberMebox">
					记住密码
				</el-checkbox> -->
				<el-form-item style="width: 100%">
					<el-button :loading="loading" size="medium" type="primary" class="login_btn"
						@click.native.prevent="handleLogin">
						<span v-if="!loading">登 录</span>
						<span v-else>登 录 中...</span>
					</el-button>
				</el-form-item>
				<el-form-item style="width: 100%">
					<a href="/register" class="register">若无账号点击此处注册新账号</a>
				</el-form-item>
			</el-form>
			<div class="warn">@wjx</div>
		</div>

		<!-- 右边盒子 背景图 -->
		<div class="background">
			<div class="title">欢迎来到中国大学搜索网站</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'login',
		data() {
			return {
				cookiePass: '',
				loginForm: {
					username: '',
					password: '',
					// rememberMe: false,
					// uuid: ''
				},
				loginRules: {
					username: [{
						required: true,
						trigger: 'blur',
						message: '用户名不能为空'
					}],
					password: [{
						required: true,
						trigger: 'blur',
						message: '密码不能为空'
					}],

				},
				loading: false,
				redirect: undefined
			}
		},
		watch: {
			$route: {
				handler: function(route) {
					this.redirect = route.query && route.query.redirect
				},
				immediate: true
			}
		},

		methods: {

			getCookie() {
				const username = Cookies.get('username')
				let password = Cookies.get('password')
				const rememberMe = Cookies.get('rememberMe')
				// 保存cookie里面的加密后的密码
				this.cookiePass = password === undefined ? '' : password
				password = password === undefined ? this.loginForm.password : password
				this.loginForm = {
					username: username === undefined ? this.loginForm.username : username,
					password: password,
					rememberMe: rememberMe === undefined ? false : Boolean(rememberMe),
					code: ''
				}
			},
			handleLogin() {
				this.$refs.loginForm.validate((valid) => {
					// const user = {
					// 	username: this.loginForm.username,
					// 	password: this.loginForm.password,
					// 	rememberMe: this.loginForm.rememberMe,
					// 	// code: this.loginForm.code,
					// 	uuid: this.loginForm.uuid
					// }
					// if (user.password !== this.cookiePass) {
					// 	user.password = encrypt(user.password)
					// }
					if (valid) {
						axios
							.post("/submitLogin/", {
								// .post("/api/login/", {
								username: this.loginForm.username,
								password: this.loginForm.password
							})
							.then(res => {
								this.$route.push({
									path: '/homepage'})
									})
									

						// this.loading = true
						// if (user.rememberMe) {
						// 	Cookies.set('username', user.username, {
						// 		expires: Config.passCookieExpires
						// 	})
						// 	Cookies.set('password', user.password, {
						// 		expires: Config.passCookieExpires
						// 	})
						// 	Cookies.set('rememberMe', user.rememberMe, {
						// 		expires: Config.passCookieExpires
						// 	})
						// } else {
						// 	Cookies.remove('username')
						// 	Cookies.remove('password')
						// 	Cookies.remove('rememberMe')
						// }
						
						//验证成功跳转主页
					} else {
						console.log('error submit!!')
						return false
					}
				})
				
			},
		}
	}
</script>

<style>
	/* 整个登录界面样式 */
	.loginbox {
		display: flex;
		position: absolute;
		/* background-color: #89AB9E; */
		background-color: #f1f19f;
		width: 800px;
		/* 左顶点在中心 */
		height: 400px;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 #4E655D;
		/* 阴影效果 */
	}

	/* 左边登录盒子大小 */
	.login {
		background-color: #f1f19f;
		width: 250px;
	}

	/* 欢迎语 */
	.wel-title {
		margin-top: 30px;
		text-align: center;
		color: #354b7a;
	}

	/* 整个登录表单大小 */
	.login-form {
		background: #f1f19f;
		position: absolute;
		width: 250px;
		height: 350px;
	}

	/* 表单各元素间距 */
	.el-form-item {
		margin-bottom: 10px;
	}

	/* 输入框样式 */
	.el-input {
		position: relative;
		font-size: 14px;
		display: inline-block;
		width: 80%;
		border-bottom: 1px solid #d0d0d0;
		margin-top: 10px;
	}

	/* 输入框内部样式 */
	.el-input__inner {
		-webkit-appearance: none;
		background-color: #f1f19f;
		background-image: none;
		border-radius: 4px;
		border: 1px solid #f1f19f;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
		color: #606266;
		display: inline-block;
		font-size: inherit;
		height: 40px;
		line-height: 40px;
		outline: 0;
		padding: 0 15px;
		padding-left: 15px;
		-webkit-transition: border-color .2s cubic-bezier(.645, .045, .355, 1);
		transition: border-color .2s cubic-bezier(.645, .045, .355, 1);
		width: 100%;
		font-weight: bold;
	}


	/* .login-tip {
		font-size: 13px;
		text-align: center;
		color: #bfbfbf;
		
	} */

	/* 登录按钮 */
	.login_btn {
		background-color: #587eb5;
		/* Green */
		border: none;
		color: #FAFAFA;
		padding: 10px 22px;
		text-align: center;
		font-size: 15px;
		border-radius: 20px;
		outline: none;
		/* 	transform: translate(-60%); */
	}

	.login_btn:hover {
		box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);
		cursor: pointer;
		background-color: #587eb5;
		font-weight: bold;
		transition: all 0.2s ease-in;
	}

	/* 记住密码 */
	.rememberMebox {
		background-color: transparent;
		/* color: #FAFAFA; */
		padding: 25px 22px;
		font-size: 20px;
		color: #587eb5;
	}

	/* 注册新用户样式 */
	.register {
		background-color: transparent;
		border: none;
		color: #587eb5;
		text-align: center;
		font-size: 13px;
		outline: none;
		text-decoration: underline;
	}

	.register:hover {
		cursor: pointer;
		font-weight: bold;
		transition: all 0.2s ease-in;
	}

	a {
		color: #FAFAFA;
		text-decoration: none;
		background-color: transparent;
		outline: none;
		cursor: pointer;
		transition: color 0.3s;
	}

	/* 右背景样式 */
	.background {
		width: 550px;

		flex-direction: row;
		background-image: url(img/小王子4.jpg);
		background-size: cover;
		font-family: sans-serif;

	}

	/* 标题样式 */
	.title {
		margin-top: 320px;
		/* display: flex; */
		font-weight: bold;
		font-size: 20px;
		color: #354b7a;

	}

	.title:hover {
		font-size: 21px;
		transition: all 0.4s ease-in-out;
		cursor: pointer;
	}

	/* 缺用户名和密码报错样式 */
	.el-form-item__error {
		color: #F56C6C;
		font-size: 12px;
		line-height: 1;
		padding-top: 5px;
		position: absolute;
		top: 100%;
		left: 25px;
	}

	/* 网页版权样式 */
	.warn {
		margin-top: 360px;
		/* margin-right:120px; */
		margin-left: 150px;
		margin-bottom: 5px;
		font-weight: bold;
		font-size: 17px;
	}
</style>
