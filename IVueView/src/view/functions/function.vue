<style lang="less">
	@import './function.less';
</style>
<template>
	<div class="table-div">
		<Modal @on-ok="ok" @on-cancel="cancel" :loading="loading" v-model="modal1" :title="modalTitle">
			<Form :model="formItem" :label-width="100">
				<FormItem label="栏目名称">
					<Input v-model="formItem.name" placeholder="输入栏目名称"></Input>
				</FormItem>
				<FormItem label="访问路径">
					<Input v-model="formItem.url" placeholder="输入访问路径"></Input>
				</FormItem>
				<FormItem label="栏目类型">
					<Select v-model="formItem.type" @on-change="typeOnChange">
						<Option value="0">模块</Option>
						<Option value="1">页面</Option>
					</Select>
				</FormItem>
				<FormItem label="父栏目">
					<Select v-model="formItem.parentId">
						<Option v-for="item in parentList" :value="item.id" :key="item.id">{{ item.name }}</Option>
					</Select>
				</FormItem>
			</Form>
		</Modal>
		<div style="width: 100%; text-align: left;">
			<Breadcrumb :style="{margin: '24px 0'}">
				<BreadcrumbItem>Home</BreadcrumbItem>
				<BreadcrumbItem>Components</BreadcrumbItem>
				<BreadcrumbItem>Layout</BreadcrumbItem>
			</Breadcrumb>
		</div>
		<Button @click="toAdd" type="primary" class="table-but" style="margin-bottom: 10px;">添加</Button>
		<ListTable class="table-bottom" :columns1="columns1" :data1="data1">
		</ListTable>
		<ListPage :total="total" v-on:pageHandle="pageHandle">
		</ListPage>
	</div>
</template>
<script>
	import ListTable from '@/components/list-table'
	import ListPage from '@/components/list-page'
	import {
		getFunctions,
		deleteFunction,
		updateFunctionStat,
		getParentFunctions,
		addFunctions
	} from '@/api/functions'
	export default {
		data() {
			return {
				parentList: [],
				formItem: {
					name: '',
					type: '',
					url: '',
					parentId: ''
				},
				modal1: false,
				modalTitle: '',
				loading: true,
				columns1: [{
						title: '名称',
						key: 'name'
					},
					{
						title: '路径',
						key: 'url'
					},
					{
						title: '栏目类型',
						key: 'typeName'
					},
					{
						title: '父菜单',
						key: 'parentName'
					},
					{
						title: '状态',
						key: 'statString'
					},
					{
						title: '操作',
						key: 'action',
						fixed: 'right',
						width: 120,
						render: (h, params) => {
							return h('div', [
								h('Button', {
									props: {
										type: 'warning',
										size: 'small'
									},
									style: {
										marginRight: '2px'
									},
									on: {
										click: () => {
											this.edit(params.index, params.row)
										}
									}
								}, this.checkStat(params.index)),
								h('Button', {
									props: {
										type: 'error',
										size: 'small'
									},
									on: {
										click: () => {
											this.del(params.index, params.row)
										}
									}
								}, '删除')
							]);
						}
					}
				],
				data1: [

				],
				total: 100
			}
		},
		components: {
			ListTable: ListTable,
			ListPage: ListPage
		},
		methods: {
			typeOnChange(value) {
				var selt = this;
				if (value == '1') {
					getParentFunctions(function(result) {
						if (result.result) {
							selt.parentList = result.obj
						}
					})
				} else {
					selt.parentList = []
					selt.formItem.parentId = ''
				}
			},
			pageHandle(index) {
				this.initData(index)
			},
			initData(pageNo) {
				var self = this;
				getFunctions('', pageNo, 10, function(result) {
					if (result.result) {
						self.data1 = result.obj
						self.total = result.dataCount
					} else {
						self.$Message.error(result.msg);
					}
				})
			},
			checkStat(index) {
				var stat = this.data1[index].stat;
				if (stat == 0) {
					return '注销'
				} else {
					return '恢复'
				}
			},
			edit(index, row) {
				var stat = this.data1[index].stat
				var id = this.data1[index].id
				var self = this;
				if (stat == 0) {
					stat = 1
				} else {
					stat = 0
				}
				updateFunctionStat(id, stat, function(result) {
					if (result.result) {
						self.$Message.success("操作成功");
						self.initData(1)
					} else {
						self.$Message.error(result.msg);
					}
				})
			},
			del(index, row) {
				var self = this;
				var id = this.data1[index].id;
				deleteFunction(id, function(result) {
					if (result.result) {
						self.$Message.success("操作成功");
						self.initData(1)
					} else {
						self.$Message.error(result.msg);
					}
				})
			},
			toAdd() {
				this.modalTitle = '添加'
				this.modal1 = true
				this.loading = true
			},
			ok() {
				var self = this;
				addFunctions(this.formItem.name, this.formItem.url, this.formItem.type, this.formItem.parentId, function(result) {
					if (result.result) {
						self.$Message.success("操作成功")
						self.loading = false
						self.modal1 = false
						self.initData(1)
					} else {
						self.$Message.error(result.msg)
					}
					setTimeout(() => {
						self.loading = false;
						self.$nextTick(() => {
							self.loading = true;
						});
					}, 1000);
				})
			},
			cancel() {

			}
		},
		created() {
			this.initData(1)
		}
	}
</script>
