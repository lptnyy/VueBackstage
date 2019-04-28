<style lang="less">
	@import './users.less';
</style>
<template>
	<div class="table-div">
		<div style="width: 100%; text-align: left;">
			<Breadcrumb :style="{margin: '24px 0'}">
				<BreadcrumbItem>Home</BreadcrumbItem>
				<BreadcrumbItem>Components</BreadcrumbItem>
				<BreadcrumbItem>Layout</BreadcrumbItem>
			</Breadcrumb>
		</div>
		<Button class="table-but" style="margin-bottom: 10px;">添加</Button>
		<ListTable class="table-bottom" :columns1="columns1" :data1="data1">
		</ListTable>
		<ListPage>
		</ListPage>
	</div>
</template>
<script>
	import ListTable from '@/components/list-table'
	import ListPage from '@/components/list-page'
	export default {
		data(){
			return {
				columns1: [
                    {
                        title: 'Name',
                        key: 'name'
                    },
                    {
                        title: 'Age',
                        key: 'age'
                    },
                    {
                        title: 'Address',
                        key: 'address'
                    },
                    {
                        title: 'Action',
                        key: 'action',
                        fixed: 'right',
                        width: 120,
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'text',
                                        size: 'small'
                                    }
                                }, 'View'),
                                h('Button', {
                                    props: {
                                        type: 'text',
                                        size: 'small'
                                    }
                                }, 'Edit')
                            ]);
                        }
                    }
                ],
				data1:[
                    {
                        name: 'John Brown',
                        age: 18,
                        address: 'New York No. 1 Lake Park',
                        date: '2016-10-03'
                    },
                    {
                        name: 'Jim Green',
                        age: 24,
                        address: 'London No. 1 Lake Park',
                        date: '2016-10-01'
                    },
                    {
                        name: 'Joe Black',
                        age: 30,
                        address: 'Sydney No. 1 Lake Park',
                        date: '2016-10-02'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    }
                ]
			}
		},
		components:{
			ListTable:ListTable,
			ListPage:ListPage
		}
	}
</script>
