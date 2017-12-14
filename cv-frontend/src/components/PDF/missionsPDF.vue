<template>
    <div class="missions">
        <div class="line"></div>
        <div class="detailMission">
          <div><p>{{infoMission.typeMissions.label}} chez {{infoMission.client.label}}</p></div>
        </div>
        <div class="titreMission">
            <p>{{infoMission.title}}</p>
        </div>
        <div class="dateMission">
            <div class="arrow"></div>
            <div><p>Du {{infoMission.beginDate}} au {{infoMission.endDate}}</p></div>
        </div>
        <div class="appType" v-if="type=chooseAppType(infoMission.skills)">
          <div class="appType" >
            <div class="arrow"></div>
            <div class="descriptionMission"><p>{{type}}</p></div>
          </div>
        </div>
        <div class="contentMission">
            <div class="arrow"></div>
            <div class="descriptionMission"><p>{{infoMission.description}}</p></div>
        </div>
        <div class="skills">
            <div v-for="skill in infoMission.skills" class="skill" :style="chooseColor(skill.label)">{{skill.label}}</div>
        </div>
    </div>
</template>

  <script>
    export default {
      data: function () {
        return {
          categories : [
            "MOBILE","OUTILS","MÉTHODOLOGIE","WEB","BASE DE DONNEES","FRAMEWORK"
          ]
        }
      },
      props: ['infoMission'],
      beforeMount:function(){
        console.log(this.infoMission);
      },
      methods: {
        chooseColor: function (target) {
            for(let i in this.skills){
                for (let j in this.skills[i]){
                    if(target==this.skills[i][j]){
                        return {'background-color': this.colors[i]};
                    }
                }
            }
            return {'background-color': '#D7D7D7'};
        },
        chooseAppType: function (skills) {
              let appType='Développement d\'application ';
              let web=this.isWebApp(skills);
              let mobile=this.isMobileApp(skills);
              if(web!=''&&mobile!='')appType+=web+' et '+mobile;
              else if (web!=''&&mobile=='')appType+=web;
              else if (web==''&&mobile!='')appType+=mobile;
              else appType='';
              return appType;
        },
        isWebApp(skills){
          for(let skill of skills){
            let webList = skills[this.categories.indexOf('WEB')]
            for (let k in webList) {
              if (skill.label == webList[k]) {
                return 'Web';
              }
            }
          }
          return '';
        },
        isMobileApp(skills){
          for(let skill of skills){
            let mobileList=skills[this.categories.indexOf('MOBILE')]
            for (let k in mobileList) {
              if (skill.label == mobileList[k]) {
                return 'Mobile';
              }
            }
          }
          return '';
        }
        /*
        giveDate(date){
          let thisDate = new Date(date);
          let thisDay, thisMonth;
          thisDate.getDate() < 10 ? thisDay = '0' + thisDate.getDate() : thisDay = thisDate.getDate();
          thisDate.getMonth() + 1 < 10 ? thisMonth = '0' + parseInt(thisDate.getMonth() + 1) : thisMonth = parseInt(thisDate.getMonth() + 1);
          return thisDay  + '/' + thisMonth  + '/' + thisDate.getFullYear() ;
        }
        */
      }
    }
</script>

<style>

</style>
