import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import config from '../src/config/config'

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
    token : '',
    isAdmin : false
};

export const store = new Vuex.Store({
    state : storeInit,
    mutations: {
      setToken(state, userToken){
        state.token = userToken;
        Object.assign(state.userLogged, jwtDecode(userToken));
      },

      resetStore(state) {
        Object.assign(state, storeInit);
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
                this.state.isAdmin = true;
              }
              else{
                console.log("Not admin");
                this.state.isAdmin = false;
              }
            }
            else{
              console.log("Token non valide");
              state.token = null;
              localStorage.removeItem("token")
              context.commit('resetStore');
              this.$router.push('/login');
            }
          }, response => {
            console.log("Token non valide");
            state.token = null;
            localStorage.removeItem("token")
            context.commit('resetStore');
            this.$router.push('/login');
          })
      }

    }
});

