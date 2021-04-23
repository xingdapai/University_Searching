<template>
	<div class="recbox" style="margin-top: -60px;">
		<div class="headerbox">
			<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" text-color="#475669"
				@select="handleSelect">
				<el-menu-item index="0">
					<img src="../../assets/img/homepage_logo.png" />
				</el-menu-item>

				<el-menu-item index="1" class="menu">
					<a href="homepage" style="text-decoration: none;">首 页</a>
				</el-menu-item>
				<el-menu-item index="2" class="menu">大学名单</el-menu-item><!-- 可以后续加上按地区分类的下拉框 -->
				<el-menu-item index="3" class="menu">
					<a href="recommend" style="text-decoration: none;">报考备考推荐</a>
				</el-menu-item><!-- 跳转到推荐页面——条件搜索匹配 -->
				<el-submenu index="4">
					<template slot="title">
						<div class="menu">联系我们</div>
					</template>
					<el-menu-item index="4-1">联系人：徐晓燕</el-menu-item>
					<el-menu-item index="4-2">联系电话：123123123123</el-menu-item>
					<el-menu-item index="4-3">通讯地址：湖北省武汉市珞喻路1037号华中科技大学</el-menu-item>
				</el-submenu>
				<el-menu-item index="60" style="float:right;">
					<div>
						<el-tooltip content="用户登录" effect="dark" placement="bottom">
							<a href="login">
								<el-avatar :size="45" shape="circle">user</el-avatar>
							</a>
						</el-tooltip>
					</div>
				</el-menu-item>
			</el-menu>
		</div>
		<div class="advertisement"></div>
		<div class="factor">
			<el-form ref="form" :model="form" label-width="80px" class="rec">
				<el-form-item label="考试分数">
					<el-input v-model="form.grade"></el-input>
				</el-form-item>
				<el-form-item label="考生类别">
					<el-select v-model="form.category" placeholder="请选择考生类别">
						<el-option label="普通" value="normal"></el-option>
						<el-option label="艺术" value="art"></el-option>
						<el-option label="军事" value="military"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="意愿地区">
					<el-cascader size="large" :options="form.will_region_options" v-model="form.will_region"
						@change="handleChange">
					</el-cascader>
				</el-form-item>

				<el-form-item>
					<el-button type="primary" @click="onSubmit">推荐搜索</el-button>
					<!-- <el-button>取消</el-button> -->
				</el-form-item>
			</el-form>
		</div>
	</div>

</template>

<script>
	import {
		provinceAndCityDataPlus
	} from 'element-china-area-data'
	export default {
		name: "recommend",

		data() {
			return {
				form: {
					grade: '',
					category: '',
					will_region_options: provinceAndCityDataPlus,
					will_region: [],
				}
			}
		},

		methods: {
			handleChange(value) {
				console.log(value)
			},
			onSubmit() {
				this.$refs['form'].validate((valid) => {
					if (valid) {
						axios
							.post("/api/login/", {
								grade: this.form.grade,
								category: this.form.category,
								will_region: this.form.will_region,

							})
							.then(res => {
								this.$route.push({
									path: '/result'
								})
							})
					}
				})
			}

		},
	}
</script>

<style>
	.menu {
		font-size: 17px;
	}

	.advertisement {
		height: 200px;
		background-image: url(./img/小王子2.jpg);
	}

	.factor {
		display: flex;
		height: 500px;
		width: 70%;
		position: absolute;
		text-align: center;
		left: 50%;
		transform: translate(-50%);
		box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 #4E655D;
	}

	.rec {
		position: relative;
		text-align: center;
		top: 20%;
		left: 50%;
		transform: translate(-50%);

	}
</style>
