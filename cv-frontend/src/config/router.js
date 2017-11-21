import Vue from 'vue'
import Router from 'vue-router'
import handleLogin from "./../components/loginPage.vue"
import handleCv from "./../components/handleCV.vue"
import handleAdmin from "./../components/adminPage.vue"
import handleProfile from "./../components/profilePage.vue"
Vue.use(Router);


const routes = [
    {
      path: "/",
      redirect: "/login"
    },
    {
      path: '/login',
      component: handleLogin
    },
    {
      path: '/mycv',
      component: handleCv
    },
    {
      path: '/admincv',
      component : handleAdmin
    },
    { path: '/myprofile',
      component : handleProfile
    }
];

const router = new Router({
  routes
});
export default router;


