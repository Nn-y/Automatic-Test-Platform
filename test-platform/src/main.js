import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import '@/assets/css/global.css'
import request from "@/utils/request";
import axios from "axios";
import ace from 'ace-builds'
import componentsInstall from './components/install'

const app = createApp(App).use(store).use(router).use(ElementPlus).use(ace).use(componentsInstall).mount('#app')
app.prototype.axios = request()

