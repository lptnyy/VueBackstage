<style lang="less">
	@import './users.less';
</style>
<template>
	<div class="table-div">
		<Modal @on-ok="ok" @on-cancel="cancel" :loading="loading" v-model="modal1" :title="modalTitle">
			<Form :model="formItem" :label-width="100">
				<FormItem label="用户名">
					<Input :readonly="formItem.readonly" v-model="formItem.userName" placeholder="输入用户名"></Input>
				</FormItem>
				<FormItem label="用户密码">
					<Input v-model="formItem.userPass" placeholder="输入用户密码"></Input>
				</FormItem>
				<FormItem label="重复输入密码">
					<Input v-model="formItem.reuserPass" placeholder="输入重复密码"></Input>
				</FormItem>
				<CheckboxGroup v-model="checkAllGroup" @on-change="checkAllGroupChange">
					<FormItem label="选择角色">
						<Checkbox :label="item.id" v-for="(item,index) in data2" :key="index" :name="item.roleName">
							<span>{{item.roleName}}</span>
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
		deleteUser,
		addUser,
		getOptionUserRoles,
		updateUser
	} from '@/api/user'
	import {
		getUserRoles
	} from '@/api/roles'
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
					userName: '',
					userPass: '',
					reuserPass: '',
					functions: '',
					userId: '',
					readonly: false
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
				this.addUser()
			},
			cancel(){
				
			},
			toAdd(){
				this.modalTitle = '添加'
				this.modal1 = true
				this.loading = true
				this.op = 1
				this.initRole(0)
				this.formItem.readonly = false
			},
			toEdt(index, row){
				this.modalTitle = '修改'
				this.modal1 = true
				this.loading = true
				this.op = 2
				var id = this.data1[index].id;
				this.formItem.userName = this.data1[index].userName
				this.formItem.userPass = this.data1[index].userPass
				this.formItem.reuserPass = this.data1[index].userPass
				this.formItem.userId = this.data1[index].id
				this.formItem.readonly = true
				this.initRole(id)
			},
			clearFrom(){
				this.formItem.userName = ''
				this.formItem.userPass = ''
				this.formItem.reuserPass = ''
				this.checkAllGroup = []
			},
			initRole(userId){
				var self = this;
				getUserRoles(function(result) {
					if (result.result) {
						self.data2 = result.obj
						if (self.op == 2){
							self.initOptionRoles(userId)
						}
					} else {
						self.$Message.error(result.msg);
					}
				})
			},
			initOptionRoles(userId){
				var self = this;
				getOptionUserRoles(userId,function(result) {
					if (result.result) {
						var myArray=new Array()
						for(var i =0;i<result.obj.length;i++){
							myArray[i]=result.obj[i].roleId
						}
						self.checkAllGroup = myArray
						self.formItem.functions = myArray
					} else {
						self.$Message.error(result.msg);
					}
				})
			},
			addUser(){
				var self = this;
				if (self.op == 1){
					var str = self.formItem.functions.toString();
					addUser(self.formItem.userName,self.formItem.userPass,self.formItem.reuserPass, str, function(result){
						if (result.result) {
							self.$Message.success("操作成功")
							self.loading = false
							self.modal1 = false
							self.initData(1)
							self.clearFrom()
						} else {
							self.$Message.error(result.msg)
						}
						setTimeout(() => {
							self.loading = false;
							self.$nextTick(() => {
								self.loading = true;
							});
						}, 1000);
					});
				} else {
					var str = self.formItem.functions.toString();
					updateUser(self.formItem.userId,self.formItem.userPass,self.formItem.reuserPass, str, function(result){
						if (result.result) {
							self.$Message.success("操作成功")
							self.loading = false
							self.modal1 = false
							self.initData(1)
							self.clearFrom()
						} else {
							self.$Message.error(result.msg)
						}
						setTimeout(() => {
							self.loading = false;
							self.$nextTick(() => {
								self.loading = true;
							});
						}, 1000);
					});
				}
			}
		},
		created(){
			this.breadcrumb = this.$route.meta.breadcrumb
			this.initData(1)
		}
	}
</script>
