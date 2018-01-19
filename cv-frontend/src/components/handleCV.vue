<template>
  <div>
    <banner :page="'Gestion CV'"></banner>
    <div class="mycv">
      <div class="infoUser">
        <informationForm :infoPerso="infoUser" :languages="languages"></informationForm>
        <saving :infoUser="infoUser" :languages="languages"></saving>
      </div>
      <div class="mission">

        <transition name="fade">
          <div v-show="showMissionInfo">
            <div class="bannerMission" style="width:100%">
              <div style="display: flex; flex-direction: row;width:60%;"><i class="fa fa-briefcase fa-lg briefcase"></i><p style="margin: 0">Gestion des Missions</p></div>
              <button class="button button-primary button-square" @click="showPDF=!showPDF" style="width:20%">
                PDF<i class="fa fa-binoculars" style="padding-left: 5px"></i>
              </button>
              <button class="button button-action button-square" @click="addMission()"
                      style="width:20%" v-show="showSaveButton == 2">
                Sauvegarder
                <i class="fa fa-floppy-o" style="padding-left: 5px"></i>
              </button>
            </div>
            <registermission :currentMission="currentMission"></registermission>
            <skills :currentSkills="currentSkills"></skills>
          </div>
        </transition>
        <listMissions v-on:deleteMission="deleteMission" @showMission="showMission"
                      @initializeSaveButton="initializeSaveButton"></listMissions>


      </div>
    </div>
    <div v-show="showPDF" class="grayer" @click="closePDF"></div>
    <curriPDF :infoPerso="infoUser" v-show="showPDF" id="PDF"></curriPDF>
  </div>
</template>

<script>
  import listMissions from './cvPage/navigationMissionsList.vue'
  import skillList from './cvPage/chooseSkills.vue'
  import banner from "./banner.vue"
  import formulaire from "./cvPage/personnalInformation.vue"
  import registerMission from './cvPage/registerMission.vue'
  import curriPDF from './PDF/curriculumPDF.vue'
  import { bus } from '../EventBus.js';
  import saveOrDownload from './cvPage/downloadPDF.vue'
  import axios from 'axios'
  import config from '../config/config'

  export default {
    components: {
      informationForm: formulaire,
      skills: skillList,
      listMissions: listMissions,
      registermission: registerMission,
      curriPDF: curriPDF,
      banner: banner,
      saving: saveOrDownload
    },
    data: function () {
        return {
          show: false,
          showPDF: false,
          showMissionInfo:false,
          domain:"",
          showSaveButton: 0,
          languages:[]
        }
    },
    computed:{
      infoUser:function(){
        return this.$store.state.userLogged;
      },
      currentMission:function(){
        return this.$store.state.currentMission;
      },
      currentSkills:function(){
        return this.$store.state.currentSkills;
      }
    },
    mounted:function(){
      axios.get(config.server + "/api/languages").then(response => {
        response.data = response.data.sort((a,b) => a.label.localeCompare(b.label))
        this.languages = response.data;
      }).catch(error => {

      });
      axios.get(config.server + "/api/missions/getMissionByUser?userId="
        + this.$store.state.userLogged.id).then(
        response => {
          var missions = response.data;
          for (let i=0;i<missions.length;i++){
            missions[i].beginDate = this.toDateString(missions[i].beginDate);
            missions[i].endDate = this.toDateString(missions[i].endDate);
          }
          this.$store.state.userLogged.missions = missions;
          console.log(this.$store.state.userLogged.missions);
        }
      ).catch( e => {
        console.log(e);
      });
      axios.get(config.server + "/api/skills").then(
        response =>{
          this.$store.state.currentSkills = response.data;
        }
      ).catch(e => {
        console.log(e);
      });
      axios.get(config.server + "/api/skillDomains").then(
        response =>{
          for (let i=0;i<response.data.length;i++){
            response.data[i].skills.sort((a,b) => a.label.localeCompare(b.label));
          }
          console.log(response.data);
          this.$store.state.skillDomains = response.data;
        }
      ).catch(e => {
        console.log(e);
      });
    },
    watch:{
      currentMission: {
        handler(val){
          console.log("change!");
          if (this.showSaveButton<2){
            this.showSaveButton++ ;
          }
        },
        deep: true
      }
    },
    methods:{
      initializeSaveButton(){
        this.showSaveButton = 0;
      },
      showMission(){
        if (!this.showMissionInfo){
          this.showMissionInfo = true;
        }
        else{
          var self = this;
          this.showMissionInfo = false;
          setTimeout(function(){ self.showMissionInfo = true; }, 100);
        }
      },
      addMission(){
        var missionToSave = this.$store.state.currentMission; // avoid date convert pb
        if(missionToSave.id != ''){
          console.log('updating the mission...');
          if(!(missionToSave.title && missionToSave.client.label
              && missionToSave.beginDate && missionToSave.endDate && missionToSave.typeMissions)){
            alert("Veuillez compeleter tous les champs obligatoires!");
          }
          console.log(missionToSave);
          axios.put(config.server + '/api/missions', missionToSave)
            .then(response =>{
              this.$store.state.currentMission.version++;
              this.showSaveButton = 0;
              console.log(this.$store.state.currentMission.version);
            })
            .catch(e => {
              console.log(e);
            })
        }
        else{
          console.log('adding new mission...');
          if(!(missionToSave.title && missionToSave.client.label
                && missionToSave.beginDate && missionToSave.endDate && missionToSave.typeMissions)){
            alert("Veuillez compeleter tous les champs obligatoires!");
          }
          else{
//            missionToSave.beginDate = this.toDateString(missionToSave.beginDate);
//            missionToSave.endDate = this.toDateString(missionToSave.endDate);
            console.log(missionToSave);
            var self = this;
            axios.post(config.server +  '/api/missions?userId=' + this.$store.state.userLogged.id, missionToSave)
              .then(response => {
                let m = response.data;
                m.beginDate = this.toDateString(m.beginDate);
                m.endDate = this.toDateString(m.endDate);
                self.$store.commit("setCurrentMission", m);
                self.$store.state.userLogged.missions.pop();
                self.$store.state.userLogged.missions.push(m);
                self.$store.state.userLogged.version++;
                this.showSaveButton = 0;
                console.log(self.$store.state.currentMission);
                //self.$store.state.userLogged.missions.push(response.data);
              })
              .catch(e => {
                console.log(e);
              });
          }
        }
      },
      deleteMission(){
          setTimeout(()=>{
            this.$store.commit('setCurrentMissionBlock', 0);
            //this.getInfoMission(0);
          },100);
      },
      closePDF: function () {
        this.showPDF=!this.showPDF
      },
      toDateString:function (date){
        var d = new Date(date),
          month = '' + (d.getMonth() + 1),
          day = '' + d.getDate(),
          year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('-');
      }
    }
  }

</script>

<style>
  .briefcase{
    position: relative;
    left: 10px;
    padding-right: 20px;
  }
  *:focus {outline: none;}
</style>
