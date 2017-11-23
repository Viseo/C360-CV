import Vue from 'vue'
import Router from 'vue-router'
import router from './config/router'
import Vuex from 'vuex'
import {store} from './store'
//import sha1 from 'sha1'
import sha256 from '../node_modules/js-sha256/src/sha256'
var jwtDecode = require('jwt-decode');

Vue.use(Router);
Vue.use(Vuex);
Vue.prototype.$sha256=sha256;




new Vue({
  el: '#app',
  router,
  store,
  template:'<div><router-view></router-view></div>'
});
