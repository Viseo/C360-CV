<template>
  <div v-bind:style="styleDv">
    <div v-bind:style="styleHead"><i v-bind:style = "stylePicT" class="fa fa-address-card-o fa-lg" aria-hidden="true"></i> {{title}}</div>
    <div class="photoUser">
      <img id="photoP" v-bind:src = "photoProfileSrc" v-on:mouseover="changePhoto" v-bind:style="stylePh" aria-hidden="true">
      <span id="textChange" v-bind:style = "styleSp" v-on:mouseout="initPhoto">Modifier</span>
    </div>

    <input type="file" id="inputFile" accept="image/*" name="picture" style="color: transparent; display: none">

    <div class="containerInput">
      <label v-bind:style = "stylep">Prénom</label>
      <div class="inputWithPicto">
        <i class="fa fa-user-o fa-2x picto" aria-hidden="true"></i>
        <input class="inputInfo" name = "firstName" maxlength="125" type="text" placeholder="Prenom"
               v-model="infoPerso.firstName">
        <span style="color:red;font-weight:bold;"
              v-show="infoPerso.firstName==''">Prénom invalide</span>
      </div>
    </div>

    <div class="containerInput">
      <label v-bind:style = "stylep">Nom</label>
      <div class="inputWithPicto">
        <i class="fa fa-user-o fa-2x picto" aria-hidden="true"></i>
        <input class="inputInfo" name="name" @input="infoPerso.lastName=infoPerso.lastName.toUpperCase()" placeHolder="Nom"
               v-model="infoPerso.lastName">
        <span style="color:red;font-weight:bold;"
              v-show="infoPerso.lastName==''">Nom invalide</span>
      </div>
    </div>

    <div class="containerInput">
      <label v-bind:style = "stylep">Age</label>
      <div class="inputWithPicto">
        <i class="fa fa-birthday-cake fa-2x picto" aria-hidden="true"></i>
        <input class="inputInfo date" name="birth" type="date" v-model="infoPerso.birth_date">
        <span style="color:red;font-weight:bold;"
              v-show="infoPerso.birth_date == ''">Age invalide</span>
        <span><i id="calendar_icon" class="fa fa-calendar pictoInput" aria-hidden="true"></i> </span>
      </div>
    </div>

    <div class="containerInput">
      <label v-bind:style = "stylep">Fonction</label>
      <div class="inputWithPicto">
        <i class="fa fa-tag fa-2x picto" aria-hidden="true"></i>
        <input class="inputInfo" name="fonction" maxlength="125" placeHolder="Poste"
               v-model="infoPerso.position">
        <span style="color:red;font-weight:bold;"
              v-show="infoPerso.position == ''">Fonction invalide</span>
      </div>
    </div>

    <div class="containerInput">
      <label v-bind:style = "stylep">Expérience (en année)</label>
      <div class="inputWithPicto">
        <i  class="fa fa-tachometer fa-2x picto" aria-hidden="true"></i>
        <input class="inputInfo" name="experience" placeHolder="Années d'expérience"
               v-model="infoPerso.experience">
        <span style="color:red;font-weight:bold;"
              v-show="infoPerso.experience == '' || !isFinite(infoPerso.experience)">Nombre d'années invalide</span>
      </div>
    </div>

    <div class="containerInput">
      <label v-bind:style = "stylep">Email</label>
      <div class="inputWithPicto">
        <i  class="fa fa-envelope-o fa-2x picto" aria-hidden="true"></i>
        <input class="inputInfo" name="email" type="email"
               v-model="infoPerso.mail" disabled>
      </div>
    </div>

    <div class="containerInput">
      <label v-bind:style = "stylep">Téléphone</label>
      <div class="inputWithPicto">
        <i class="fa fa-phone fa-2x picto" aria-hidden="true"></i>
        <input class="inputInfo" name="telephone" placeholder="tel" v-model="infoPerso.telephone">
        <span style="color:red;font-weight:bold;" v-show="infoPerso.telephone == ''">Téléphone invalide</span>
        <img v-bind:src="pictoTelSrc" :style="styleTel" class="pictoInput">
      </div>
    </div>

    <div class="containerInput" style="height: auto">
      <label v-bind:style = "stylep">Langues</label>

      <div class="inputWithPicto" >
        <i class="fa fa-language fa-2x picto" aria-hidden="true"></i>
        <vSelect multiple v-model="infoPerso.languages" taggable
                 :options="languages" class="selectorStyle"></vSelect>
      </div>
    </div>

    <div class="containerInput">
      <label v-bind:style = "stylep">Hobbies</label>
      <div class="inputWithPicto">
        <i class="fa fa-gamepad fa-2x picto" aria-hidden="true"></i>
        <input class="inputInfo" name="hobbies" maxlength="50" placeHolder="hobbies"
               v-model="infoPerso.hobbies">
      </div>
    </div>
  </div>
</template>

<script>

