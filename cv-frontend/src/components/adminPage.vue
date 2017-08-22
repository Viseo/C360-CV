<template>
  <div>
    <banner></banner>
    <div class="containerSearch">
      <div class="zone-search-collab">
        <span class="fa fa-search icon-search" v-on:click="searchCollab"></span>
        <input v-model="currentSearch" id="search-collab" list="browsers" v-on:input="searchCollab" placeholder="Rechercher un collaborateur">
        <datalist id="browsers">
          <option v-for="item in defaultCollab" v-bind:value="item.firstName + ' '+item.lastName" ></option>
        </datalist>
        <div class="messageErreur"><span id="msgErreur"></span></div>
        <span class="fa fa-times icon-cancel" v-on:click="cancelSearch"></span>
      </div>
    </div>
    <div class="page-content">
        <filters class="filterZone" :colors="colors" :skills="skills" :categories="categories" @updateFilters="updateFilters"></filters>
        <listCollab class="collabZone" :collaborators="collaborators" @showPDF="showPDFUser" @downloadPDF="downloadPDF"></listCollab>
    </div>
    <div v-show="showPDF" class="grayer" @click="closePDF"></div>
    <img v-show="showPDF" class="closePDF" src="../../static/icone-supprimer.png" @click="closePDF">
    <curriPDF :infoPerso="infoUser" :infoMission="missions" v-show="showPDF" id="PDF"></curriPDF>
  </div>
</template>

<script>
  import banner from "./banner.vue"
  import filters from "./adminPageComponents/filters.vue"
  import listCollab from "./adminPageComponents/listCollab.vue"
  import curriPDF from './PDF/curriculumPDF.vue'
  import axios from 'axios'

  const skills = [
    ["Android","Ios","React Native","Xamarin"],
    ["Axure","Balsamiq","Jira","Taiga","Photoshop"],
    ["Cycle en V", "Kanban", "Lean", "Lean startup", "Less", "Rup", "Scrum", "Safe"],
    ["Angular", "Bootstrap", "CSS", "Html", "Java", "Javascript", "Python", "SVG"],
    ["Apache Derb", "Microsoft Access", "Microsoft SQL Server", "MySQL", "Oracle Database", "PostgreSQL"],
    ["Bootstrap", "Cake PHP", "Google Guava", "Hibernate", "JUnit", "JQuery", "Node.js", "Laravel", "Phalcon", "PHPUnit", "Spring", "Symfony", "Zend","Vue.js"]
  ];
  const categories = [
    "MOBILE","OUTILS","MÉTHODOLOGIE","WEB","BASE DE DONNEES","FRAMEWORK"
  ];
  const colors = [
    "#00B6E8","#D13040","#7E7995","#FFC15E","#DC7633","#154360"
  ];

  export default {
    components: {
      banner:banner,
      filters:filters,
      listCollab:listCollab,
      curriPDF:curriPDF,
    },
    data: function () {
      return {
        showPDF:false,
        collaborators:[],
        currentSearch:"",
        defaultCollab:[],
        filterPosition:[],
        filterExperience:[],
        filterClients:"",
        filterSkills:[],
        skills:skills,
        categories: categories,
        colors: colors,
        infoUser: {},
        missions: {},

      }
    },
    beforeCreate:function(){
      if(!this.$session.has("id")) window.location.href = '/';
    },
    methods:{
      searchCollab(){
        let tab = [];
        let msg = document.getElementById('msgErreur');
        for(let i of this.defaultCollab){
          if((i.firstName+" "+i.lastName).toLowerCase().includes(this.currentSearch.toLowerCase())
            ||(i.lastName+" "+i.firstName).toLowerCase().includes(this.currentSearch.toLowerCase())){
            msg.style.display="none";
            tab.push(i);
          }
        }
        tab = this.applyFilters(tab);
        this.collaborators=tab;
        if(tab.length == 0){
          msg.innerHTML = "Le collaborateur que vous recherchez n'est pas référencé.";
          msg.style.display="block";
        }
      },
      cancelSearch(){
        this.currentSearch="";
        this.searchCollab();
      },
      applyFilters(tab){
        let tmp = [];
        for(let i of tab){
          let searched = true;
          //CheckExp
          if(this.filterExperience.length>=1){
            searched=false;
            for(let j of this.filterExperience){
              if(((j.down<=i.experience)&&(j.up>=i.experience))||(j.up==null&&j.down<i.experience)){
                searched=true;
              }
            }
          }
          //CheckPoste
          if((this.filterPosition.indexOf(i.position)==-1)&&(this.filterPosition.length>=1)){
            searched=false;
          }
          //CheckClients
          if(this.filterClients!=""){
            let test = false;
            for(let j of i.missions){
              let client = j.clientId.label;
              if(client.toUpperCase().trim().includes(this.filterClients.toUpperCase().trim())){
                test=true;
              }
            }
            if(!test)searched=false;
          }
          //CheckSkills
          if(this.filterSkills.length>=1){
            let tmpTab=[];

            for(let k of i.missions){
                for(let l of k.skills) {
                  tmpTab.push(l.label);
                }
            }
            for (let j of this.filterSkills) {
              if (tmpTab.indexOf(j.skill) == -1) {
                searched = false;
              }
            }

          }
          if(searched) tmp.push(i);
        }
        return tmp;
      },
      showPDFUser(infoUser){
        this.infoUser=infoUser;
        this.showPDF=true;
      },
      downloadPDF(infoUser){
        this.infoUser=infoUser;
      },
      closePDF(){
        this.showPDF=false
      },
      updateFilters(pos,exp,cli,ski){
        this.filterPosition=pos;
        this.filterSkills=ski;
        this.filterExperience=exp;
        this.filterClients=cli;
        this.searchCollab();
      }

    },
    created: function () {
        return axios.get('/api/getUsers')
          .then(response=>{
            if(response.data.length>0){
              this.collaborators = response.data;
              console.log(this.collaborators)
              this.defaultCollab = response.data;
              for(let collab in this.collaborators){
                  this.collaborators[collab].languages=this.collaborators[collab].languages.map(
                    function (elem) {
                      return elem.label;
                    }).join(", ");
              }
            }else{
              console.log("failed");
            }
          })
          .catch((error)=>{
            console.log(error);
          })
    }
  }
</script>

<style>

  .containerSearch {
  display: flex;
  justify-content: center;
  }
  #search-collab{
    line-height: 2em;
    width:31em;
    border:none;
    outline:none;
  }
  .zone-search-collab{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    align-self: center;
    width:50em;
    height: 2.3em;
    border:1.3px solid dodgerblue;
    border-radius: 30px;
    padding: 0 1em;
  }
  .icon-search{
    width:2em;
    color: dodgerblue;
  }
  .messageErreur{
    display: flex;
    align-items: center;
    height: 1.8em;
    width: 30em;
    padding-left: 1em;
    margin-left: 1em;
    border: none;
    border-left: 1.5px solid lightgray;
  }

  #msgErreur{
    color: red;
  }

  .page-content{
    display: flex; flex-direction: row; justify-content: space-around; width: 100%; margin-top: 1em;
  }

  .filterZone{
    display: flex;
    flex-basis: 23%;
  }

  .collabZone{
    display: flex;
    flex-basis: 73%;
  }

</style>
