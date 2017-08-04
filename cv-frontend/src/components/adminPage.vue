<template>
  <div>
      <banner></banner>
      <div style="text-align: center">
          <div class="zone-search-collab">
            <input v-model="currentSearch" id="search-collab" list="browsers" v-on:input="searchCollab">
            <datalist id="browsers">
              <option v-for="item in defaultCollab" v-bind:value="item.firstName + ' '+item.name" ></option>
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
  // TODO : Unmock dataset
  let collab = [
    {name:"Naulin",firstName:"Thomas",experience:2, poste:"Sénior",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0154879565', email:'thomas.naulin@viseo.com', hobbies: 'pictor',languages:'Anglais', clients:["Orange","Alten"],skills:["Mobile","Web"]},
    {name:"Tranzer",firstName:"Master",experience:4, poste:"Sénior",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0123456789', email:'tranzer@chelou.com', hobbies: 'orthphoniste',languages:'Français, mais vite fait',clients:["Viseo","Bouygues"],skills:["Android","Web"]},
    {name:"Test",firstName:"Test",experience:5, poste:"Manager",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0635897456', email:'test@test.try', hobbies: 'mocha',languages:'coffeescript',clients:["Orange","Viseo"],skills:["Mobile","Java"]},
    {name:"Balboa",firstName:"Rocky",experience:1, poste:"Stagiaire",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0879461325', email:'tin.tintintin@tintin.tiiin', hobbies: 'Boxe',languages:'Argot',clients:["Renault","Bouygues"],skills:["Java","Javascript"]},
    {name:"Zarrin",firstName:"Maxime",experience:2, poste:"Sénior",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0669362584', email:'maxime.zarrin@viseo.com', hobbies: 'LOL, Flame, flame on LOL',languages:'Anglais',clients:["Renault","Riot"],skills:["Javascript","JEE"]},
    {name:"Touati",firstName:"Farah",experience:0, poste:"Stagiaire",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0215487926', email:'touati.farah@viseo.com', hobbies: 'lecture',languages:'Anglais, Arabe',clients:["Dassault","Renault"],skills:["Perl","Agile"]},
    {name:"Plouvier",firstName:"Julien",experience:4, poste:"Sénior",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0254896544', email:'plouvier.julien@edu.ece.fr', hobbies: 'enseignement',languages:'Anglais',clients:["Renault","NASA"],skills:["Agile","C#"]},
    {name:"Short",firstName:"Edouard",experience:2, poste:"Junior",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0615958432', email:'edouard.long@viseo.com', hobbies: 'Algo',languages:'Anglais',clients:["Astek","NASA"],skills:["C++","Java"]},
    {name:"Lerandy",firstName:"Emmanuelle",experience:0, poste:"Stagiaire",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0528649751', email:'emma.lerandy@viseo.com', hobbies: 'handball, kine',languages:'Anglais',clients:["Astek","Orange"],skills:["Mobile","Web"]},
    {name:"Ouamar",firstName:"Lydia",experience:5, poste:"Sénior",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0154879565', email:'lydia@viseo.com', hobbies: 'ocean,design',languages:'Anglais',clients:["MarineLand","Bouygues"],skills:["Javascript","Biodiversité Marine"]},
    {name:"Ehrmann",firstName:"Geoffrey",experience:7, poste:"Manager",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0123456987', email:'ouam@viseo.com', hobbies: 'pizza',languages:'Anglais',clients:["Riot","Orthofiga"],skills:["Auto-Destruction","Web"]},
    {name:"Bouvet",firstName:"Nicolas",experience:4, poste:"Sénior",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '05478965411', email:'nico.bouvet@viseo.com', hobbies: 'rien',languages:'Anglais',clients:["Dassault","Bouygues"],skills:["C++","PHP"]},
    {name:"Riquier",firstName:"Master",experience:9, poste:"Sénior",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0235854123', email:'riquier@ece.fr', hobbies: 'elec, nage',languages:'Anglais',clients:["Orange","ECE"],skills:["VHDL","Et ça ne marche pas"]},
    {name:"Bouchez",firstName:"David-Olivier",experience:11, poste:"Junior",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0658965478', email:'do.bouchez@edu.ece.fr', hobbies: 'PFE, VPE',languages:'Anglais',clients:["ECE","Inseec"],skills:["Entreprenariat","Pause"]},
    {name:"Darmet",firstName:"Henri",experience:12, poste:"Junior",picture:"../../static/ReverseFlash_wallapaper.png",
      telephone: '0658742589', email:'henri.darmet@viseo.com', hobbies: 'SVG',languages:'Anglais',clients:["Viseo","SVG"],skills:["Tout"]}
  ];

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
        collaborators:collab,
        currentSearch:"",
        defaultCollab:collab,
        filterPosition:[],
        filterExperience:[],
        filterClients:"",
        filterSkills:[],
        skills:skills,
        categories: categories,
        colors: colors,
        infoUser: {},
        missions: {}
      }
    },
    methods:{
      searchCollab(){
        let tab = [];
        let msg = document.getElementById('msgErreur');
        for(let i of collab){
            if((i.firstName+" "+i.name).toLowerCase().includes(this.currentSearch.toLowerCase())
              ||(i.name+" "+i.firstName).toLowerCase().includes(this.currentSearch.toLowerCase())){
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
            if((this.filterPosition.indexOf(i.poste)==-1)&&(this.filterPosition.length>=1)){
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
