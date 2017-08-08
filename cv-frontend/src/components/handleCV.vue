<template>
  <div>
    <banner></banner>
    <div class="mycv">
      <div class="infoUser">
        <informationForm @infoUser="updateInfoUserPDF" :infoUser="infoUser" :today="today"></informationForm>

        <saving :missions="missions" @getInfoMission="getInfoMission"></saving>
      </div>
      <div class="mission">
        <div class="bannerMission">
          <div style="display: flex; flex-direction: row;"><i class="fa fa-briefcase fa-lg briefcase"></i><p v-show="titleMission" style="margin: 0">Gestion des Missions</p></div>
          <div style="display: flex; flex-direction: row;margin-right: 10px" @click="showPDF=!showPDF"><div style="display: flex;margin-right: 10px">Afficher aperçu PDF</div><i class="fa fa-binoculars"></i></div>
        </div>
        <registermission :currentBlock="currentBlock" :titleMission="titleMission" :beginDate="beginDateMission" :endDate="endDateMission"
                         :client="client" :description="description" :typeM="type" :today="today" :domain="domain" @updateSector="updateSector" @updateProps="updateProps"></registermission>

        <skills v-bind:currentSkills="skills" :block="currentBlock" v-on:updateSkills="updateSkills"></skills>
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

  bus.$on('changeBlock', function (currentBlock) {
    for(let mission in missions){
      if(currentBlock===missions[mission].id){
        missions[mission].name=document.getElementById('Title Mission').value;
        missions[mission].client=document.getElementById('Client Form').value;
        missions[mission].beginDate=document.getElementById('Start Calendar Date').value.split('-').reverse().join('/');
        if(document.getElementById('Until Now Box').checked){
          missions[mission].endDate='';
        }else{
          missions[mission].endDate=document.getElementById('End Calendar Date').value.split('-').reverse().join('/');
        }
        missions[mission].description=document.getElementById('Description').value;
        for(let radio of document.getElementsByName('typeMission')){
          if(radio.checked){
            missions[mission].type=radio.value;
          }
        }
        break;
      }
    }
  });

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

  axios.get('/getUser', {

  })
    .then(function (response) {
      initInfoPerso=response;
    })
    .catch(function (error) {
      console.log("Error loading user \n" + error );
    });

  var missions = [
    {id: 0, name : "Orange Business Suite",beginDate:"06/05/2015",endDate:"06/06/2015",client: 'Woodix', domain:'FINANCE', description: 'Multitudine resistente multitudine capesseret est.', type: 'Séminaire',keyword:["Android","HTML5", "CSS","Javascript"]},
    {id: 1, name : "Viseo Intern",beginDate:"06/05/2016",endDate:"06/06/2016",client: '', domain:'', description: '', type: 'Mission',keyword:["Android","Agile"]},
    {id: 2, name : "Viseo Technologies",beginDate:"08/08/2016",endDate:"01/02/2017",client: 'Sony', domain:'COMMERCE', description: 'Multitudine resistente multitudine capesseret est.', type: 'Séminaire',keyword:[]},
    {id: 3, name : "Chez moi",beginDate:"06/05/2017",endDate:"",client: 'Kinder Surprise', domain:'INDUSTRIE', description: '', type: 'Séminaire',keyword:[]},
    {id: 4, name : "Viseo Technologies",beginDate:"08/08/2016",endDate:"01/02/2017",client: 'Samsung', domain:'TELECOMS', description: '', type: 'Mission',keyword:[]},
    {id: 5, name : "Chez moi",beginDate:"06/05/2017",endDate:"",client: '',  domain:'',description: 'Multitudine resistente multitudine capesseret est.', type: 'Séminaire',keyword:[]},
    {id: 6, name : "Viseo Technologies",beginDate:"08/08/2016",endDate:"01/02/2017",client: 'Dell', domain:'DISTRIBUTION', description: 'Multitudine resistente multitudine capesseret est.', type: 'Mission',keyword:[]}

  ];


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
        titleMission: missions[0].name,
        beginDateMission: missions[0].beginDate.split('/').reverse().join('-'),
        endDateMission: missions[0].endDate == ''?'now':missions[0].endDate.split('/').reverse().join('-'),
        description: missions[0].description,
        client: missions[0].client,
        domain: missions[0].domain,
        type: missions[0].type,
        currentBlock:missions[0].id,
        today: '',
        show: false,
        missions:missions,
        skills:missions[0].keyword,
        showPDF: false,
        infoUser: initInfoPerso
      }
    },
    created: function(){
      let date = new Date();
      let thisDay, thisMonth;
      date.getDate() < 10 ? thisDay = '0' + date.getDate() : thisDay = date.getDate();
      date.getMonth() + 1 < 10 ? thisMonth = '0' + parseInt(date.getMonth() + 1) : thisMonth = parseInt(date.getMonth() + 1);
      this.today = date.getFullYear() + '-' + thisMonth + '-' + thisDay;
    },
    methods:{
      updateProps(){
        this.description = document.getElementById('Description').value;
        this.titleMission = document.getElementById('Title Mission').value;
        this.client = document.getElementById('Client Form').value;
      },
      getInfoMission(id){
        let i;
        for(let mission in missions) {
          if (missions[mission].id === id){
            i=mission;
            break;
          }
        }
        if(missions[i]) {
          this.currentBlock = missions[i].id;
          this.titleMission = missions[i].name;
          this.beginDateMission = missions[i].beginDate.split('/').reverse().join('-');
          this.skills = missions[i].keyword;
          if (missions[i].endDate == '') {
            this.endDateMission = 'now';
          } else {
            this.endDateMission = missions[i].endDate.split('/').reverse().join('-');
          }
          this.client = missions[i].client;
          this.domain = missions[i].domain;
          this.description = missions[i].description;
          for (let radio of document.getElementsByName('typeMission')) {
            if (radio.value == missions[i].type) {
              radio.checked = true;
              this.type = missions[i].type;
            }
          }
        }
      },
      addMission() {
        let newId=missions[missions.length-1].id +1;
        missions.push({id:newId,name: "", beginDate: "",
          endDate: "", client: "", description: "",type: 'mission',keyword:[]});
        this.currentBlock=newId;
        this.getInfoMission(newId);
      },
      deleteMission(){
        setTimeout(()=>{
          this.currentBlock=this.missions[0].id;
          this.getInfoMission(this.missions[0].id);
        },100);
      },
      updateSkills(skillsSelected){
        for(let mission in this.missions) {
          if (this.currentBlock === this.missions[mission].id) {
            this.missions[mission].keyword = skillsSelected;
          }
        }
      },
      updateInfoUserPDF: function (infoUser) {
//            for(let info in infoUser)console.log(infoUser[info],info)
        this.infoUser=infoUser;
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
