<template>
  <div style="margin-top:1vh; overflow: hidden;">
    <div>
      <i class="fa fa-space-shuttle" style="margin-left:1vw;"></i>
      <span class="title-2" style="font-family: Bradley Hand ITC, cursive; font-weight: bold; font-size: 25px;margin-left: 1% ">Missions</span>
    </div>
    <div class="container-cv" style="width:80vw;height:21vh;">
      <div id="listMissions" style="width:100vw;height:21vh;">
        <div style="width:2vw;height:20vh;float:left">
          <i v-bind:class="chevronLeft":style="styleChevron" v-on:click="moveMissionsToLeft" v-if="missions.length>5"></i>
        </div>
        <i v-bind:class="chevronRight":style="styleChevronRight" v-on:click="moveMissionsToRight" v-if="missions.length>5"></i>
        <div id="containerMissions" style="height:23.5vh;width:75vw;overflow:hidden;">
          <div id="listAnimate" v-bind:style="styleAnimatingList">
            <transition-group name="list-complete" tag="p">
                 <span v-bind:style="getStyleMission(item)" v-for="(item,index) in missions" v-bind:key="item" @click="getInfoMission(item)"
                       v-on:mouseover="borderColorToHover" v-on:mouseleave="borderColorToDefault"
                       class="list-complete-item missionItem">
                    <div v-bind:style="styleTitle">{{ item.name!=""?item.name:"Nouvelle Mission" }}</div>
                    <div v-bind:style="styleDate">{{ item.beginDate }} to {{ item.endDate!=""?item.endDate:"now" }}</div>
                    <i v-on:mouseover="trashToRed" v-on:mouseleave="trashToBlack" v-on:click="deleteMission(index)"
                       v-bind:class="trash":style="styleTrash"></i>
                 </span>
            </transition-group>
            <div v-bind:style="styleObject" id="add-mission" class="missionItem list-complete-item" v-on:click="addMission"
                 v-on:mouseover="borderColorToHover" v-on:mouseleave="borderColorToDefault">
              <i class="fa fa-plus fa-2x"></i>
              <div v-bind:style="styleDate">Ajouter une nouvelle Mission</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { bus } from '../../EventBus';

  var styleMissionBox = {
    width: "11.5vw",
    height:"4vh",
    "padding-top": "3vh",
    "padding-bottom": "8vh",
    margin: "0.5vw",
    "text-align": "center",
    "border-radius":"10px",
    "float":"left",
    position:"relative",
    "background-color":"#EEE"
  };

  var styleMissionBoxTitle = {
    "text-transform": "uppercase",
    "font-size" : "2vh"
  };

  var styleMissionBoxDate = {
    color:"grey",
    "padding-top": "1.5vh",
    "font-size" : "1.8vh"
  };

  var styleChevron = {
    position:"relative",
    "margin-top": "7.5vh",
    display:"inline",
    "margin-right":"5px",
    float:"left"
  };

  var styleChevronRight = {
    "float":"right",
    position:"relative",
    right:"21vw",
    top:"8vh"
  };

  var styleTrash = {
    position:"absolute",
    "left":"10vw",
    "bottom":"1vh",
    color:"black"
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
        styleTitle: styleMissionBoxTitle,
        styleDate: styleMissionBoxDate,
        styleChevron : styleChevron,
        styleChevronRight:styleChevronRight,
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
        e.target.style.color="black";
      },
      borderColorToHover(e){
        if(e.target.classList.value.includes("missionItem"))e.target.style.border = "1px solid orange";
        else if(e.path[1].classList.value.includes("missionItem"))e.path[1].style.border = "1px solid orange";
      },
      borderColorToDefault(e){
        e.target.style.border = ""
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
            (parseInt(elem.style.right?elem.style.right.substring(0,elem.style.right.length-2):0)
            >=parseInt((this.missions.length-5)*vwTOpx(item.style.width.substring(0,item.style.width.length-2))))){
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
        if(this.missions.length<=5){
          document.getElementById("listAnimate").style.right = 0;
          document.getElementById("listAnimate").style.left = 0;
        }
        this.$emit('deleteMission');
      },
      getInfoMission(item){
          this.$emit('getInfoMission', item.id);
      },
      addMission(){
          this.$emit('addMission');
      },
      getStyleMission(item){
          if(this.block==item.id){
              return {
                width: "11.5vw",
                height:"4vh",
                "padding-top": "3vh",
                "padding-bottom": "8vh",
                margin: "0.5vw",
                "text-align": "center",
                "border-radius":"10px",
                "float":"left",
                position:"relative",
                "background-color":"#EEE",
                border: "1px solid orange"
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
    border-top: 1px solid lightgrey;
    border-bottom: 1px solid lightgrey;
    border-right: 1px solid lightgrey;
  }

  .list-complete-enter-active, .list-complete-leave-active {
    transition: .5s
  }
  .list-complete-enter, .list-complete-leave-to {
    opacity: 0;
    transform: translateY(30px);
  }

</style>
