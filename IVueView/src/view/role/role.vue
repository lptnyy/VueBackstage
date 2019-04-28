<style lang="less">
	@import './role.less';
</style>
<template>
	<div class="table-div">
		<Modal @on-ok="ok" @on-cancel="cancel" :loading="loading" v-model="modal1" :title="modalTitle">
			<Form :model="formItem" :label-width="100">
				<FormItem label="角色名称">
					<Input v-model="formItem.name" placeholder="输入角色名称"></Input>
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
		getRoles,
		getAuthorizations,
		deleteRoles,
		updateRolesStat,
		addRole,
		getRoleFunctions,
		updateRole
	} from '@/api/roles'
	export default {
		data() {
			return {
				op: 1,
				roid: 0,
				checkAllGroup:[],
				parentList: [],
				formItem: {
					name: '',
					functions: ''
				},
				modal1: false,
				modalTitle: '',
				loading: true,
				columns1: [{
						title: '角色名称',
						key: 'roleName'
					},
					{
						title: '状态',
						key: 'statString',
						width: 160,
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
				data1: [

				],
				data2:[],
				total: 100
			}
		},
		components: {
			ListTable: ListTable,
			ListPage: ListPage
		},
		methods: {
			checkAllGroupChange(data){
				this.formItem.functions = data
			},
			pageHandle(index) {
				this.initData(index)
			},
			clearFrom(){
				this.formItem.name = ''
				this.checkAllGroup = []
			},
			initData(pageNo) {
				var self = this;
				getRoles('', pageNo, 10, function(result) {
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
			toAdd(){
				this.modalTitle = '添加'
				this.modal1 = true
				this.loading = true
				this.getAuthorization(1)
				this.clearFrom()
				this.op = 1
			},
			toEdt(index, row){
				var id = this.data1[index].id
				var name = this.data1[index].roleName
				this.modalTitle = '添加'
				this.modal1 = true
				this.loading = true
				this.clearFrom()
				this.op = 2
				this.getAuthorization(id)
				this.roid = id;
				this.formItem.name = name
			},
			getAuthorization(id){
				var self = this;
				getAuthorizations(function(result) {
					if (result.result) {
						self.data2 = result.obj
						if (self.op == 2){
							self.getRoleFunctions(id)
						}
					} else {
						self.$Message.error(result.msg);
					}
				})
			},
			getRoleFunctions(roldId){
				var self = this;
				getRoleFunctions(roldId, function(result){
					if (result.result) {
						var myArray=new Array()
						for(var i =0;i<result.obj.length;i++){
							myArray[i]=result.obj[i].functionId
						}
						self.checkAllGroup = myArray
						self.formItem.functions = myArray
					} else {
						self.$Message.error(result.msg);
					}
				})
			},
			del(index, row){
				var self = this;
				var id = this.data1[index].id;
				deleteRoles(id,function(result) {
					if (result.result) {
						self.$Message.success("操作成功");
						self.initData(1)
					} else {
						self.$Message.error(result.msg);
					}
				})
			},
			updateStat(index, row){
				var self = this;
				var id = this.data1[index].id;
				var stat = this.data1[index].stat;
				if (stat==0){
					stat=1
				} else {
					stat=0
				}
				updateRolesStat(id,stat,function(result) {
					if (result.result) {
						self.initData(1)
						self.$Message.success("操作成功");
					} else {
						self.$Message.error(result.msg)
					}
				})
			},
			ok(){
				var self = this;
				var str = self.formItem.functions.toString();
				if (self.op == 1) {
					addRole(self.formItem.name, str, function(result) {
						if (result.result) {
							self.$Message.success("操作成功")
							self.loading = false
							self.modal1 = false
							self.initData(1)
							seft.clearFrom()
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
				} else {	
					var roleId = self.roid;
					updateRole(roleId,self.formItem.name,str, function(result) {
						if (result.result) {
							self.$Message.success("操作成功")
							self.loading = false
							self.modal1 = false
							self.initData(1)
							seft.clearFrom()
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
				}
			},
			cancel(){
				
			}
		},
		created() {
			this.initData(1)
		}
	}
</script>
