import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import config from '../src/config/config'
import router from '../src/config/router'

Vue.use(Vuex);

var storeInit = {
    userLogged:{
      version:0,
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
      setUser(state, user){
        Object.assign(state.userLogged, user);
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
            version:0,
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
        axios.get(config.server + '/api/identification?token=' + localStorage.getItem("token")).then(
          response => {
            if(response.data != null){
              console.log("Token valide");
              context.commit('setUser',response.data);
              if(context.state.userLogged.admin){
                router.push('/admincv');
              }
              else{
                router.push('/mycv');
              }
            }
            else{
              console.log("Token non valide");
              localStorage.removeItem("token")
              context.commit('resetStore');
              router.push('/login');
            }
          }).catch(e => {
          console.log("token non valide" + e);
          localStorage.removeItem("token")
          context.commit('resetStore');
          router.push('/login');
        })
      }

    }
});

