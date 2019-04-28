import Vue from 'vue/dist/vue.js';
import App from './App.vue'
import router from './router'
import iView from 'iview'
import './index.less'
import Vuex from 'vuex'
Vue.use(iView);
Vue.use(Vuex);
new Vue({
	router,
  render: h => h(App),
}).$mount('#app')
