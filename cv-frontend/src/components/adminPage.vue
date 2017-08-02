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
      </div>
      <div class="page-content">
          <filters :colors="colors" :skills="skills" :categories="categories" ></filters>
          <listCollab :collaborators="collaborators"></listCollab>
      </div>
      <span id="msgErreur" style="display: none; color: red;"></span>
    </div>
</template>

<script>
  import banner from "./banner.vue"
  import filters from "./adminPageComponents/filters.vue"
  import listCollab from "./adminPageComponents/listCollab.vue"
  import axios from 'axios'

  let collab = [
    {name:"Naulin",firstName:"Thomas",experience:2, poste:"senior",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["Orange","Alten"],skills:["Mobile","Web"]},
    {name:"Tranzer",firstName:"Master",experience:4, poste:"senior",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["Viseo","Bouygues"],skills:["Android","Web"]},
    {name:"Test",firstName:"Test",experience:5, poste:"manager",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["Orange","Viseo"],skills:["Mobile","Java"]},
    {name:"Balboa",firstName:"Rocky",experience:1, poste:"stagiaire",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["Renault","Bouygues"],skills:["Java","Javascript"]},
    {name:"Zarrin",firstName:"Maxime",experience:2, poste:"senior",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["Renault","Riot"],skills:["Javascript","JEE"]},
    {name:"Touati",firstName:"Farah",experience:0, poste:"stagiaire",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["Dassault","Renault"],skills:["Perl","Agile"]},
    {name:"Plouvier",firstName:"Julien",experience:4, poste:"senior",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["Renault","NASA"],skills:["Agile","C#"]},
    {name:"Short",firstName:"Edouard",experience:2, poste:"junior",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["Astek","NASA"],skills:["C++","Java"]},
    {name:"Lerandy",firstName:"Emmanuelle",experience:0, poste:"stagiaire",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["Astek","Orange"],skills:["Mobile","Web"]},
    {name:"Ouamar",firstName:"Lydia",experience:5, poste:"senior",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["MarineLand","Bouygues"],skills:["Javascript","Biodiversité Marine"]},
    {name:"Ehrmann",firstName:"Geoffrey",experience:7, poste:"manager",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["Riot","Orthofiga"],skills:["Auto-Destruction","Web"]},
    {name:"Bouvet",firstName:"Nicolas",experience:4, poste:"senior",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["Dassault","Bouygues"],skills:["C++","PHP"]},
    {name:"Riquier",firstName:"Master",experience:9, poste:"senior",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["Orange","ECE"],skills:["VHDL","Et ça ne marche pas"]},
    {name:"Bouchez",firstName:"David-Olivier",experience:11, poste:"junior",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["ECE","Inseec"],skills:["Entreprenariat","Pause"]},
    {name:"Darmet",firstName:"Henri",experience:12, poste:"junior",picture:"../../static/ReverseFlash_wallapaper.png",
      clients:["Viseo","SVG"],skills:["Tout"]}
  ];

  const skills = [
    ["Android","Ios","React Native","Xamarin"],
    ["Axure","Balsamiq","Jira","Taiga","Photoshop"],
    ["Cycle en V", "Kanban", "Lean", "Lean startup", "Less", "Rup", "Scrum", "Safe"],
    ["Angular", "Bootstrap", "CSS", "Html", "Java", "Javascript", "Python"],
    ["Apache Derb", "Microsoft Access", "Microsoft SQL Server", "MySQL", "Oracle Database", "PostgreSQL"],
    ["Bootstrap", "Cake PHP", "Google Guava", "Hibernate", "JUnit", "JQuery", "Node.js", "Laravel", "Phalcon", "PHPUnit", "Spring", "Symfony", "Zend"]
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
    },
    data: function () {
      return {
        collaborators:collab,
        currentSearch:"",
        defaultCollab:collab,
        filterPosition:[],
        filterExperience:[],
        filterClients:"",
        filterSkills:[],
        skills:skills,
        categories: categories,
        colors: colors
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
            //CheckPoste
            if((this.filterPosition.indexOf(i.poste)==-1)&&(this.filterPosition.length>=1)){
                searched=false;
            }

            //CheckExp
            if(this.filterExperience.length>=1){
                searched=false;
                for(let j of this.filterExperience){
                    if((j.down<=i.experience)&&(j.up>=i.experience)){
                        searched=true;
                    }
                }
            }

            //CheckClients
            if((i.clients.indexOf(this.filterClients)==-1)&&this.filterClients!=""){
                searched=false;
            }

            //CheckSkills
            if(this.filterSkills.length>=1){
                searched=false;
                for(let j of this.filterSkills){
                    if(i.skills.indexOf(j)!=-1){
                        searched=true;
                    }
                }
            }

            if(searched) tmp.push(i);
        }

        return tmp;
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
