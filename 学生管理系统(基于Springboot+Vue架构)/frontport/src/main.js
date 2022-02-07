import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueRouter from "vue-router";
import store from './store'
import './plugins/element.js'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);

Vue.use(VueRouter)
Vue.use(ElementUI)
Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
