<template>
  <div id="filters-menu">
    <div class="bannerFilter">Filtres</div>
    <div class="FilterField">
      <div class="arrowTitle">
        <div class="arrow"></div>
        <p>Competences</p>
      </div>
      <div>
        <input class="skillsFilter" v-show="!newSkill" type="text" v-model="searchedSkill" @keyup.enter="isThatASkill(searchedSkill)">
        <div style="display: flex; justify-content: center; align-items: center">
          <div class="newSkill" v-show="newSkill">{{skillToAdd.skill}}</div>
          <i v-show="newSkill" class="fa fa-times-circle fa-lg" style="position: relative; left: -20px" @click="newSkill=!newSkill; searchedSkill=''"></i>
        </div>
        <i class="fa fa-search" style="position: relative; left: -20px"></i>
      </div>
      <div v-show="newSkill">
        <span v-show="!showCat" style="font-size: 12px; font-family: Calibri,serif">Cette compétence n'existe pas, voulez-vous l'ajouter?</span>
        <i v-show="!showCat" class="fa fa-plus-square-o fa-lg" style="color: #3498DB;" @click="addSkill(searchedSkill)"></i>
        <div v-show="showCat"><span v-for="(cat,index) in categories" :style="chooseColor(cat)" class="categoriesWhenAddSkill" @click="addCat(cat)">{{cat}}</span></div>
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
      <div></div>
    </div>
    <div class="FilterField">
      <div class="arrowTitle">
        <div class="arrow"></div>
        <p>Experience</p>
      </div>
      <div></div>
    </div>
    <div class="FilterField">
      <div class="arrowTitle">
        <div class="arrow"></div>
        <p>Client</p>
      </div>
      <div></div>
    </div>
  </div>
</template>

<script>
  export default{
    props: ['colors','categories','skills'],
    methods:{
      isThatASkill(searchedSkill){
          for(let skillsList in this.skills){
              for(let skill in this.skills[skillsList]){
                if(this.skills[skillsList][skill].toLowerCase()==searchedSkill.toLowerCase()){
                    let skill=searchedSkill[0].toUpperCase()+searchedSkill.slice(1);
                  if(this.listOfSkills.filter((e) => e.skill == skill ).length==0){
                      this.listOfSkills.push({skill: skill, categorie: this.categories[skillsList]});
                  }
                  return;
                }
              }
          }
          this.newSkill=true;
          this.skillToAdd.skill=searchedSkill[0].toUpperCase()+searchedSkill.slice(1);
      },
      addSkill(skillToAdd){
          this.skillToAdd.skill=skillToAdd[0].toUpperCase()+skillToAdd.slice(1);
          this.showCat= true;
          console.log('will add '+this.skillToAdd);
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
        console.log('will add '+this.skillToAdd.skill+' in categorie '+this.skillToAdd.categorie);
      },
      chooseColor(cat){
          return 'color : '+this.colors[this.categories.indexOf(cat)];
      },
      chooseBackgroundColor(cat){
        return 'background-color : '+this.colors[this.categories.indexOf(cat)];
      },
      removeSkillFromList(skill){
          this.listOfSkills.splice(this.listOfSkills.indexOf(skill),1)
      }
    },
    data: function () {
      return {
        searchedSkill: "",
        newSkill: false,
        showCat: false,
        skillToAdd: {
            skill: '',
            categorie: ''
        },
        listOfSkills: []
      };
    }
  }
</script>

<style>


</style>
