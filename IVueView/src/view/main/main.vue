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
							<MenuItem name="3-1">退出</MenuItem>
						</Submenu>
                    </div>
                </Menu>
            </Header>
            <Layout>
                <Sider hide-trigger :style="{background: '#fff'}">
                    <Menu active-name="1-2" theme="light" width="auto" :open-names="['1']">
                        <Submenu name="1">
                            <template slot="title">
                                <Icon type="ios-navigate"></Icon>
                                用戶管理
                            </template>
                            <MenuItem name="1-1" to="/users">管理員</MenuItem>
                            <MenuItem name="1-2" to="/role">角色</MenuItem>
                            <MenuItem name="1-3" to="/functions">权限</MenuItem>
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
	export default {
		data(){
			return{
				userName:'admin'
			}
		},
		components: {
			
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
			setInterval(reinitIframe, 1000);
			this.userName = userName;
		}
	}
</script>
