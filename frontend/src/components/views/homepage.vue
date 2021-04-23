<template>
	<div class="homebox" style="margin-top: -60px;">
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
		<div class="banner">
			<el-carousel :interval="4000" type="card" height="300px">
				<el-carousel-item v-for="item in imgs" :key="item">
					<img :src="item.url" alt="无图片" />
					<h3 class="medium">{{ item }}</h3>
				</el-carousel-item>
			</el-carousel>
		</div>
		<div class="searchbox">
			<el-row>
				<el-col :span="22">
					<el-input v-model="searchData" size="large" prefix-icon="el-icon-search" placeholder="中国大学搜索">>
					</el-input>
				</el-col>
				<el-col :span="2">
					<el-button type="success" @click="search">搜索</el-button>
				</el-col>
			</el-row>
		</div>
		<div class="message">
			<div class="hotschool">
				<el-card class="box-card">
					<div slot="header" class="clearfix">
						<span>推荐学校</span>
					</div>
					<div class="text item"><a href="https://www.hust.edu.cn/">华中科技大学</a></div>
					<div class="text item"><a href="https://www.tsinghua.edu.cn/">清华大学</a></div>
					<div class="text item"><a href="https://www.nju.edu.cn/">南京大学</a></div>
					<div class="text item"><a href="https://www.pku.edu.cn/">北京大学</a></div>
					<!-- <div v-for="o in 4" :key="o" class="text item">
						{{'列表内容 ' + o }}
					</div> -->
				</el-card>
			</div>
			<div class="hotnews">
				<el-card class="box-card">
					<div slot="header" class="clearfix">
						<span>热门新闻</span>
						<!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
					</div>
					<div v-for="o in 4" :key="o" class="text item">
						{{'列表内容 ' + o }}
					</div>
				</el-card>
			</div>
		</div>
	</div>

</template>


<script>
	export default {
		name: "homepage",
		data() {
			return {
				searchData: '',
				imgs: [{
						url: require("@/assets/img/banner/4.jpg")
					},
					{
						url: require("@/assets/img/banner/5.jpg")
					},
					{
						url: require("@/assets/img/banner/6.jpg")
					},
					{
						url: require("@/assets/img/banner/7.jpg")
					},
				]

			}
		},
		methods: {
			// 搜索过滤数据
			search() {
				this.getList()
				this.$route.push({
					path: '/result'
				})
			},
			// 处理数据
			getList() {
				if (valid) {
					axios
						.post("/submitsearch/", {
							// .post("/api/login/", {
							searchData: this.searchData,
						})
						.then(res => {
							this.$route.push({
								path: '/result'})
								})
								}

			},


		}
	}
</script>
<style>
	.headerbox {
		margin-bottom: 30px;
	}

	.menu {
		font-size: 17px;
	}

	.el-carousel__item h3 {
		color: #475669;
		font-size: 14px;
		opacity: 0.75;
		line-height: 200px;
		margin: 0;
	}

	.el-carousel__item:nth-child(2n) {
		background-color: #99a9bf;
	}

	.el-carousel__item:nth-child(2n+1) {
		background-color: #d3dce6;
	}

	.searchbox {
		left: 50%;
		position: absolute;
		width: 70%;
		text-align: center;
		transform: translate(-50%);
		padding-top: 20px;
		padding-bottom: 20px;
	}

	.el-input__inner {
		height: 50px;
		font-size: 20px;
	}

	.message {
		display: flex;
		margin-top: 100px;
		margin-left: 60px;
		margin-right: 60px;
		height: 300px;
		width: 100%;
	}

	.hotschool {
		display: flex;
		height: 300px;
		width: 50%;
	}

	.hotnews {
		display: flex;
		height: 300px;
		width: 50%;
	}

	.text {
		font-size: 15px;
	}

	.item {
		margin-bottom: 20px;
	}

	a {
		margin-bottom: 20px;
	}

	.clearfix:before,
	.clearfix:after {
		display: table;
		content: "";
	}

	.clearfix:after {
		clear: both
	}

	.box-card {
		width: 95%;
	}
</style>
