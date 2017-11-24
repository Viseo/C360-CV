import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import config from '../src/config/config'
import router from '../src/config/router'

Vue.use(Vuex);
var jwtDecode = require('jwt-decode');

var storeInit = {
    userLogged:{
      id: -1,
      admin: false,
      birth_date: "",
      experience: '',
      firstName: "",
      hobbies: "",
      lastName: "",
      login: "",
      mail: "",
      picture: "",
      position: "",
      telephone: ""
    },
    token : ''
};

export const store = new Vuex.Store({
    state : storeInit,
    mutations: {
      setToken(state, userToken){
        state.token = userToken;
        Object.assign(state.userLogged, jwtDecode(userToken));
      },

      resetStore(state) {
        Object.assign(state, {
          userLogged:{
            id: -1,
            admin: false,
            birth_date: "",
            experience: '',
            firstName: "",
            hobbies: "",
            lastName: "",
            login: "",
            mail: "",
            picture: "",
            position: "",
            telephone: ""
          },
          token : ''
        });
      }
    },
    getters: {

    },
    actions: {
      checkIfTokenValide(context){
        console.log("TEST if token is valid");
        axios.post(config.server + '/api/identification', localStorage.getItem("token")).then(
          response => {
            if(response.data != ""){
              console.log("Token valide");
              if (response.data == "admin"){
                console.log("Admin");
                context.state.isAdmin = true;
                router.push('/admincv');
              }
              else{
                console.log("Not admin");
                context.state.isAdmin = false;
                router.push('/mycv');
              }
            }
            else{
              console.log("Token non valide");
              localStorage.removeItem("token")
              context.commit('resetStore');
              router.push('/login');
            }
          }, response => {
            console.log("Token non valide");
            localStorage.removeItem("token")
            context.commit('resetStore');
            router.push('/login');
          })
      }

    }
});

