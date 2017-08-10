<template>
  <div>
    <banner></banner>
    <div style="text-align: center">
      <div class="zone-search-collab">
        <input v-model="currentSearch" id="search-collab" list="browsers" v-on:input="searchCollab">
        <datalist id="browsers">
          <option v-for="item in defaultCollab" v-bind:value="item.firstName + ' '+item.lastName" ></option>
        </datalist>
        <span class="fa fa-search icon-search" v-on:click="searchCollab"></span>
        <span class="fa fa-times icon-cancel" v-on:click="cancelSearch"></span>
      </div>
      <span id="msgErreur" style="display: none; color: red;"></span>

    </div>
    <div class="page-content">
      <filters :colors="colors" :skills="skills" :categories="categories" @updateFilters="updateFilters"></filters>
      <listCollab :collaborators="collaborators" @showPDF="showPDFUser" @downloadPDF="downloadPDF"></listCollab>
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
            for(let j of i.clients){
              if(j.includes(this.filterClients)){
                test=true;
              }
            }
            if(!test)searched=false;
          }
          //CheckSkills
          if(this.filterSkills.length>=1){
            for(let j of this.filterSkills){
              if(i.skills.indexOf(j.skill)==-1){
                searched=false;
              }
            }
          }
          if(searched) tmp.push(i);
        }
        console.log(tmp);
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
              this.defaultCollab = response.data;
            }else{
              console.log("failed");
            }
          })
          .catch((error)=>{
            console.log(error,"et oui! ");
          })
    }
  }
</script>

<style>
  .page-content{
    display:flex;
  }
  #search-collab{
    float:left;
    line-height: 2em;
    width:31em;
    margin-left:0.4em;
    border:none;
    outline:none;
  }
  .zone-search-collab{
    line-height: 2em;
    width:30em;
    border:1px solid black;
    border-radius:0.5em;
    display: inline-block;
  }
  .icon-search{
    line-height:2em;
    width:2em;
    background-color: #D7D7D7;
    float:right;
    border-top-right-radius: 0.5em;
    border-bottom-right-radius: 0.5em;
  }
  .icon-cancel{
    float:right;
    line-height:2em;
    margin-right:0.5em;
  }
</style>
