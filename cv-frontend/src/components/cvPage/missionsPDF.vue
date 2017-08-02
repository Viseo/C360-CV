<template>
    <div class="missions">
        <div class="line"></div>
        <div class="detailMission">
            <div><p>{{infoMission.type}} chez {{infoMission.client}}</p></div>
        </div>
        <div class="titreMission">
            <p>{{infoMission.name}}</p>
        </div>
        <div class="dateMission">
            <div class="arrow"></div>
            <div><p>Du {{infoMission.beginDate}} au {{infoMission.endDate}}</p></div>
        </div>
        <div class="appType" v-if="type=chooseAppType(infoMission.keyword)">
            <div class="arrow"></div>
            <div class="descriptionMission"><p>{{type}}</p></div>
        </div>
        <div class="contentMission">
            <div class="arrow"></div>
            <div class="descriptionMission"><p>{{infoMission.description}}</p></div>
        </div>
        <div class="skills">
            <div v-for="skill in infoMission.keyword" class="skill" :style="chooseColor(skill)">{{skill}}</div>
        </div>
    </div>
</template>

  <script>
    export default {
      data: function () {
        return {
          skills : [
            ["Android","Ios","React Native","Xamarin"],
            ["Axure","Balsamiq","Jira","Taiga","Photoshop"],
            ["Cycle en V", "Kanban", "Lean", "Lean startup", "Less", "Rup", "Scrum", "Safe"],
            ["Angular", "Bootstrap", "CSS", "Html", "Java", "Javascript", "Python"],
            ["Apache Derby", "Microsoft Access", "Microsoft SQL Server", "MySQL", "Oracle Database", "PostgreSQL"],
            ["Bootstrap", "Cake PHP", "Google Guava", "Hibernate", "JUnit", "JQuery", "Node.js", "Laravel", "Phalcon", "PHPUnit", "Spring", "Symfony", "Zend"]
          ],
          categories : [
            "MOBILE","OUTILS","MÉTHODOLOGIE","WEB","BASE DE DONNEES","FRAMEWORK"
          ],
          colors : [
            "#00B6E8","#D13040","#7E7995","#FFC15E","#DC7633","#154360"
          ],
          styleSkills: {
            'background-color': '#FFC15E'
          }
        }
      },
      props: ['infoMission'],
      methods: {
        chooseColor: function (target) {
            for(let i in this.skills){
                for (let j in this.skills[i]){
                    if(target==this.skills[i][j]){
//                        console.log(target, this.skills[i][j])
                        return {'background-color': this.colors[i]}
                    }
                }
            }
            return {'background-color': '#D7D7D7'};
        },
        chooseAppType: function (skills) {
              let appType='Développement d\'application ';
              let web=this.isWebApp(skills);
              let mobile=this.isMobileApp(skills);
              if(web!=''&&mobile!='')appType+=web+' et '+mobile
              else if (web!=''&&mobile=='')appType+=web
              else if (web==''&&mobile!='')appType+=mobile
              else appType=''
              return appType;
        },
        isWebApp(skills){
          for(let skill of skills){
              console.log(skills)
            let webList=this.skills[this.categories.indexOf('WEB')]
            for (let k in webList) {
              if (skill == webList[k]) {
                console.log('CEST DU WEB')
                return 'Web';
              }
            }
          }
          return '';
        },
        isMobileApp(skills){
          for(let skill of skills){
            console.log(skills)
            let webList=this.skills[this.categories.indexOf('MOBILE')]
            for (let k in webList) {
              if (skill == webList[k]) {
                return 'Mobile';
              }
            }
          }
          return '';
        }
      }
    }
</script>

<style>

</style>
