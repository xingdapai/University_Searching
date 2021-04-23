<template>
	<!-- 整个注册盒子 -->
	<div class="registerbox" style="top: 325px;">
		<!-- 注册页面 -->
		<div class="page-register">
			<!-- 注册页面头部————跳转登录 -->
			<article class="header">
				<header>
					<!-- <el-avatar icon="el-icon-user-solid" shape="circle"></el-avatar> -->
					<span class="login">
						<em class="emp">已有账号？</em>
						<a href="/login">
							<el-button type="primary" size="small">登录</el-button>
						</a>
					</span>
				</header>
			</article>
			<!-- 进度条步骤：1和2 -->
			<el-steps :active="active" finish-status="success">
				<el-step title="填写登录信息"></el-step>
				<el-step title="填写个人信息"></el-step>
			</el-steps>

			<!-- 注册主体：表单形式 -->
			<section>
				<el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm"
					autocomplete="off">
					<!-- 第一步骤 -->
					<div v-if="active==0">
						<el-form-item label="用户名" prop="name">
							<el-input v-model="ruleForm.name" />
						</el-form-item>
						<el-form-item label="邮箱" prop="email">
							<el-input v-model="ruleForm.email" placeholder="输入邮箱" />
							<!-- <el-col :span="12">
							              <el-input
							                v-model="ruleForm.email"
							                placeholder="输入邮箱并点击发送验证码"
							              />
							            </el-col>
							            <el-button
							              :loading="codeLoading"
							              :disabled="isDisable"
							              size="small"
							              round
							              @click="sendMsg"
							            >发送验证码</el-button>
							<span class="status">{{ statusMsg }}</span> -->
						</el-form-item>
						<!-- <el-form-item label="验证码" prop="code">
							 <el-col :span="12">
							              <el-input
							                v-model="ruleForm.code"
							                maxlength="6"
							                placeholder="请登录邮箱接收验证码"
							              />
							            </el-col>
						</el-form-item> -->
						<el-form-item label="密码" prop="pwd">
							<el-input v-model="ruleForm.pwd" type="password" />
						</el-form-item>
						<el-form-item label="确认密码" prop="cpwd">
							<el-input v-model="ruleForm.cpwd" type="password" />
						</el-form-item>
						<el-form-item prop="textarea">
							<el-input :value="ruleForm.textarea" type="textarea" :rows="3" :readonly="true"
								style="overflow-x:auto ;overflow-y: auto;">
							</el-input>
						</el-form-item>
						<el-form-item prop="agreed">
							<el-checkbox-group v-model="ruleForm.agreed">
								<el-checkbox label="同意注册协议" name="agreed"></el-checkbox>
							</el-checkbox-group>
						</el-form-item>
					</div>
					<!-- 第二步骤 -->
					<div v-if="active==1">
						<el-form-item label="性别" prop="sex">
							<el-radio-group v-model="ruleForm.sex">
								<el-radio label="男"></el-radio>
								<el-radio label="女"></el-radio>
							</el-radio-group>
						</el-form-item>
						<el-form-item label="出生日期" prop="birthday">
							<el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.birthday"
								style="width: 70%;"></el-date-picker>
						</el-form-item>
						<el-form-item label="手机号" prop="telnumber" style="width: 100%;">
							<el-input v-model="ruleForm.telnumber" />
						</el-form-item>
						<el-form-item label="民族" prop="valuenation">
							<el-select v-model="ruleForm.valuenation" placeholder="请选择民族">
								<el-option v-for="item in ruleForm.nation_options" :key="item.value" :label="item.label"
									:value="item.valuenation">
								</el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="所在地区" prop="area">
							<el-cascader size="large" :options="region_options" v-model="region" @change="handleChange">
							</el-cascader>
						</el-form-item>
						<el-form-item label="所选科目" prop="subject">
							<el-checkbox-group v-model="ruleForm.subject" :max="6">
								<el-checkbox label="数学"></el-checkbox>
								<el-checkbox label="语文"></el-checkbox>
								<el-checkbox label="外语"></el-checkbox>
								<el-checkbox label="政治"></el-checkbox>
								<el-checkbox label="地理"></el-checkbox>
								<el-checkbox label="历史"></el-checkbox>
								<el-checkbox label="物理"></el-checkbox>
								<el-checkbox label="化学"></el-checkbox>
								<el-checkbox label="生物"></el-checkbox>
							</el-checkbox-group>
						</el-form-item>
					</div>
				</el-form>
				<!-- 注册页面脚部：页面转换及提交表单 -->
			</section>
			<div class="footer" style="height: 140px;">
				<el-button v-if="active>0" type="primary" icon="el-icon-arrow-left" @click="prev">上一步</el-button>
				<el-button v-if="active<step-1" type="primary" icon="el-icon-arrow-right" @click="next">同意协议并进行下一步
				</el-button>
				<el-button v-if="active==step-1" type="primary" @click="register">提交信息并登录</el-button>
				<div class="error">{{ error }}</div>
			</div>
		</div>
	</div>
