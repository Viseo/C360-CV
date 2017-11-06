import Vue from 'vue'
import handleLogin from "./components/loginPage.vue"
import VueSession from 'vue-session'
//import sha1 from 'sha1'
import sha256 from '../node_modules/js-sha256/src/sha256'

Vue.use(VueSession);
Vue.prototype.$sha256=sha256;
const handleCv = resolve => {
  require.ensure([], () => {
    resolve(require('./components/handleCV.vue'))
  })
};

const handleAdmin = resolve => {
  require.ensure([], () => {
    resolve(require('./components/adminPage.vue'))
  })
};

const handleProfile = resolve => {
  require.ensure([], () => {
    resolve(require('./components/profilePage.vue'))
  })
};

const NotFound = { template: '<p><strong>Page not found</strong></p>' };

const routes = [
  { path: '/mycv', component: handleCv },
  { path: '/', component: handleLogin },
  { path: '/admincv', component : handleAdmin },
  { path: '/myprofile', component : handleProfile }
];

new Vue({
  el: '#app',
  data: {
    currentRoute: window.location.pathname,
  },
  created: function(){
    console.log('sha256 test', sha256('test'))
  },
  computed: {
    ViewComponent () {
      let comp = null;
      for(let i of routes){
        if(i.path==this.currentRoute) comp = i;
      }
      return comp?comp.component:NotFound
    }
  },
  render (h) { return h(this.ViewComponent) }
});
