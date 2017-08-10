<template>
  <div>
    <banner></banner>
    <div class="mycv">
      <div class="infoUser">
        <informationForm :infoUser="infoUser" :today="today"></informationForm>

        <saving :missions="missions" @getInfoMission="getInfoMission"></saving>
      </div>
      <div class="mission">
        <div class="bannerMission">
          <div style="display: flex; flex-direction: row;"><i class="fa fa-briefcase fa-lg briefcase"></i><p style="margin: 0">Gestion des Missions</p></div>
          <div style="display: flex; flex-direction: row;margin-right: 10px" @click="showPDF=!showPDF"><div style="display: flex;margin-right: 10px">Afficher aperçu PDF</div><i class="fa fa-binoculars"></i></div>
        </div>
        <registermission :currentBlock="currentBlock" :titleMission="missions[currentBlock].title" :beginDate="missions[currentBlock].beginDate"
                         :client="missions[currentBlock].clientId?missions[currentBlock].clientId.label:''" :description="missions[currentBlock].description"
                         :typeM="missions[currentBlock].clientId?missions[currentBlock].typeMissions.label:''"
                         :today="today" :domain="missions[currentBlock].domain" :endDate="missions[currentBlock].endDate"
                         @updateSector="updateSector" @updateProps="updateMission"></registermission>

        <skills v-bind:currentSkills="missions[currentBlock].skills" :block="currentBlock" v-on:updateSkills="updateSkills"></skills>
        <listMissions @getInfoMission="getInfoMission" v-bind:missions="missions" :block="currentBlock" v-on:addMission="addMission" v-on:deleteMission="deleteMission"></listMissions>
      </div>
    </div>
    <div v-show="showPDF" class="grayer" @click="closePDF"></div>
    <img v-show="showPDF" class="closePDF" src="../../static/icone-supprimer.png" @click="closePDF">
    <curriPDF :infoPerso="infoUser" :infoMission="missions" v-show="showPDF" id="PDF"></curriPDF>
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

  let initInfoPerso = {
    name: 'DARMET',
    firstName: 'Henri',
    birth: '1975-02-02',
    fonction: 'Responsable Practice Web & Java',
    experience: '30 ans',
    email: 'henri.darmet@viseo.com',
    telephone: '0615482659',
    hobbies: 'Voyages',
    languages: 'Anglais',
    picture: '../../static/henri.png',
    age: '50',
  };

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
          infoUser: initInfoPerso,
          missions:[{id:0,name: "", beginDate: "",
            endDate: "", client: "", description: "",type: 'mission',skills:[]}],
          currentBlock:0,
        }
    },
    beforeCreate:function(){
      if(!this.$session.has("id")) {
        window.location.href = '/'
      }
    },
    created: function(){
      let date = new Date();
      let thisDay, thisMonth;
      date.getDate() < 10 ? thisDay = '0' + date.getDate() : thisDay = date.getDate();
      date.getMonth() + 1 < 10 ? thisMonth = '0' + parseInt(date.getMonth() + 1) : thisMonth = parseInt(date.getMonth() + 1);
      this.today = date.getFullYear() + '-' + thisMonth + '-' + thisDay;

      let id = this.$session.get("id");
      axios.get('http://cv360-dev.lan:8061/api/getUser', {
        params: {
          id: id
        }
      })
        .then((response) => {

          var birthDate = new Date(response.data.date_birth);
          this.infoUser = {
            name: response.data.lastName,
            firstName: response.data.firstName,
            birth: birthDate.getFullYear() + "-" +
            ("0" + (parseInt(birthDate.getMonth()) + 1)).slice(-2) + "-" +
            ("0" + birthDate.getDate()).slice(-2),
            fonction: response.data.fonction,
            experience: response.data.experience,
            email: response.data.mail,
            telephone: response.data.telephone,
            hobbies: response.data.hobbies,
            languages: response.data.languages.map(
              function (elem) {
                return elem.label;
              }).join(" "),
            picture: ""
          };

          this.missions = response.data.missions;

          for (let i in this.missions) {
            let tmp = new Date(this.missions[i].beginDate);
            let tmpEnd = new Date(this.missions[i].endDate);

            this.missions[i].beginDate = tmp.getFullYear() + "-" +
              ("0" + (parseInt(tmp.getMonth()) + 1)).slice(-2) + "-" +
              ("0" + tmp.getDate()).slice(-2);

            this.missions[i].endDate = tmpEnd.getFullYear() + "-" +
              ("0" + (parseInt(tmpEnd.getMonth()) + 1)).slice(-2) + "-" +
              ("0" + tmpEnd.getDate()).slice(-2);
          }

          this.currentBlock = 0;

        })
        .catch(function (error) {
          console.log("Error loading user \n" + error);
        });
    },
    methods:{
        getInfoMission(index){
            this.currentBlock=index;
        },
        addMission() {
            this.missions.push({id:0,title: "", beginDate: "",
              endDate: "", clientId:{id:0,label:""}, description: "",typeMissions:{id:1,label:'mission'},skills:[]});
            this.currentBlock=this.missions.length-1;
            this.getInfoMission(this.missions.length-1);
        },
        deleteMission(){
            setTimeout(()=>{
              this.currentBlock=0;
              this.getInfoMission(0);
            },100);
        },
        updateSkills(skillsSelected){
          for(let mission in this.missions) {
            if (this.currentBlock === this.missions[mission].id) {
              this.missions[mission].skills = skillsSelected;
            }
          }
        },
        closePDF: function () {
          this.showPDF=!this.showPDF
        },
        updateSector: function (sector) {
          for(let mission in this.missions) {
            if (this.currentBlock === this.missions[mission].id) {
              this.missions[mission].domain = sector;
              this.domain = sector;
            }
          }
        },
        updateMission:function(name,client,dateB,dateE,descr,type){
            this.missions[this.currentBlock].title=name;
            this.missions[this.currentBlock].clientId.label=client;
            this.missions[this.currentBlock].beginDate=dateB;
            this.missions[this.currentBlock].endDate=dateE;
            this.missions[this.currentBlock].description=descr;
            this.missions[this.currentBlock].typeMissions.label=type;
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
</style>