</template>

<script>
	import axios from "axios"
	import {
		regionDataPlus
	} from 'element-china-area-data'
	import {
		getEmailCode
	} from '@/api'
	export default {
		name: "register",

		data() {
			return {
				step: 2,
				active: 0,
				// statusMsg: '',
				error: '',
				// isDisable: false,
				// codeLoading: false,
				region_options: regionDataPlus,
				region: [],
				ruleForm: {
					textarea: '请仔细阅读以下协议:这个人打扮与众姑娘不同,彩绣辉煌,恍若神妃仙子: 头上戴着金丝八宝攒珠髻,绾着朝阳五凤挂珠钗,项上戴着赤金盘螭璎珞圈,裙边系着豆绿宫绦,双衡比目玫瑰佩,身上穿着缕金百蝶穿花大红洋缎窄Ё袄, 外罩五彩刻丝石青银鼠褂,下着翡翠撒花洋绉裙.一双丹凤三角眼,两弯柳叶吊梢眉, 身量苗条, 体格风骚,粉面含春威不露,丹唇未起笑先闻。',
					agreed: false,
					name: '',
					code: '',
					pwd: '',
					telnumber: '',
					birthday: '',
					cpwd: '',
					email: '',
					agreed: [],
					sex: '',
					nation_options: [{
							valuenation: '1',
							label: '汉族'
						},
						{
							valuenation: '2',
							label: '蒙古族'
						},
						{
							valuenation: '3',
							label: '彝族'
						},
						{
							valuenation: '4',
							label: '侗族'
						},
						{
							valuenation: '5',
							label: '哈萨克族'
						},
						{
							valuenation: '6',
							label: '畲族'
						},
						{
							valuenation: '7',
							label: '纳西族'
						},
						{
							valuenation: '8',
							label: '仫佬族'
						},
						{
							valuenation: '9',
							label: '仡佬族'
						},
						{
							valuenation: '10',
							label: '怒族'
						},
						{
							valuenation: '11',
							label: '保安族'
						},
						{
							valuenation: '12',
							label: '鄂伦春族'
						},
						{
							valuenation: '13',
							label: '回族'
						},
						{
							valuenation: '14',
							label: '壮族'
						},
						{
							valuenation: '15',
							label: '瑶族'
						},
						{
							valuenation: '16',
							label: '傣族'
						},
						{
							valuenation: '17',
							label: '高山族'
						},
						{
							valuenation: '18',
							label: '景颇族'
						},
						{
							valuenation: '19',
							label: '羌族'
						},
						{
							valuenation: '20',
							label: '锡伯族'
						},
						{
							valuenation: '21',
							label: '乌孜别克族'
						},
						{
							valuenation: '22',
							label: '裕固族'
						},
						{
							valuenation: '23',
							label: '赫哲族'
						},
						{
							valuenation: '24',
							label: '藏族'
						},
						{
							valuenation: '25',
							label: '布依族'
						},
						{
							valuenation: '26',
							label: '白族'
						},
						{
							valuenation: '27',
							label: '黎族'
						},
						{
							valuenation: '28',
							label: '拉祜族'
						},
						{
							valuenation: '29',
							label: '柯尔克孜族'
						},
						{
							valuenation: '30',
							label: '布朗族'
						},
						{
							valuenation: '31',
							label: '阿昌族'
						},
						{
							valuenation: '32',
							label: '俄罗斯族'
						},
						{
							valuenation: '33',
							label: '京族'
						},
						{
							valuenation: '34',
							label: '门巴族'
						},
						{
							valuenation: '35',
							label: '维吾尔族'
						},
						{
							valuenation: '36',
							label: '朝鲜族'
						},
						{
							valuenation: '37',
							label: '土家族'
						},
						{
							valuenation: '38',
							label: '傈僳族'
						},
						{
							valuenation: '39',
							label: '水族'
						},
						{
							valuenation: '40',
							label: '土族'
						},
						{
							valuenation: '41',
							label: '撒拉族'
						},
						{
							valuenation: '42',
							label: '普米族'
						},
						{
							valuenation: '43',
							label: '鄂温克族'
						},
						{
							valuenation: '44',
							label: '塔塔尔族'
						},
						{
							valuenation: '45',
							label: '珞巴族'
						},
						{
							valuenation: '46',
							label: '苗族'
						},
						{
							valuenation: '47',
							label: '满族'
						},
						{
							valuenation: '48',
							label: '哈尼族'
						},
						{
							valuenation: '49',
							label: '佤族'
						},
						{
							valuenation: '50',
							label: '东乡族'
						},
						{
							valuenation: '51',
							label: '达斡尔族'
						},
						{
							valuenation: '52',
							label: '毛南族'
						},
						{
							valuenation: '53',
							label: '塔吉克族'
						},
						{
							valuenation: '54',
							label: '德昂族'
						},
						{
							valuenation: '55',
							label: '独龙族'
						},
						{
							valuenation: '56',
							label: '基诺族'
						},
					],
					valuenation: '',
					area: '',
					subject: ['语文', '数学', '外语'],
				},
				rules: {
					agreed: [{
						validator: (rule, value, callback) => {
							if (value !== true) {
								callback(new Error('请确认同意注册协议'))
							} else {
								callback()
							}
						},
						trigger: 'blur'
					}],

					name: [{
						required: true,
						type: 'string',
						message: '请输入用户名',
						trigger: 'blur'
					}],
					email: [{
						required: true,
						type: 'email',
						message: '请输入邮箱',
						trigger: 'blur'
					}],
					// code: [{
					//           required: true,
					//           type: 'string',
					//           message: '请输入验证码',
					//           trigger: 'blur'
					//         }],
					pwd: [{
							required: true,
							message: '创建密码',
							trigger: 'blur'
						},
						{
							pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/,
							message: '密码必须同时仅包含数字与字母,且长度为 8-20位'
						}
					],
					cpwd: [{
						required: true,
						message: '确认密码',
						trigger: 'blur'
					}, {
						validator: (rule, value, callback) => {
							if (value === '') {
								callback(new Error('请再次输入密码'))
							} else if (value !== this.ruleForm.pwd) {
								callback(new Error('两次输入密码不一致'))
							} else {
								callback()
							}
						},
						trigger: 'blur'
					}],
					sex: [{
						required: true,
						message: '请选择性别',
						trigger: 'change'
					}],
					valuenation: [{
						required: true,
						message: '请选择民族',
						trigger: 'change'
					}],
					subject: [{
						required: true,
						type: 'array',
						message: '请选择六门学科',
						trigger: 'change'
					}],
					telnumber: [{
							required: true,
							type: 'string',
							message: '请输入电话号码',
							trigger: 'blur'
						},
						{
							min: 11,
							max: 11,
							message: '请输入11位手机号码',
							trigger: 'blur'
						}
					],
					// area: [{
					// 	required: true,
					// 	type: 'string',
					// 	message: '请选择省市地区',
					// 	trigger: 'blur'
					// }],
					birthday: [{
						type: 'date',
						required: true,
						message: '请选择日期',
						trigger: 'change'
					}],

				}
			}
		},
		layout: 'blank',

		methods: {
			// 地区
			handleChange(value) {
				var loc = ''
				for (let i = 0; i < this.region.length; i++) {
					loc += CodeToText[this.region[i]] + '/'
				}
				this.ruleForm.area = loc
			},

			// 发送验证码
			// sendMsg: function() {
			// 	const self = this
			// 	let namePass
			// 	let emailPass
			// 	if (self.timerid) {
			// 		return false
			// 	}
			// 	this.$refs['ruleForm'].validateField('name', (valid) => {
			// 		namePass = valid
			// 	})
			// 	self.statusMsg = ''
			// 	if (namePass) {
			// 		return false
			// 	}
			// 	this.$refs['ruleForm'].validateField('email', (valid) => {
			// 		emailPass = valid
			// 	})
			//向后台API验证码发送
			// 	if (!namePass && !emailPass) {
			// 		self.codeLoading = true
			// 		self.statusMsg = '验证码发送中...'
			// 		getEmailCode(self.ruleForm.email).then(res => {
			// 			this.$message({
			// 				showClose: true,
			// 				message: '发送成功，验证码有效期5分钟',
			// 				type: 'success'
			// 			})
			// 			let count = 60
			// 			self.ruleForm.code = ''
			// 			self.codeLoading = false
			// 			self.isDisable = true
			// 			self.statusMsg = `验证码已发送,${count--}秒后重新发送`
			// 			timerid = window.setInterval(function() {
			// 				self.statusMsg = `验证码已发送,${count--}秒后重新发送`
			// 				if (count <= 0) {
			// 					console.log('clear' + timerid)
			// 					window.clearInterval(timerid)
			// 					self.isDisable = false
			// 					self.statusMsg = ''
			// 				}
			// 			}, 1000)
			// 		}).catch(err => {
			// 			this.isDisable = false
			// 			this.statusMsg = ''
			// 			this.codeLoading = false
			// 			console.log(err.response.data.message)
			// 		})
			// 	}
			// },

			// 下一步
			next: function() {
				if (this.active === 0) {
					let namePass
					let emailPass
					// let codePass
					let pwdPass
					let cpwdPass
					this.$refs['ruleForm'].validate('agreed', (valid) => {
						// console.log(valid)
						if (valid === '') {
							this.active++
						}
					})
					this.$refs['ruleForm'].validateField('name', (valid) => {
						namePass = valid
					})
					// self.statusMsg = ''
					// if (namePass) {
					// 	return false
					// }
					this.$refs['ruleForm'].validateField('email', (valid) => {
						emailPass = valid
					})
					if (emailPass) {
						return false
					}
					// this.$refs['ruleForm'].validateField('code', (valid) => {
					//   codePass = valid
					// })
					// if (codePass) {
					//   return false
					// }
					this.$refs['ruleForm'].validateField('pwd', (valid) => {
						pwdPass = valid
					})
					if (pwdPass) {
						return false
					}
					this.$refs['ruleForm'].validateField('cpwd', (valid) => {
						cpwdPass = valid
					})
					if (cpwdPass) {
						return false
					}
					this.active++
				}
			},
			// 前一步
			prev: function() {
				this.$refs['ruleForm'].clearValidate()
				if (--this.active < 0) this.active = 0
			},

			// 用户注册
			register: function() {
				this.$refs['ruleForm'].validate((valid) => {
					if (valid) {
						axios
							.post("/api/login/", {

								username: this.ruleForm.name,
								// code: this.ruleForm.code,
								password: encrypt(this.ruleForm.pwd),
								email: this.ruleForm.email,
								sex: this.ruleForm.sex,
								telnumber: this.ruleForm.telnumber,
								birthday: this.ruleForm.birthday,
								valuenation: this.ruleForm.valuenation,
								area: this.ruleForm.area,
								subject: this.ruleForm.subject,
								// enabled: true
							})
							.then(res => {
								this.$message({
									showClose: true,
									message: '注册成功',
									type: 'success'
								})
								setTimeout(() => {
									this.$router.push('/')
								}, 2000)
							}).catch(err => {
								console.log(err.response.data.message)
							})
					}
				})
			}
		}
	}
</script>

<style>
	/* 注册盒子 */
	.registerbox {
		display: flex;
		position: absolute;
		/* background-color: #effab5; */
		background-color: #ffffff;
		width: 1000px;
		height: 650px;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 #4E655D;
		/* 阴影效果 */
	}

	/* 注册页面 */
	.page-register {
		width: 550px;
		padding-left: 200px;
		padding-top: 10px;
	}

	/* 页面脚部位置 */
	.footer {
		padding-left: 100px;
	}

	/* 步骤主样式 */
	.el-step__main {
		white-space: normal;
		text-align: center;
		width: 280px;
	}

	/* 步骤线 */
	.el-step__line {
		width: 380px;
	}

	.el-steps--horizontal {
		white-space: nowrap;
		padding-bottom: 20px;
		padding-left: 100px;
	}

	/* 表单元素样式 */
	.el-form-item {
		margin-bottom: 16px;
	}
</style>
