<template>
  <div style="margin-top:1vh; overflow: hidden;">
    <div class="headerMissionsBlock">
      <i class="fa fa-space-shuttle fa-2x"></i>
      <span class="title-2" style="font-family: Arial, cursive; font-weight: bold; font-size: 25px;margin-left: 1% ">Missions</span>
    </div>
    <div class="container-cv">
      <div id="listMissions" class="listMissions">
        <i v-bind:class="chevronLeft" v-on:click="moveMissionsToLeft" v-if="missions.length>4"></i>
        <div id="containerMissions" class="containerMissions">
          <div id="listAnimate" v-bind:style="styleAnimatingList">
            <transition-group name="list-complete" tag="div">
                 <span v-bind:style="block==index?styleObjectChecked:styleObject" v-for="(item,index) in missions" v-bind:key="index" @click="getInfoMission(index)"
                       class="list-complete-item missionItem">
                    <div v-bind:style="styleTitle">{{ item.title!=""?item.title:"Nouvelle Mission" }}</div>
                    <div v-bind:style="styleDate">{{ item.beginDate }} to {{ item.endDate!=""?item.endDate:"now" }}</div>
                    <i v-on:mouseover="trashToRed" v-on:mouseleave="trashToBlack" v-on:click="deleteMission(index)"
                       v-bind:class="trash":style="styleTrash"></i>
                 </span>
            </transition-group>
            <div v-bind:style="styleObject" id="add-mission" class="missionItem list-complete-item" v-on:click="addMission">
              <i class="fa fa-plus fa-2x"></i>
              <div v-bind:style="styleDate">Ajouter une nouvelle Mission</div>
            </div>
          </div>
        </div>
        <i v-bind:class="chevronRight" v-on:click="moveMissionsToRight" v-if="missions.length>4"></i>
      </div>
    </div>
  </div>
</template>

