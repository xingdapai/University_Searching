<template>
	<div class="resultpage" style="margin-top: -60px;">
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
		<div style="display: flex;"></div>
		<div class="advertisement"></div>
		<div class="resultbox">
			<el-table :data="pageList">
				<el-table-column label="名称" prop="name">
					<template slot-scope="scope">
						<router-link :to="{name:'school',query:{schoolName:scope.row.name}}">{{scope.row.name}}
						</router-link>
					</template>
				</el-table-column>

				<el-table-column label="详细信息" prop="message"></el-table-column>
			</el-table>
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page"
				:page-sizes="[1,2,5]" :page-size="limit" layout="total, sizes, prev, pager, next, jumper"
				:total="total">
			</el-pagination>

		</div>
	</div>
</template>

<script>
	export default {
		name: "result",
		data() {
			return {
				list: [{
						name: '华中科技大学',
						message: '1'
					},
					{
						name: '华中科技大学',
						message: '2'
					},
					{
						name: '华中科技大学',
						message: '3'
					},
					{
						name: '华中科技大学',
						message: '4'
					},
					{
						name: '华中科技大学',
						message: '5'
					},
				],
				data: [],
				limit: 2,
				total: null,
				page: 1,
				pageList: [],
			}
		},
		mounted: function() {
			// alert("123");
			this.currentChangePage(this.list, 1);
			this.total = this.list.length; //需要触发的函数
		},
		methods: {


			handleSizeChange(val) {
				console.log(`每页 ${val} 条`);
				this.limit = val
				this.handleCurrentChange(this.page)
			},
			handleCurrentChange(val) {
				console.log(`当前页: ${val}`);
				this.page = val
				this.currentChangePage(this.list, val)
			},
			currentChangePage(list, currentPage) {
				let from = (currentPage - 1) * this.limit;
				let to = currentPage * this.limit;
				this.pageList = [];
				for (; from < to; from++) {
					if (list[from]) {
						this.pageList.push(list[from]);
					}
				}
				// console.log(this.pageList)
			},
			//从后台获取处理过的数据（搜索结果）
			getschooldata() {
				// let schoolName = this.$route.query.schoolName;
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
						this.list.name = this.msg.schoolName
						this.list.message=this.msg.schoolMes
					})

			}

		}
	}
</script>

<style>
	.menu {
		font-size: 17px;
	}

	.advertisement {
		display: flex;
		height: 800px;
		width: 19%;
		position: absolute;
		text-align: center;
		background-image: url(img/小王子2.jpg);
	}

	.resultbox {
		/* display: flex; */
		height: 800px;
		width: 81%;
		transform: translate(24%);
		box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 #4E655D;
	}

	.el-table__row {
		height: 130px;
	}
</style>
