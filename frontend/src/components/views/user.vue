<template>
	<div class="registerbox" style="top: 325px;">
		<div class="page-register">
			<article class="header">
				<header>
					<el-avatar icon="el-icon-user-solid" shape="circle"></el-avatar>
					<span class="login">
						<em class="emp">已有账号？</em>
						<a href="/login">
							<el-button type="primary" size="small">登录</el-button>
						</a>
					</span>
				</header>
			</article>
			<el-steps :active="active" finish-status="success">
				<el-step></el-step>
				<el-step></el-step>
			</el-steps>

			<section>
				<el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm"
					autocomplete="off">
					<div v-if="active==0">
						<el-form-item label="用户名" prop="name">
							<el-input v-model="ruleForm.name" />
						</el-form-item>
						<el-form-item label="邮箱" prop="email">
							<el-input v-model="ruleForm.email" />
							<el-button size="mini" round @click="sendMsg">发送验证码</el-button>
							<span class="status">{{ statusMsg }}</span>
						</el-form-item>
						<el-form-item label="验证码" prop="code">
							<el-input v-model="ruleForm.code" maxlength="4" />
						</el-form-item>
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
								<!-- <el-checkbox v-model="ruleForm.agreed">同意注册协议</el-checkbox> -->
							</el-checkbox-group>
						</el-form-item>
					</div>
					<div v-if="active==1">
						<el-form-item label="性别" prop="sex">
							<el-radio-group v-model="ruleForm.sex">
								<el-radio label="男"></el-radio>
								<el-radio label="女"></el-radio>
							</el-radio-group>
						</el-form-item>
						<el-form-item label="民族" prop="nation">
							<el-select v-model="ruleForm.valuenation" placeholder="请选择民族">
								<el-option v-for="item in ruleForm.nation_options" :key="item.value" :label="item.label"
									:value="item.valuenation">
								</el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="所在地区">
							<v-distpicker></v-distpicker>
						</el-form-item>
						<el-form-item label="所选科目">
							<el-checkbox-group v-model="ruleForm.checkList" :min="3"
    :max="6">
								<el-checkbox label="数学" disabled></el-checkbox>
								<el-checkbox label="语文" disabled></el-checkbox>
								<el-checkbox label="外语" disabled></el-checkbox>
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
			</section>
			<div class="footer" style="height: 140px;">
				<el-button v-if="active>0" type="primary" icon="el-icon-arrow-left" @click="prev">上一步</el-button>
				<el-button v-if="active<step-1" type="primary" icon="el-icon-arrow-right" @click="next">同意协议并进行下一步
				</el-button>
				<el-button v-if="active==step-1" type="primary" @click="register">提交信息并注册</el-button>
				<div class="error">{{ error }}</div>
			</div>
		</div>
	</div>

</template>

<script>
	import VDistpicker from 'v-distpicker'
	export default {
		components:{ VDistpicker },
		data() {
			return {
				step: 2,
				active: 0,
				statusMsg: '',
				error: '',
				ruleForm: {
					textarea: '请仔细阅读以下协议:这个人打扮与众姑娘不同,彩绣辉煌,恍若神妃仙子: 头上戴着金丝八宝攒珠髻,绾着朝阳五凤挂珠钗,项上戴着赤金盘螭璎珞圈,裙边系着豆绿宫绦,双衡比目玫瑰佩,身上穿着缕金百蝶穿花大红洋缎窄Ё袄, 外罩五彩刻丝石青银鼠褂,下着翡翠撒花洋绉裙.一双丹凤三角眼,两弯柳叶吊梢眉, 身量苗条, 体格风骚,粉面含春威不露,丹唇未起笑先闻。',
					agreed: '',
					name: '',
					code: '',
					pwd: '',
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
					checkList: ['语文','数学','外语'],
					


				},
				rules: {
					agreed: [{
						required: true,
						type: 'array',
						message: '请确认同意注册协议',
						trigger: 'change'
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
					pwd: [{
						required: true,
						message: '创建密码',
						trigger: 'blur'
					}],
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
				}
			}
		},
		layout: 'blank',
		created() {
		    this.getList()
		    this._getJsonData()
		    //console.log('jsonData:',jsonData)
		  },
		methods: {
			sendMsg: function() {
				const self = this
				let namePass
				let emailPass
				if (self.timerid) {
					return false
				}
				this.$refs['ruleForm'].validateField('name', (valid) => {
					namePass = valid
				})
				self.statusMsg = ''
				if (namePass) {
					return false
				}
				this.$refs['ruleForm'].validateField('email', (valid) => {
					emailPass = valid
				})
				// 模拟验证码发送
				if (!namePass && !emailPass) {
					let count = 30
					self.statusMsg = `验证码已发送,剩余${count--}秒`
					self.timerid = setInterval(function() {
						self.statusMsg = `验证码已发送,剩余${count--}秒`
						if (count == 0) {
							clearInterval(self.timerid)
						}
					}, 1000)
				}
			},

			next: function() {
				if (this.active === 0) {
					this.$refs['ruleForm'].validate((valid) => {
						console.log(valid)
						if (valid) {
							this.active++
						}
					})
				}
			},
			prev: function() {
				if (--this.active < 0) this.active = 0
			},

			// 模拟登录
			register: function() {
				this.$refs['ruleForm'].validate((valid) => {
					if (valid) {
						setTimeout(
							this.$router.push('/login'), 2000
						)
					}
				})
			},
			
			


		}
	}
</script>

<style>
	.registerbox {
		display: flex;
		position: absolute;
		background-color: #fefea7;
		width: 1000px;
		height: 630px;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 #4E655D;
		/* 阴影效果 */
	}

	.page-register {
		width: 500px;
		padding-left: 200px;
		padding-top: 10px;
	}

	.el-avatar {
		display: inline-block;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
		text-align: center;
		color: #fff;
		background: #C0C4CC;
		width: 35px;
		height: 35px;
		line-height: 35px;
		font-size: 14px;
	}

	.el-avatar--icon {
		font-size: 20px;
	}

	.footer {
		padding-left: 100px;
	}

	.el-step__main {
		white-space: normal;
		text-align: center;
		width: 280px;
	}

	.el-step__title.is-process {
		font-weight: 700;
		color: #303133;
	}

	.el-step__title.is-process {
		margin-left: 170px;
		width: 80px;
	}

	.el-step__line {
		width: 370px;
	}

	.el-form-item {
		margin-bottom: 16px;
	}

	.el-steps--horizontal {
		white-space: nowrap;
		padding-bottom: 20px;
		padding-left: 100px;
	}
</style>
