<template>
  <div>
    <banner></banner>
    <div class="mycv">
      <div class="infoUser">
        <informationForm :infoUser="infoUser" :today="today"></informationForm>
        <saving :missions="missions" @getInfoMission="getInfoMission" @saveData="updateUserBDD"></saving>
      </div>
      <div class="mission">
        <div class="bannerMission">
          <div style="display: flex; flex-direction: row;">
            <p v-show="titleMission" style="margin: 0">Gestion des Missions</p></div>
          <div style="display: flex; flex-direction: row;margin-right: 10px" @click="showPDF=!showPDF"><div style="display: flex;margin-right:10px">
          </div>
            <img class="jumelles" src="static/png/jumelles.png" />
          </div>
        </div>
        <registermission :currentBlock="currentBlock" :titleMission="missions[currentBlock].title" :beginDate="missions[currentBlock].beginDate"
                         :client="missions[currentBlock].clientId?missions[currentBlock].clientId.label:''" :description="missions[currentBlock].description"
                         :typeM="missions[currentBlock].clientId?missions[currentBlock].typeMissions.label:''"
                         :today="today" :domain="missions[currentBlock].clientId?missions[currentBlock].clientId.domain:''" :endDate="missions[currentBlock].endDate"
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
    lastName: 'DARMET',
    firstName: 'Henri',
    birth: '1975-02-02',
    position: 'Responsable Practice Web & Java',
    experience: '30 ans',
    mail: 'henri.darmet@viseo.com',
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
          domain:""
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

          var birthDate = new Date(response.data.birth_date);
          this.infoUser = {
            id:response.data.id,
            login:response.data.login,
            lastName: response.data.lastName,
            firstName: response.data.firstName,
            birth: response.data.birth_date,
            birthDate: birthDate.getFullYear() + "-" +
            ("0" + (parseInt(birthDate.getMonth()) + 1)).slice(-2) + "-" +
            ("0" + birthDate.getDate()).slice(-2),
            position: response.data.position,
            experience: response.data.experience,
            mail: response.data.mail,
            telephone: response.data.telephone,
            hobbies: response.data.hobbies,
            languages: response.data.languages.map(
              function (elem) {
                return elem.label;
              }).join(" "),
            picture: response.data.picture,
          };
          if(response.data.missions.length==0){
            this.missions=[
              {id:0,title: "", beginDate: "",
                endDate: "", clientId:{id:0,label:"",domain:""}, description: "",typeMissions:{id:1,label:'mission'},skills:[]}
            ];

            this.currentBlock=this.missions.length-1;
            this.getInfoMission(this.missions.length-1);

          }
          else {
            this.missions = response.data.missions;
          }

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
            let newId=missions[missions.length-1].id +1;
            missions.push({id:newId,name: "", beginDate: "",
              endDate: "", client: "", description: "",type: 'mission',keyword:[]});
            this.currentBlock=newId;
            this.getInfoMission(newId);

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
        updateInfoUserPDF: function (infoUser) {
            for(let info in infoUser)console.log(infoUser[info],info)
          this.infoUser=infoUser;
        },
        closePDF: function () {
          this.showPDF=!this.showPDF
        },
        updateSector: function (sector) {
          for(let i = 0; i<this.missions.length;i++) {
            if (this.currentBlock === i) {
              this.missions[i].clientId.domain = sector;
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
        },
        updateUserBDD:function(){
          console.log(this.infoUser)
          let birth = this.infoUser.birthDate.split("-");
          console.log("cccccccccccccccccccccc", this.infoUser.birth,this.infoUser.birthDate);
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
            languages: [],
            picture: this.infoUser.picture
          };
          user.missions = this.missions;
          console.log(user.birth_date,"dedede")
          for (let i in user.missions) {
            let tabBegin = user.missions[i].beginDate.split("-");
            let tmpBegin = new Date(tabBegin[0],tabBegin[1],tabBegin[2]);
            console.log(tmpBegin,tmpBegin.getTime())
            let tabEnd = user.missions[i].endDate.split("-");
            let tmpEnd = new Date(tabEnd[0],tabEnd[1],tabEnd[2]);

            user.missions[i].beginDate=tmpBegin.getTime();
            user.missions[i].endDate=tmpEnd.getTime();
          }

          axios.post('/api/updateUser', user)
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
              ("0" + (parseInt(tmp.getMonth()) + 1)).slice(-2) + "-" +
              ("0" + tmp.getDate()).slice(-2);

            this.missions[i].endDate = tmpEnd.getFullYear() + "-" +
              ("0" + (parseInt(tmpEnd.getMonth()) + 1)).slice(-2) + "-" +
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

  .mycv{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    width: 100%;
  }

  .bannerMission{
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 2em;
    width: auto;
    background-color: #D7D7D7;
    border-top-left-radius: 8px;
    border-top-right-radius: 8px;
  }

  .bannerMission p{
    position: relative;
    left: 2.5%
  }

  .mission{
    margin-left: 0.5%;
    width: 82%;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    border: 1px solid #D7D7D7;
    border-top-left-radius: 9px;
    border-top-right-radius: 9px;
  }

  .jumelles{
    height:2.5em;
    width:100%;
    margin-right: 12cm;
  }

  .infoUser{
    width: 18%;
    display: flex;
    flex-direction: column;
  }

  #PDF{
    z-index:10;
    position: fixed;
    top: 4%;
    left: 50%;
    transform: translate(-50%, -2%);
    background-color: white;
    border : 1px solid #D7D7D7;
    height: 96%;
  }

  .grayer{
    z-index: 9;
    position: fixed;
    left: 0;
    top: 0;
    width: 100vw;
    height: 100vh;
    background-color: #A8A8A8;
    opacity: 0.7;
  }


  .closePDF {
    z-index:11;
    position: absolute;
    left: 1200px;
    top: 8px;
    width:35px;
    height:35px;
  }
  *:focus {outline: none;}
</style>
