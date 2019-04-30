<style lang="less">
	@import './users.less';
</style>
<template>
	<div class="table-div">
		<Modal @on-ok="ok" @on-cancel="cancel" :loading="loading" v-model="modal1" :title="modalTitle">
			<Form :model="formItem" :label-width="100">
				<FormItem label="用户名">
					<Input v-model="formItem.name" placeholder="输入用户名"></Input>
				</FormItem>
				<FormItem label="用户密码">
					<Input v-model="formItem.password" placeholder="输入用户密码"></Input>
				</FormItem>
				<FormItem label="重复输入密码">
					<Input v-model="formItem.repassword" placeholder="输入重复密码"></Input>
				</FormItem>
				<CheckboxGroup v-model="checkAllGroup" @on-change="checkAllGroupChange">
					<FormItem :label="item.name" v-for="(item,index) in data2" :key="index" :name="item.name">
						<Checkbox :label="item2.id" v-for="(item2,index2) in item.functionVoList" :key="index2" :name="item2.name">
							<span>{{item2.name}}</span>
						</Checkbox>
					</FormItem>
				</CheckboxGroup>
			</Form>
		</Modal>
		<div style="width: 100%; text-align: left;">
			<Breadcrumb :style="{margin: '24px 0'}">
				<BreadcrumbItem v-for="(item,index) in breadcrumb" :key="index">
					{{item.name}}
				</BreadcrumbItem>
			</Breadcrumb>
		</div>
		<Button @click="toAdd" class="table-but" type="primary" style="margin-bottom: 10px;">添加</Button>
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
		getUsers,
		updateStat,
		deleteUser
	} from '@/api/user'
	export default {
		data(){
			return {
				columns1: [
                    {
                        title: '账号',
                        key: 'userName'
                    },
                    {
                        title: '登录次数',
                        key: 'loginNum'
                    },
                    {
                        title: '状态',
                        key: 'statString',
                    },
                   {
                   	title: '操作',
                   	key: 'action',
                   	fixed: 'right',
                   	width: 160,
                   	render: (h, params) => {
                   		return h('div', [
                   			h('Button', {
                   				props: {
                   					type: 'primary',
                   					size: 'small'
                   				},
                   				style: {
                   					marginRight: '2px'
                   				},
                   				on: {
                   					click: () => {
                   						this.toEdt(params.index, params.row)
                   					}
                   				}
                   			}, '修改'),
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
                   						this.updateStat(params.index, params.row)
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
				data1:[],
				total: 100,
				breadcrumb:[],
				modal1: false,
				modalTitle: '',
				loading: true,
				formItem: {
					name: '',
					password: '',
					repassword: '',
					functions: ''
				},
				checkAllGroup:[],
				data2:[]
			}
		},
		components:{
			ListTable:ListTable,
			ListPage:ListPage
		},
		methods:{
			initData(pageNo){
				var self = this;
				getUsers('', pageNo, 10, function(result) {
					if (result.result) {
						self.data1 = result.obj
						self.total = result.dataCount
					} else {
						self.$Message.error(result.msg);
					}
				})
			},
			pageHandle(index) {
				this.initData(index)
			},
			checkStat(index) {
			 	var stat = this.data1[index].stat;
			 	if (stat == 0) {
			 		return '注销'
			 	} else {
			 		return '恢复'
			 	}
			},
			updateStat(index, row){
				var self = this;
				var stat = this.data1[index].stat
				var id = this.data1[index].id
				if (stat == 0) {
					stat = 1
				} else {
					stat = 0
				}
				updateStat(id, stat, function(result) {
					if (result.result) {
						self.$Message.success("操作成功");
						self.initData(1)
					} else {
						self.$Message.error(result.msg);
					}
				})
			},
			del(index, row){
				var self = this;
				var id = this.data1[index].id
				deleteUser(id, function(result) {
					if (result.result) {
						self.$Message.success("操作成功");
						self.initData(1)
					} else {
						self.$Message.error(result.msg);
					}
				})
			},checkAllGroupChange(data){
				this.formItem.functions = data
			},
			ok(){
				
			},
			cancel(){
				
			},
			toAdd(){
				this.modalTitle = '添加'
				this.modal1 = true
				this.loading = true
			},
			toEdt(index, row){
				this.modalTitle = '修改'
				this.modal1 = true
				this.loading = true
			}
		},
		created(){
			this.breadcrumb = this.$route.meta.breadcrumb
			this.initData(1)
		}
	}
</script>
