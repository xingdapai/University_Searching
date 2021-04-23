<template>
	<div class="schoolpage" style="margin-top: -60px;">
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
		<div class="title">
			<el-card shadow="hover">
			     {{schoolName}}
			   </el-card>
		</div>
		<div class="schoolbox" style="display: flex;">
			<div class="schoolpic"></div>
			<div class="schoolmesbox">
				<el-card class="schoolmes">
				  <div class="text_item">
				    {{'高校类型 :' + schoolType }}
				  </div>
				  <div class="text_item">
				    {{'高校隶属于 :' + schoollishuyu }}
				  </div>
				  <div class="text_item">
				    {{'高校所在地 :' + schoolsuozaidi }}
				  </div>
				 
				</el-card>
				<el-card class="schoolmes">
				  <div class="text_item">
				    {{'通讯地址 :' + schoolAddress }}
				  </div>
				  <div class="text_item">
				    {{'联系电话 :' + schoolTelnumber }}
				  </div>
				  <div class="text_item">
				    {{'学校网址 :' + schoolWebsite }}
				  </div>	
				</el-card>
			</div>
		</div>
        <div class="collegebox">
			<el-card class="college" >
			  <div slot="header" class="clearfix">
			    <span>专业名单</span>
			    <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
			  </div>
			  <div v-for="o in college" :key="o" class="text item">
			    {{ o }}
			  </div>
			</el-card>
		</div>


	</div>
</template>

<script>
	import url from "@/api.js";
	export default {
		name: "school",
		data() {
			return {
				schoolName: "",
				schoolType:"",
				schoollishuyu:"",
				schoolsuozaidi:"",
				schoolAddress:"",
				schoolTelnumber:"",
				schoolWebsite:"",
				college:[1,2,3,4],
			};
		},
		created() {
			// 接收result.vue传过来的schoolName
			this.schoolName = this.$route.query.schoolName
			this.schoolType = this.$route.query.schoolType
			this.schoollishuyu = this.$route.query.schoollishuyu
			this.schoolsuozaidi = this.$route.query.schoolsuozaidi
			this.schoolAddress = this.$route.query.schoolAddress
			this.schoolTelnumber = this.$route.query.schoolTelnumber
			this.schoolWebsite = this.$route.query.schoolWebsite
			this.college = this.$route.query.college
			// console.log(this.schoolName)
			this.getschoolInfo();
		},
		methods: {
			getschoolInfo() {
				let method = 'GET'
				let url = ''
				
				this.axios({
						url: url + devCode,
						method: method,
						baseURL: baseUrl,
						// params: dd
					})
					.then(res => {
						this.msg = res.data
						this.schoolType = this.msg.schoolType
						this.schoollishuyu = this.msg.schoollishuyu
						this.schoolsuozaidi = this.msg.schoolsuozaidi
						this.schoolAddress = this.msg.schoolAddress
						this.schoolTelnumber = this.msg.schoolTelnumber
						this.schoolWebsite = this.msg.schoolWebsite
						this.college = this.msg.college
					})
				// this.$http
				// 	.post(url.getDetailGoodsInfo, {
				// 		schoolName: this.schoolName
				// 	})
				// 	.then(response => {
				// 		//根据schoolName获取对应的学校详情信息
				// 		console.log(response)
				// 	})
				// 	.catch(error => {

				// 	});
			}
		}
	};
</script>

<style>
	.menu {
		font-size: 17px;
	}
	.advertisement {
		height: 120px;
		background-image: url(./img/小王子2.jpg);
	}
	.title{
		width: 780px;
		margin-top: 20px;
		position: relative;
		left: 50%;
		transform: translate(-50%);
	}

	.schoolbox {
		display: flex;
		height: 150px;
		width: 780px;
		background-color: #2C3E50;
		margin-top: 10px;
		left: 50%;
		transform: translate(-50%);
		text-align: center;
		position: absolute;
		/* box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 #4E655D; */
	}
	
	.schoolpic{
		height: 150px;
		width: 150px;
		background-color:#F56C6C;
		margin-right: 10px;
	}
	.schoolmesbox{
		height: 150px;
		width: 620px;
		display: flex;
	}
	.schoolmes{
		height: 150px;
		width: 50%;
	}
	.text_item{
		margin-bottom: 20px;
	}
	.text {
	    font-size: 14px;
	  }
	
	  .item {
	    margin-bottom: 18px;
	  }
	
	  .clearfix:before,
	  .clearfix:after {
	    display: table;
	    content: "";
	  }
	  .clearfix:after {
	    clear: both
	  }
	
	  .college {
	    width: 780px;
	  }
	  .collegebox{
		  position: absolute;
		  left: 50%;
		  transform: translate(-50%,200px);
		  width: 780px;
	  }
		
</style>