<script>
  import { bus } from '../../EventBus';

  var styleMissionBox = {
    color: 'white',
    width: "11.5vw",
    height:"4vh",
    "padding-top": "3vh",
    "padding-bottom": "8vh",
    margin: "0.5vw",
    "text-align": "center",
    "border-radius":"10px",
    "float":"left",
    position:"relative",
    "background-image":'linear-gradient(to bottom, #3498db, #2980b9)'
  };

  var styleMissionBoxChecked = {
    color: 'white',
    width: "11.5vw",
    height:"4vh",
    "padding-top": "3vh",
    "padding-bottom": "8vh",
    margin: "0.5vw",
    "text-align": "center",
    "border-radius":"10px",
    "float":"left",
    position:"relative",
    "background-image":'linear-gradient(to bottom, #487692, #265471)',
  };

  var styleMissionBoxTitle = {
    "text-transform": "uppercase",
    "font-size" : "2vh"
  };

  var styleMissionBoxDate = {
    color:"white",
    "padding-top": "1.5vh",
    "font-size" : "1.8vh"
  };

  var styleTrash = {
    position:"absolute",
    "left":"10vw",
    "bottom":"1vh",
    color:"white"
  };

  var styleAnimatingList = {
    position:"relative",
    width:"300%"
  };

  function vwTOpx(value) {
    var w = window,
      d = document,
      e = d.documentElement,
      g = d.getElementsByTagName('body')[0],
      x = w.innerWidth || e.clientWidth || g.clientWidth;

    var result = (x*value)/100;
    return(result);
  }

  export default {
    data:function(){
      return {
        styleObject: styleMissionBox,
        styleObjectChecked: styleMissionBoxChecked,
        styleTitle: styleMissionBoxTitle,
        styleDate: styleMissionBoxDate,
        styleTrash : styleTrash,
        styleAnimatingList : styleAnimatingList,
        imageLink:"static/img/add.png",
        chevronLeft:"fa fa-angle-left fa-3x",
        chevronRight:"fa fa-angle-right fa-3x",
        trash:"fa fa-trash"
      }
    },
    methods:{
      trashToRed(e){
        e.target.style.color="red";
      },
      trashToBlack(e){
        e.target.style.color="white";
      },
      moveMissionsToLeft(){
        var elem = document.getElementById("listAnimate");
        var posLeft = elem.style.left?(parseInt(elem.style.left.substring(0,elem.style.left.length-2))):0;
        var posRight = elem.style.right?(parseInt(elem.style.right.substring(0,elem.style.right.length-2))):0;
        var count=0;
        let item = document.getElementsByClassName("missionItem")[0];
        var end = vwTOpx(item.style.width.substring(0,item.style.width.length-2))
          +2*vwTOpx(item.style.margin.substring(0,item.style.margin.length-2));
        var id = setInterval(frame, 1);
        function frame(){
          if(count >= end||(posRight==0&&posLeft<=0)){
            clearInterval(id);
          }
          else{
            if(posRight&&posRight>=0){
              if(count<end/3||count>2*(end/3)){
                posRight--;
                count++;
              }
              else {
                posRight-=2;
                count+=2;
              }
              elem.style.right = posRight + 'px';
            }
            else{
              elem.style.right="";
              if(count<end/3||count>2*(end/3)){
                posLeft++;
                count++;
              }
              else {
                posLeft+=2;
                count+=2;
              }
              elem.style.left = posLeft + 'px';
            }
          }

        }
      },
      moveMissionsToRight(){
        var elem = document.getElementById("listAnimate");
        var posLeft = elem.style.left?(parseInt(elem.style.left.substring(0,elem.style.left.length-2))):0;
        var posRight = elem.style.right?(parseInt(elem.style.right.substring(0,elem.style.right.length-2))):0;
        var count = 0;
        let item = document.getElementsByClassName("missionItem")[0];
        var end = vwTOpx(item.style.width.substring(0,item.style.width.length-2))
          +2*vwTOpx(item.style.margin.substring(0,item.style.margin.length-2));

        let frame=()=>{
          if(count >= end||
            (parseInt(posRight)>=parseInt((this.missions.length-4)*end))){
            clearInterval(id);
          }
          else{
            if(posLeft&&posLeft>=0){
              if(count<end/3||count>2*(end/3)){
                posLeft--;
                count++;
              }
              else {
                posLeft-=2;
                count+=2;
              }
              elem.style.left = posLeft + 'px';
            }
            else{
              elem.style.left="";
              if(count<end/3||count>2*(end/3)){
                posRight++;
                count++;
              }
              else {
                posRight+=2;
                count+=2;
              }
              elem.style.right = posRight + 'px';
            }
          }
        };

        var id = setInterval(frame, 1);
      },
      deleteMission(index){
        this.missions.splice(index,1);

        setTimeout(()=> {
          if (this.missions.length <= 5) {
            document.getElementById("listAnimate").style.right = 0;
            document.getElementById("listAnimate").style.left = 0;
          }
          this.$emit('deleteMission');
        },500);
      },
      getInfoMission(index){
          this.$emit('getInfoMission',index);
      },
      addMission(){
        this.$emit('addMission');
      },
      getStyleMission(item){
        if(this.block==item.id){
          return {
            color: 'white',
            width: "11.5vw",
            height:"4vh",
            "padding-top": "3vh",
            "padding-bottom": "8vh",
            margin: "0.5vw",
            "text-align": "center",
            "border-radius":"10px",
            "float":"left",
            position:"relative",
            "background-image":"linear-gradient(to bottom, #3498db, #2980b9)",
          };
        }
        else{
          return this.styleObject;
        }

      }
    },
    props:[
      'missions',"block"
    ]
  }
</script>

<style>
  .container-cv{
    width: 100%;
    height:21vh;
  }

  .headerMissionsBlock{
    width: 95%;
    display: flex;
    margin-left: 2%;
    padding-bottom: 0.2em;
    border: none;
    border-bottom: 1px solid dimgrey;
  }

  .listMissions{
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    width: 100%;
    height:21vh;
  }

  .containerMissions{
    width: 85%;
    overflow:hidden;
  }
  .list-complete-enter-active, .list-complete-leave-active {
    transition: .5s
  }
  .list-complete-enter, .list-complete-leave-to {
    opacity: 0;
    transform: translateY(30px);
  }

  .missionItem:hover{
    transform: scale(1.1);
  }

  .fa-trash{
    color: white;
  }

</style>
