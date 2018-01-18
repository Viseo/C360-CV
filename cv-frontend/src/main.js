import Vue from 'vue'
import Router from 'vue-router'
import router from './config/router'
import Vuex from 'vuex'
import {store} from './store'
import sha256 from '../node_modules/js-sha256/src/sha256'

// for vue-notification setup
import VueNotifications from 'vue-notifications'
import iziToast from 'izitoast'// https://github.com/dolce/iziToast
import 'izitoast/dist/css/iziToast.min.css'

function toast ({title, message, type, timeout, cb}) {
  if (type === VueNotifications.types.warn) type = 'warning'
  return iziToast[type]({title, message, timeout})
}

const options = {
  success: toast,
  error: toast,
  info: toast,
  warn: toast
}

Vue.use(VueNotifications, options)

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
