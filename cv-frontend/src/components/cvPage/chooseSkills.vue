<template>
  <div>
    <div class="head-component">
      <div class="title-skills">
        <i class="fa fa-anchor" style="margin-left:1vw;"></i>
        <span class="title-2" style="font-family: Bradley Hand ITC, cursive; font-weight: bold; margin-left: 1% ">Mots-clés</span>
      </div>
      <div class="zone-search-skill">
        <input type=text maxlength="25" class="search-skill" placeholder="Recherche" v-model="search"
               v-on:keyup.enter="searchSkill" v-on:keyup.delete="cancelSearch">
        <div class="result-search" v-bind:style="colorSearch">{{ resultSearch }}</div>
        <i class="fa fa-search picto-search" v-on:click="searchSkill"></i>
        <i class="fa fa-times fa-2x picto-cancel" v-on:click="deleteSearch"></i>
      </div>
    </div>
    <div id="tab-skills" style="width:80vw;height:20vh;overflow-y: scroll;overflow-x: hidden;" class="container-cv">
      <transition-group name="transition-skills" tag="p">
        <div v-for="(item,index) in categories" v-bind:key="item">
          <div v-bind:style="findColor(item)" class="categorie-style" v-on:click="toggleActive(item)">
            {{ categories[index] }}
          </div>
            <transition name="fade">
              <div v-if="findActive(item)" class="skill-list" v-bind:style="applyHeight(item)" >
                <a v-for="(i,num) in findSkills(item)" class="skill-style" v-bind:style="backgroundSelected(i)" v-on:click="select(i)"
                   v-on:mouseover="changeColorToSelec" v-on:mouseleave="changeColorToUnselec(i,$event)">
                  <span>{{ i }}</span>
                  <i v-if="checkSelected(i)" v-bind:class="checked" class="icon-skill"></i>
                  <i v-else v-bind:class="unchecked" class="icon-skill" style="color:black"></i>
                </a>
              </div>
            </transition>
        </div>
      </transition-group>
    </div>
  </div>
</template>

<script>
  import { bus } from '../../EventBus.js';

  let skills = [
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
    props:["currentSkills","block"],
    data: function () {
      return {
        skills: skills,
        defaultSkills :[
          ["Android","Ios","React Native","Xamarin"],
          ["Axure","Balsamiq","Jira","Taiga","Photoshop"],
          ["Cycle en V", "Kanban", "Lean", "Lean startup", "Less", "Rup", "Scrum", "Safe"],
          ["Angular", "Bootstrap", "CSS", "Html", "Java", "Javascript", "Python"],
          ["Apache Derb", "Microsoft Access", "Microsoft SQL Server", "MySQL", "Oracle Database", "PostgreSQL"],
          ["Bootstrap", "Cake PHP", "Google Guava", "Hibernate", "JUnit", "JQuery", "Node.js", "Laravel", "Phalcon", "PHPUnit", "Spring", "Symfony", "Zend"]
        ],
        categories : categories,
        colors : colors,
        checked : "fa fa-check-circle-o fa-2x",
        unchecked: "fa fa-plus-circle fa-2x",
        items : [false, false, false, false,false,false],
        resultSearch:"Recherche Vide",
        colorSearch:"color:white",
        search:"",
        idMission:this.block
      }
    },
    methods: {
      findColor(cat){
        return "background-color:" + colors[categories.indexOf(cat)];
      },
      applyHeight(cat){
        let space = 11;
        return "height:" + (Math.ceil(skills[categories.indexOf(cat)].length / 6) * space) + "vh;";
      },
      toggleActive(cat){
        this.items.splice(categories.indexOf(cat), 1, !this.items[categories.indexOf(cat)]);
      },
      findActive(cat){
        return this.items[categories.indexOf(cat)];
      },
      findSkills(cat){
        return this.skills[categories.indexOf(cat)];
      },
      checkSelected(skill){
        return this.currentSkills.indexOf(skill) != -1;
      },
      backgroundSelected(skill){
        return "background-color:" + (this.currentSkills.indexOf(skill) == -1 ? "white;" : "#50BDAC;") +
          "color:" + (this.currentSkills.indexOf(skill) == -1 ? "black;" : "white;");
      },
      select(skill){
        if (this.currentSkills.indexOf(skill) == -1) this.currentSkills.push(skill);
        else {
          this.currentSkills.splice(this.currentSkills.indexOf(skill), 1);
        }
        this.updateSkills();
      },
      changeColorToSelec(e){
        if (e.target.classList.value.includes("skill-style")) e.target.style.backgroundColor = "#50BDAC";
        else if (e.path[1].classList.value.includes("skill-style")) e.path[1].style.backgroundColor = "#50BDAC";
      },
      changeColorToUnselec(skill, e){
        if (this.currentSkills.indexOf(skill) == -1) {
          if (e.target.classList.value.includes("skill-style")) e.target.style.backgroundColor = "white";
          else if (e.path[1].classList.value.includes("skill-style")) e.path[1].style.backgroundColor = "white";
        }
      },
      searchSkill(){
        if (this.search != "") {
          let done = false;
          for (let i in categories) {
            if (categories[i].toLowerCase() == this.search.toLowerCase()) {
              this.categories = [categories[i]];
              this.resultSearch = "Recherche réussie";
              this.colorSearch = "color:green";
              this.items.splice(i, 1, true);
              done = true;
              break;
            }
          }

          for (let i in this.skills) {
            for (let j in this.skills[i]){
              if (this.skills[i][j].toLowerCase() == this.search.toLowerCase()) {
                this.skills[i] = [this.skills[i][j]];
                this.categories = [categories[i]];
                this.resultSearch = "Recherche réussie";
                this.colorSearch = "color:green";
                this.items.splice(i, 1, true);
                done = true;
                break;
              }
            }
          }

          if (!done) {
            this.categories = [];
            this.resultSearch = "Aucun Résultat";
            this.colorSearch = "color:red";
          }
        }
        else {
          this.categories = categories;
          this.skills = this.defaultSkills;
          this.resultSearch = "Recherche vide";
          this.colorSearch = "color:white";
          this.items = [false, false, false, false,false,false];
        }
      },
      deleteSearch(){
        this.search = "";
        this.categories = categories;
        this.resultSearch = "Recherche vide";
        this.colorSearch = "color:white";
        this.items = [false, false, false, false,false,false];
        this.resetSkills();
      },
      cancelSearch(){
        if (this.search == "") {
          this.categories = categories;
          this.resultSearch = "Recherche vide";
          this.colorSearch = "color:white";
          this.items = [false, false, false, false,false,false];
          this.resetSkills();
        }
      },
      resetSkills(){
        this.skills = [];
        for (let i in this.defaultSkills) {
          this.skills.push([]);
          for (let j in this.defaultSkills[i]) {
            this.skills[i].push(this.defaultSkills[i][j]);
          }
        }
      },
      updateSkills: function () {
       this.$emit('updateSkills',this.currentSkills);
      }
    }
  }


