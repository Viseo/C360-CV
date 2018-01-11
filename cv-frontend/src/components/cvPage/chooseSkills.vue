<template>
  <div>
    <div class="head-component">
      <div class="title-skills">
        <i class="fa fa-anchor fa-2x"></i>
        <span class="title-2" style="font-family: Arial, cursive; font-weight: bold; margin-left: 1%; width: 20% ">Mots-clés</span>
      </div>
      <div class="zone-search-skill">
        <i class="fa fa-search picto-search" v-on:click="searchSkill"></i>
        <input type=text maxlength="25" class="search-skill" placeholder="Recherche" v-model="search"
               v-on:keyup.enter="searchSkill" v-on:keyup.delete="cancelSearch">
        <transition name="fade">
          <div class="result-search" v-bind:style="colorSearch" v-if="resultSearch!='Recherche vide'">
            {{ resultSearch }}
          </div>
        </transition>

        <i class="fa fa-times fa-2x picto-cancel" v-on:click="deleteSearch"></i>
      </div>
    </div>
    <div id="tab-skills" class="containerCv">
      <transition-group name="transition-skills">
        <div v-for="item in categories" v-bind:key="item.id">
          <div v-bind:style="findColor(item)" class="categorie-style" v-on:click="toggleActive(item)">
            {{ item.label }}
          </div>
          <transition name="fade">
            <div v-if="findActive(item)" class="skill-list" v-bind:style="applyHeight(item)" >
              <a v-for="skill in item.skills" class="skill-style" v-bind:style="backgroundSelected(skill)" v-on:click="select(skill)"
                 v-on:mouseover="changeColorToSelec" v-on:mouseleave="changeColorToUnselec(skill,$event)">
                <span>{{ skill.label }}</span>
                <i v-if="checkSelected(skill)" v-bind:class="checked" class="icon-skill"></i>
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

  const colors = [
    "#00B6E8","#D13040","#7E7995","#FFC15E","#DC7633","#154360"
  ];

  export default {
    props:['currentSkills'],
    data: function () {
      return {
        checked : "fa fa-check-circle-o fa-2x",
        unchecked: "fa fa-plus-circle fa-2x",
        items : [false, false, false, false,false,false],
        resultSearch:"Recherche vide",
        colorSearch:"color:white",
        search:""
      }
    },
    computed:{
      categories:function(){
        return this.$store.state.skillDomains;
      },
      skills:function(){
        return this.currentSkills;
      }
    },
    methods: {
      findColor(cat){
        return "background-color:" + colors[this.categories.indexOf(cat)%6];
      },
      applyHeight(cat){
        return "height:" + cat.skills.length  + "vh;";
      },
      toggleActive(cat){
        this.items.splice(this.categories.indexOf(cat), 1, !this.items[this.categories.indexOf(cat)]);
      },
      findActive(cat){
        return this.items[this.categories.indexOf(cat)];
      },
      findSkills(cat){
        return this.skills[this.categories.indexOf(cat)];
      },
      checkSelected(skill){
          for(let i in this.$store.state.currentMission.skills){
              if(this.$store.state.currentMission.skills[i].label==skill.label) return i;
          }
          return false;
      },
      backgroundSelected(skill){
        return "background-color:" + (!this.checkSelected(skill) ? "white;" : "#50BDAC;") +
          "color:" + (!this.checkSelected(skill) ? "black;" : "white;");
      },
      select(skill){
        if (!this.checkSelected(skill)){
          this.$store.state.currentMission.skills.push(skill);
          console.log("adding skill...");
          console.log(this.$store.state.currentMission.skills);
        }
        else {
          this.$store.state.currentMission.skills.splice(this.checkSelected(skill), 1);
          console.log("removing skill...");
          console.log(this.$store.state.currentMission.skills);
        }
      },
      changeColorToSelec(e){
        if (e.target.classList.value.includes("skill-style")) e.target.style.backgroundColor = "#50BDAC";
        else if (e.path[1].classList.value.includes("skill-style")) e.path[1].style.backgroundColor = "#50BDAC";
      },
      changeColorToUnselec(skill, e){
        if (!this.checkSelected(skill)) {
          if (e.target.classList.value.includes("skill-style")) e.target.style.backgroundColor = "white";
          else if (e.path[1].classList.value.includes("skill-style")) e.path[1].style.backgroundColor = "white";
        }
      },
      searchSkill(){
        if (this.search != "") {
          let done = false;
          for (let i in this.categories) {
            if (this.categories[i].label.toLowerCase() == this.search.toLowerCase()) {
              this.resultSearch = "Recherche réussie";
              this.colorSearch = "color:green;font-weight: bold;";
              this.items.splice(i, 1, true);
              done = true;
              break;
            }
          }
          for (let i in this.categories){
            for (let j in this.categories[i].skills){
              if(this.categories[i].skills[j].label.toLowerCase() == this.search.toLowerCase()){
                this.resultSearch = "Recherche réussie";
                this.colorSearch = "color:green;font-weight: bold;";
                this.items.splice(i, 1, true);
                this.$store.state.currentMission.skills.push(this.categories[i].skills[j]);
                console.log(this.$store.state.currentMission.skills);
                done = true;
                break;
              }
            }
          }
          if (!done) {
            this.resultSearch = "Aucun Résultat";
            this.colorSearch = "color:red;font-weight: bold;";
          }
        }
        else {
          this.resultSearch = "Recherche vide";
          this.colorSearch = "color:white";
          this.items = [false, false, false, false,false,false];
        }
      },
      deleteSearch(){
        this.search = "";
        this.resultSearch = "Recherche vide";
        this.colorSearch = "color:white";
        this.items = [false, false, false, false,false,false];
        this.resetSkills();
      },
      cancelSearch(){
        if (this.search == "") {
          this.resultSearch = "Recherche vide";
          this.colorSearch = "color:white";
          this.items = [false, false, false, false,false,false];
          this.resetSkills();
        }
      },
      resetSkills(){
//        this.skills = [];
//        for (let i in this.defaultSkills) {
//          this.skills.push([]);
//          for (let j in this.defaultSkills[i]) {
//            this.skills[i].push(this.defaultSkills[i][j]);
//          }
//        }
      }
    }
  }


