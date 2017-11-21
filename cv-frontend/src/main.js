import Vue from 'vue'
import Router from 'vue-router'
import VueSession from 'vue-session'
import router from './config/router'
/*
import handleLogin from "./components/loginPage.vue"
import handleCv from "./components/handleCV.vue"
import handleAdmin from "./components/adminPage.vue"
import handleProfile from "./components/profilePage.vue"
*/
//import sha1 from 'sha1'
import sha256 from '../node_modules/js-sha256/src/sha256'
var jwtDecode = require('jwt-decode');

Vue.use(VueSession);
Vue.use(Router);
Vue.prototype.$sha256=sha256;




new Vue({
  el: '#app',
  router,
  template:'<div><router-view></router-view></div>'
});
