<style lang="less">
	@import './main.less';
</style>
<template>
	<div class="layout" id="layout">
        <Layout>
            <Header>
                <Menu mode="horizontal" theme="dark" active-name="1">
                    <div class="layout-logo">
						
					</div>
                    <div class="layout-nav">
					    <Submenu name="1">
							<template slot="title">
								<Icon type="md-person" />
								{{userName}}
							</template>
							<MenuItem name="2-1" @click.native="reLogin()">退出</MenuItem>
						</Submenu>
                    </div>
                </Menu>
            </Header>
            <Layout>
                <Sider hide-trigger :style="{background: '#fff'}">
                    <Menu active-name="1-2" theme="light" width="auto" :open-names="['1']">
                        <Submenu v-for="(item,index) in mainfunctions" :key="index" :name="item.id">
                            <template slot="title">
                                <Icon type="ios-navigate"></Icon>
								{{item.name}}
                            </template>
                            <MenuItem v-for="(item2,index2) in item.functions" :key="index2" :name="item2.id" :to="item2.url">
								{{item2.name}}
							</MenuItem>
                        </Submenu>
                    </Menu>
                </Sider>
                <Layout :style="{padding: '0 24px 24px'}">
                    <Content :style="{padding: '24px', minHeight: '600px', background: '#fff'}">
                        <router-view></router-view>
                    </Content>
                </Layout>
            </Layout>
        </Layout>
    </div>
</template>
<script>
	import $ from "jquery";
	import router from '../../router'
	export default {
		data(){
			return{
				userName:'admin',
				mainfunctions:[]
			}
		},
		components: {
			
		},
		methods:{
			reLogin(){
				localStorage.removeItem("token")
				localStorage.removeItem("userId")
				localStorage.removeItem("userName")
				router.push({
					name: "login"
				})
			}
		},
		mounted() {
			var userName = localStorage.getItem("userName")
			function reinitIframe() {
				try {
					$("#layout").height($(window).height());
					console.log(mainheight)
					console.log($("#layout"))
					$("layout").height(mainheight)
				} catch (ex) {}
			};
			setInterval(reinitIframe, 1000)
			this.userName = userName
			this.mainfunctions = JSON.parse(localStorage.getItem('functions'));
		}
	}
</script>