</script>

<style>
  .containerCv{
    width: 100%;
    height: 10em;
    overflow-y: scroll;
  }

  .categorie-style{
    display: flex;
    width: 96.5%;
    height:7vh;
    margin-top: 0.5vh;
    margin-bottom: 0.5vh;
    margin-left: 2%;
    margin-right: 1vw;
    position:relative;
    color:white;
    justify-content: center;
    line-height: 7vh;
    font-family: "Arial", Arial, sans-serif;
    font-size:3vh;
    cursor: pointer;
  }

  .skill-style{
    width : auto;
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
    cursor: pointer;
    padding:5px 10px 5px 10px;
  }

  .skill-list{
    margin-left : 1vw;
  }

  .icon-skill{
    position : relative;
    float:right;
    padding-left:5px;
    color:white;
    line-height:8vh;
  }

  .title-2{
    color:dimgrey;
    font-size:25px;
  }

  .search-skill{
    padding-left:1vw;
    border:none;
    outline: none;
    width:15em;
    display:inline-block;
  }

  .zone-search-skill{
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    position:relative;
    left:-10%;
    padding: 0 1%;
    height:5vh;
    width:27em;
    border-radius: 20px;
    border: 1px solid dodgerblue;
    background-color: white;
  }

  .result-search{
    display:inline-block;
    width: 9em;
    padding: 0 2em;
    border-left:1px solid lightgrey;
    color:white;
    font-size:2vh;
  }

  .picto-search{
    color: dodgerblue;
    line-height:5vh;
    padding-left:1vw;
    padding-right:1vw;
  }
  .picto-cancel{
    cursor: pointer;
  }

  .head-component{
    width: 95%;
    display: flex;
    justify-content: space-between;
    margin-left: 2%;
    padding-bottom: 0.2em;
    border: none;
    border-bottom: 1px solid dimgrey;
  }

  .title-skills{
    display:inline;
    width: 15em;
  }

  .transition-skills-move{
    transition: transform 1s;
  }

  .fade-enter-active{
    transition: opacity .5s;
  }
  .fade-enter ,.fade-leave-to{
    opacity: 0;
  }

  .fade-leave-active{
    transition: opacity .5s;
  }

</style>
