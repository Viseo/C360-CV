<template>
  <div id="filters-menu">
    <div class="bannerFilter">Filtres</div>
    <div class="FilterField">
      <div class="arrowTitle">
        <div class="arrow"></div>
        <p>Competences</p>
      </div>
      <div class="contenerSkillsFilter">
        <input class="inputFilter" v-show="!newSkill" type="text" v-model="searchedSkill" @keyup.enter="isThatASkill">
        <div style="display: flex; justify-content: center; align-items: center">
          <div class="newSkill" v-show="newSkill">{{skillToAdd.skill}}</div>
          <i v-show="newSkill" class="fa fa-times-circle fa-lg" style="position: relative; left: -20px" @click="newSkill=!newSkill; searchedSkill=''"></i>
        </div>
        <i class="fa fa-search" style="position: relative; left: -20px"></i>
      </div>
      <div v-show="newSkill" class="addingSkill">
        <span v-show="!showCat" style="font-size: 12px; font-family: Calibri,serif">Cette compétence n'existe pas, voulez-vous l'ajouter?</span>
        <i v-show="!showCat" class="fa fa-plus-square-o fa-lg" style="color: #3498DB;" @click="addSkill(searchedSkill)"></i>
        <div style="display: flex; flex-direction: row; flex-wrap: wrap">
          <div v-show="showCat"><span v-for="(cat,index) in categories" :style="chooseColor(cat)" class="categoriesWhenAddSkill" @click="addCat(cat)">{{cat}}</span></div>
        </div>
      </div>
      <div class="skills">
        <template  v-for="skill in listOfSkills">
          <div class="skill" :style="chooseBackgroundColor(skill.categorie)">{{skill.skill}}</div>
          <i class="fa fa-times" style="position: relative; left: -1.1em;top: 0.2em; width: 0; color: white"  @click="removeSkillFromList(skill)"></i>
        </template>
      </div>
    </div>
    <div class="FilterField">
      <div class="arrowTitle">
        <div class="arrow"></div>
        <p>Consultants</p>
      </div>
      <div class="contenerCheckboxFilter">
        <div>
          <input id="Stagiaire" type="checkbox" v-model="consultant" name="Consultant" value="Stagiaire">
          <label for="Stagiaire">Stagiaire</label>
        </div>
        <div>
          <input id="Junior" type="checkbox" v-model="consultant" name="Consultant" value="Junior">
          <label for="Junior">Junior</label>
        </div>
        <div>
          <input id="Sénior" type="checkbox" v-model="consultant" name="Consultant" value="Sénior">
          <label for="Sénior">Sénior</label>
        </div>
          <div>
            <input id="Manager" type="checkbox" v-model="consultant" name="Consultant" value="Manager">
            <label for="Manager">Manager</label>
        </div>
      </div>
    </div>
    <div class="FilterField">
      <div class="arrowTitle">
        <div class="arrow"></div>
        <p>Experience</p>
      </div>
      <div class="contenerCheckboxFilter">
        <div>
          <input id="0 à 3 ans" type="checkbox" v-model="experience" name="Experience" value="0 à 3 ans">
          <label for="0 à 3 ans"> 0 à 3 ans</label>
        </div>
        <div>
          <input id="4 à 5 ans" type="checkbox" v-model="experience" name="Experience" value="4 à 5 ans">
          <label for="4 à 5 ans"> 4 à 5 ans</label>
        </div>
        <div>
          <input id="6 à 10 ans" type="checkbox" v-model="experience" name="Experience" value="6 à 10 ans">
          <label for="6 à 10 ans"> 6 à 10 ans</label>
        </div>
        <div>
          <input id="Plus de 10 ans" type="checkbox" v-model="experience" name="Experience" value="Plus de 10 ans">
          <label for="Plus de 10 ans" > Plus de 10 ans</label>
        </div>
      </div>
    </div>
    <div class="FilterField">
      <div class="arrowTitle">
        <div class="arrow"></div>
        <p>Client</p>
      </div>
      <div class="contenerClientsFilter">
        <input class="inputFilter" type="text" v-model="searchedClient">
        <i class="fa fa-search" style="position: relative; left: -20px"></i>
      </div>
    </div>
    <div class="divReset">
      <div class="resetFilters" v-on:click="resetFilters">Reset Filters</div>
    </div>
  </div>
</template>

<script>
  export default{
    props: ['colors','categories','skills'],
    methods:{
      isThatASkill(){
          for(let skillsList in this.skills){
              for(let skill in this.skills[skillsList]){
                if(this.skills[skillsList][skill].toLowerCase()==this.searchedSkill.toLowerCase()){
                    let skill=this.searchedSkill[0].toUpperCase()+this.searchedSkill.slice(1);
                    if(this.listOfSkills.filter((e) => e.skill == skill ).length==0){
                        this.listOfSkills.push({skill: skill, categorie: this.categories[skillsList]});
                    }
                    this.searchedSkill="";
                    return;
                }
              }
          }
          this.newSkill=true;
          this.skillToAdd.skill=this.searchedSkill[0].toUpperCase()+this.searchedSkill.slice(1);
      },
      addSkill(skillToAdd){
          this.skillToAdd.skill=skillToAdd[0].toUpperCase()+skillToAdd.slice(1);
          this.showCat= true;
      },
      addCat(cat){
        this.skillToAdd.categorie=cat;
        let skill=this.skillToAdd.skill;
        this.showCat=false;
        this.newSkill=false;
        this.searchedSkill='';
        if(this.listOfSkills.filter((e) => e.skill == skill ).length==0){
          this.listOfSkills.push({skill: skill, categorie: cat});
        }
      },
      chooseColor(cat){
          return 'color : '+this.colors[this.categories.indexOf(cat)];
      },
      chooseBackgroundColor(cat){
        return 'background-color : '+this.colors[this.categories.indexOf(cat)];
      },
      removeSkillFromList(skill){
          this.listOfSkills.splice(this.listOfSkills.indexOf(skill),1)
      },
      resetFilters(){
          this.searchedSkill='';
          this.searchedClient='';
          this.listOfSkills=[];
          this.experience=[];
          this.consultant=[];
          this.skillToAdd={};
          this.newSkill=false;
          this.showCat=false;
      },
      sendFilters(){
          let tabExp =[];
          for(let i of this.experience){
              let exp;

              if(i[0]<"0"||i[0]>"9"){
                  exp = {down:parseInt(i.substring(8,10)),up:null};
              }
              else{
                if(i[4]>i[0]) exp = {down:parseInt(i[0]),up:parseInt(i[4])};
                else exp = {down:parseInt(i[0]),up:parseInt(i.substring(4,6))};
              }

              tabExp.push(exp);
          }

          this.$emit("updateFilters",this.consultant,tabExp,this.searchedClient,this.listOfSkills);
      }
    },
    data: function () {
      return {
        searchedSkill: "",
        searchedClient: "",
        newSkill: false,
        showCat: false,
        skillToAdd: {
            skill: '',
            categorie: ''
        },
        listOfSkills: [],
        experience: [],
        consultant: [],
      };
    },
    watch:{
      experience:function(){
        this.sendFilters();
      },
      consultant:function(){
        this.sendFilters();
      },
      searchedClient:function(){
        this.sendFilters();
      },
      listOfSkills:function(){
        this.sendFilters();
      }
    }
  }
</script>

<style>


</style>
