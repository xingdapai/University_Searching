<!-- 一个实验文件 -->
<template>
	<div class="block">
		<span class="demonstration">完整功能</span>
		<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
			:current-page="currentPage4" :page-sizes="[100, 200, 300, 400]" :page-size="100"
			layout="total, sizes, prev, pager, next, jumper" :total="400">
		</el-pagination>
	</div>
</template>
<script>
	export default {
		methods: {
			handleSizeChange(val) {
				console.log(`每页 ${val} 条`);
			},
			handleCurrentChange(val) {
				console.log(`当前页: ${val}`);
			},
			getList() {
				// console.log(111)
				//  es6过滤得到满足搜索条件的展示数据list
				let list = this.data.filter((item, index) =>
					item.name.includes(this.list)
					// let list = this.data.filter((item, index) =>
					// 	item.name.includes(this.searchData)
				)
				// 过滤分页
				this.list = this.list.filter((item, index) =>
					index < this.page * this.limit && index >= this.limit * (this.page - 1)
				)
				// 分页的总数据
				// console.log(this.list)
				this.total = this.list.length
				// console.log(this.total)
			},
		},
		data() {
			return {
				currentPage1: 5,
				currentPage2: 5,
				currentPage3: 5,
				currentPage4: 4
			};
		}
	}
</script>
