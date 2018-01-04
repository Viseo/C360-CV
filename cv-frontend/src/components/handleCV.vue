<template>
  <div>
    <banner :page="'Gestion CV'"></banner>
    <div class="mycv">
      <div class="infoUser">
        <informationForm></informationForm>
        <saving :missions="missions" @saveData="updateUserBDD"></saving>
      </div>
      <div class="mission">

        <transition name="fade">
          <div v-show="showMissionInfo">
            <div class="bannerMission" >
              <div style="display: flex; flex-direction: row;"><i class="fa fa-briefcase fa-lg briefcase"></i><p style="margin: 0">Gestion des Missions</p></div>
              <button class="button button-primary button-rounded" @click="showPDF=!showPDF">
                Afficher aperçu PDF<i class="fa fa-binoculars" style="padding-left: 10px"></i>
              </button>
              <button class="button button-action button-rounded" @click="addMission()">
                Sauvegarder la mission<i class="fa fa-floppy-o" style="padding-left: 10px"></i>
              </button>
            </div>
            <registermission :currentMission="currentMission"></registermission>
            <skills v-on:updateSkills="updateSkills"></skills>
          </div>
          <!--
          <registermission :currentBlock="currentBlock" :titleMission="missions[currentBlock].title" :beginDate="missions[currentBlock].beginDate"
                           :client="missions[currentBlock].clientId?missions[currentBlock].clientId.label:''" :description="missions[currentBlock].description"
                           :typeM="missions[currentBlock].clientId?missions[currentBlock].typeMissions.label:''"
                           :today="today" :domain="missions[currentBlock].clientId?missions[currentBlock].clientId.domain:''" :endDate="missions[currentBlock].endDate"
                           @updateSector="updateSector" @updateProps="updateMission"></registermission>
          -->


        </transition>
        <listMissions v-on:deleteMission="deleteMission" @showMission="showMission"></listMissions>


      </div>
    </div>
    <div v-show="showPDF" class="grayer" @click="closePDF"></div>
    <img v-show="showPDF" class="closePDF" src="../../static/png/icone-supprimer.png" @click="closePDF">
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
          infoUser: this.$store.state.userLogged,
          missions: this.$store.state.userLogged.missions,
          domain:""
        }
    },
    computed:{
      currentMission:function(){
        return this.$store.state.currentMission;
      }
    },
    methods:{
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
        console.log(this.$store.state.currentMission);
        if(this.$store.state.currentMission.id != ''){
          console.log('updating the mission...');
        }
        else{
          console.log('adding new mission...');
//          axios.post(config.server +  '/api/missions',{
//
//          }).then(function(response){
//
//          }).catch(function(error){
//
//          });
        }
      },
      deleteMission(){
          setTimeout(()=>{
            this.$store.commit('setCurrentMissionBlock', 0);
            //this.getInfoMission(0);
          },100);
      },
      updateSkills(skillsSelected){
//          for(let mission in this.missions) {
//            if (this.currentBlock === this.missions[mission].id) {
//              this.missions[mission].skills = skillsSelected;
//            }
//          }
      },
      closePDF: function () {
        this.showPDF=!this.showPDF
      },
      updateSector: function (sector) {
//          for(let i = 0; i<this.missions.length;i++) {
//            if (this.currentBlock === i) {
//              this.missions[i].clientId.domain = sector;
//              this.domain = sector;
//            }
//          }
      },
      updateMission:function(name,client,dateB,dateE,descr,type){
//            this.missions[this.currentBlock].title=name;
//            this.missions[this.currentBlock].clientId.label=client;
//            this.missions[this.currentBlock].beginDate=dateB;
//            this.missions[this.currentBlock].endDate=dateE;
//            this.missions[this.currentBlock].description=descr;
//            this.missions[this.currentBlock].typeMissions.label=type;
      },
      updateUserBDD:function(){
        let birth = this.infoUser.birthDate.split("-");
        let user = {
          id:this.infoUser.id,
          login:this.infoUser.login,
          lastName: this.infoUser.lastName,
          firstName: this.infoUser.firstName,
          birth_date: new Date(this.infoUser.birthDate).getTime(),
          position: this.infoUser.position,
          experience: this.infoUser.experience,
          mail: this.infoUser.mail,
          telephone: this.infoUser.telephone,
          hobbies: this.infoUser.hobbies,
          languages: this.infoUser.languages,
          picture: this.infoUser.picture,
          password: this.infoUser.password,
          admin: this.infoUser.admin
        };
        user.missions = this.missions;

        //TEST CODE
        user.languages = [];

        axios.post(config.server + '/api/updateUser', user)
          .then((response)=>{
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });

        for (let i in this.missions) {
          let tmp = new Date(this.missions[i].beginDate);
          let tmpEnd = new Date(this.missions[i].endDate);

          this.missions[i].beginDate = tmp.getFullYear() + "-" +
            ("0" + (parseInt(tmp.getMonth()))).slice(-2) + "-" +
            ("0" + tmp.getDate()).slice(-2);

          this.missions[i].endDate = tmpEnd.getFullYear() + "-" +
            ("0" + (parseInt(tmpEnd.getMonth()))).slice(-2) + "-" +
            ("0" + tmpEnd.getDate()).slice(-2);
        }
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