</script>

<style>
  .categorie-style{
    display: flex;
    width: 97%;
    height:7vh;
    margin-top: 0.5vh;
    margin-bottom: 0.5vh;
    margin-left: 1vw;
    margin-right: 1vw;
    position:relative;
    color:white;
    justify-content: center;
    line-height: 7vh;
    font-family: "Arial", Arial, sans-serif;
    font-size:3vh;
  }

  .skill-style{
    width : 11vw;
    height : 8vh;
    margin : 0.5vw;
    border:2px solid #50BDAC;
    border-radius:10px;
    float:left;
    position:relative;
    text-align:center;
    line-height:8vh;
    font-family: "Arial", Arial, sans-serif;
    font-size:2vh;
  }

  .skill-list{
    margin-left : 1vw;
  }

  .icon-skill{
    position : relative;
    float:right;
    right:0.4vw;
    color:white;
    line-height:8vh;
  }

  .title-2{
    color:dodgerblue;
    font-size:3.5vh;
  }

  .search-skill{
    padding-left:1vw;
    border:none;
    outline: none;
    width:10vw;
    display:inline-block;
  }

  .zone-search-skill{
    position:relative;
    float:right;
    right:-1px;
    height:5vh;
    display:inline;
    width:27vw;
    border-bottom:none;
    border-top:1px solid lightgrey;
    border-left:1px solid lightgrey;
    border-right:1px solid lightgrey;
  }

  .result-search{
    display:inline-block;
    margin-top:2vh;
    padding-left:1vw;
    border-left:1px solid lightgrey;
    color:white;
    font-size:2vh;
  }

  .picto-search{
    margin-left:1vw;
    float:right;
    line-height:5vh;
    background-color:#EEE;
    padding-left:1vw;
    padding-right:1vw;
  }

  .picto-cancel{
    float:right;
    line-height:5vh;
  }

  .head-component{
    width:80vw;
  }

  .title-skills{
    display:inline;
  }

  .transition-skills-move{
    transition: transform 1s;
  }

  .fade-enter-active{
    transition: opacity .5s
  }
  .fade-enter ,.fade-leave-to{
    opacity: 0
  }

  .fade-leave-active{
    transition: opacity .5s;
  }

</style>
