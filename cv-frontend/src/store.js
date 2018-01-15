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
      telephone: "",
      missions:[],
      languages:[]
    },
    token : '',
    currentMission:{
      beginDate: "",
      endDate: "",
      title: "",
      skills: [],
      client: {},
      typeMissions: {label:''},
      id: "",
      description: ""
    },
    currentSkills:[],
    skillDomains:[]
};

export const store = new Vuex.Store({
    state : storeInit,
    mutations: {
      setCurrentMission(state, m){
        state.currentMission = m;
      },
      setToken(state, userToken){
        state.token = userToken;
        Object.assign(state.userLogged, jwtDecode(userToken));
        //convert missions date to yyyy-mm-dd type
        for (let i in state.userLogged.missions) {
          state.userLogged.missions[i].beginDate = new Date(state.userLogged.missions[i].beginDate).toUTCString();
          state.userLogged.missions[i].endDate = new Date(state.userLogged.missions[i].endDate).toUTCString();
          let tmp = new Date(state.userLogged.missions[i].beginDate);
          let tmpEnd = new Date(state.userLogged.missions[i].endDate);
          state.userLogged.missions[i].beginDate = tmp.getFullYear() + "-" +
            ("0" + (parseInt(tmp.getMonth()) + 1)).slice(-2) + "-" +
            ("0" + tmp.getDate()).slice(-2);
          state.userLogged.missions[i].endDate = tmpEnd.getFullYear() + "-" +
            ("0" + (parseInt(tmpEnd.getMonth()) + 1)).slice(-2) + "-" +
            ("0" + tmpEnd.getDate()).slice(-2);
        }
        //convert birthday to yyyy-mm-dd type
        state.userLogged.birth_date = new Date(state.userLogged.birth_date).getFullYear() + "-" +
          ("0" + (parseInt(new Date(state.userLogged.birth_date).getMonth()) + 1)).slice(-2) + "-" +
          ("0" + new Date(state.userLogged.birth_date).getDate()).slice(-2);
        // if (state.userLogged.languages != undefined){
        //   state.userLogged.languages = state.userLogged.languages.map(
        //     function (elem) {
        //       return elem.label;
        //     }).join(" ");
        // }
        // else{
        //   state.userLogged.languages = [];
        // }
      },
      setCurrentMissionBlock(state, block){
        state.currentBlock = block;
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
            telephone: "",
            missions:[],
            languages:[]
          },
          token : '',
          currentMission:{
            beginDate: "",
            endDate: "",
            title: "",
            skills: [],
            client: {},
            typeMissions: {label:'', id:''},
            id: "",
            description: "",
            version:""
          },
          currentSkills:[],
          skillDomains:[]
        });
      },
      restoreCurrentMission(state){
        Object.assign(state.currentMission,{
          beginDate: "",
          endDate: "",
          title: "",
          skills: [],
          client: {},
          typeMissions: {label:'', id:''},
          id: "",
          description: ""
        });
      },
      setCurrentMission(state,missionSelected){
        state.currentMission = missionSelected;
      }
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
              }
              else{
                console.log("Not admin");
                context.state.isAdmin = false;
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

