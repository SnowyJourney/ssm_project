import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/assets/css/global.css'

// 引入组件库
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 国际化
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

createApp(App).use(store).use(router).use(ElementPlus,
    {locale: zhCn,}).mount('#app')