import axios from "axios"
import config from "../../config/config"
import vSelect from 'vue-select'
  export default{
    components:{vSelect},
    methods:{
      changePhoto: function () {
        let text = document.getElementById("textChange");
        text.style.opacity = 1;
        text.style.zIndex = 1;
        let inputFile = document.getElementById("inputFile");
        let photo = document.getElementById("photoP");
        photo.style.opacity=0.2;
        text.onclick = function () {
          occur++;
          if(occur==1){
            inputFile.style.display="block";
            inputFile.style.position = "relative";
            inputFile.style.width ="11em";
            text.style.opacity=0;
          }
        };
        document.getElementById('inputFile').addEventListener('change', this.handleFileSelect, false);
      },
      handleFileSelect: function () {
        var file = event.target.value;
        let tab = file.split("\\");
        let srcChanging = "../static/users/"+tab[tab.length-1];
        let photo=document.getElementById("photoP");
        photo.src=srcChanging;
        let inputF = document.getElementById("inputFile");
        inputF.style.display="none";
        occur = 0;
        photo.style.opacity=1;
      },
      initPhoto: function () {
        let text = document.getElementById("textChange");
        document.getElementById("photoP").style.opacity=1;
        text.style.opacity = 0;
        text.style.zIndex = -2;
      },
    },
    data: function () {
      return {
        occur:0,
        title: 'Informations personnelles',
        stylep: style,
        stylei: styleInput,
        stylePh: stylePhoto,
        styleDv: styleMainDiv,
        stylePicT:stylePictoHeader,
        styleHead: styleHeader,
        stylea: styleA,
        styleSp: styleSpan,
        styleTel:stylePictoTel,
        styleCal:stylePictoCal,
        photoProfileSrc: "../../static/png/viseo-logo.png",
        pictoTelSrc:"../../static/png/flag-fr.png"
      };
    },
    mounted:function(){

    },
//    computed:{
//      computeAge: function(){
//        Math.floor((new Date().getTime()-new Date(this.infoPerso[field]).getTime())/(1000*3600*24*365));
//      }
//    },
    props:['infoPerso','languages']
  }

  var style = {
    height:"9px",
    width:"100%",
    color: "rgb(180,180,180)",
    'text-indent' : "15%",
    'font-family': "Caflisch Script Pro",
    'font-style': "italic",
    'font-size': "11px"
  };

  var styleInput = {
    border: "none",
    'border-bottom': "0.5px solid rgb(180,180,180)",
    width: "100%",
    'font-size':"90%",
    'font-family': "New Century Schoolbook, serif",
    'background-color': '#F2F3F4'
  };

  var stylePhoto ={
    height : "80px",
    width: "80px",
    'border-radius': "50%",
  };

  var styleMainDiv = {
    display: 'flex',
    'flex-direction':'column',
    'align-items': 'center',
    'justify-content': 'space-between',
    height: "100%",
    width: "100%",
    'background-color': '#F2F3F4',
    'margin-left': "2%"
  };

  var stylePictoHeader = {
    'margin-right':'0.5em'
  };

  var styleHeader = {
    display: 'flex',
    'justify-content': 'center',
    'align-items':'center',
    'background-color': "#229954",
    'text-align': "center",
    height:"2.5em",
    width: "100%",
    'font-family': "New Century Schoolbook, serif",
    color: 'white',
  };

  var styleA = {
    position: "relative",
    'text-align': "center"
  };

  var styleSpan ={
    position: 'absolute',
    'text-shadow': "0.1em 0.1em 0.2em black",
    color: "white",
    cursor: "pointer",
    padding: '30px 12px',
    'text-align':'center',
    'border-radius':'50%',
    opacity: "1",
    '-webkit-transition': "opacity 500ms",
    '-moz-transition': "opacity 500ms",
    '-o-transition': "opacity 500ms",
    transition: "opacity 500ms",
    'z-index': "-2",
  };



  var stylePictoTel= {
    "height": '18px',
    "width":'18px',
    "position":'relative',
    "left":'8px',
    "top":'3px'
  };

  var stylePictoCal={
    height: "1em",
    position: "relative",
    left: "-1em",
    'pointer-events': "none",
    'background-color': "white"
  };

  let occur = 0;

</script>

<style>
  .inputInfo{
    border: none;
    border-bottom: 0.5px solid rgb(180,180,180);
    width: 80%;
    font-size:90%;
    font-family: New Century Schoolbook, serif;
    background-color: #F2F3F4;
  }

  .containerInput{
    display: flex;
    width: 80%;
    height: 3em;
    flex-direction: column;
    align-items: flex-start;
    justify-content: space-around;
  }

  .inputWithPicto{
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    width:100%;
  }

  .picto{
    margin-right: .5em;
  }

  .pictoInput{
    position: relative;
    left: -1em;
    top: 0.5em;
    pointer-events: none;
    background-color: #F2F3F4;
  }
  .date{
    cursor: pointer;
  }

  .photoUser{
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .selectorStyle{
    margin: 10px 0px 5px 0px;
  }

</style>
