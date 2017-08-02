<template>
  <div v-bind:style="styleDv">
    <div v-bind:style="styleHead"><i v-bind:style = "stylePicT" class="fa fa-address-card-o fa-lg" aria-hidden="true"></i> {{title}}</div>
    <div v-bind:style="styleDiv2">
      <a>
        <img id="photoP" v-bind:src = "photoProfileSrc" v-on:mouseover="changePhoto" v-bind:style="stylePh" aria-hidden="true">
        <input type="file" id="inputFile" accept="image/*" name="picture" @change="updateInfoUser" style="color: transparent; display: none">
      </a>
      <span id="textChange" v-bind:style = "styleSp" v-on:mouseout="initPhoto">Modifier</span>
    </div>
    <form>
      <p v-bind:style = "stylep">Prénom</p>
      <i v-bind:style = "stylePic" class="fa fa-user-o" aria-hidden="true"></i>
      <input v-bind:style="stylei" name = "firstName" v-on:keyup="verificationChar" @input="updateInfoUser" maxlength="125" type="text" placeholder="Prenom"
             v-model="firstName">
      <span id="alM" style="opacity: 0; font-size: 10px; display: none">Veuillez entrer un prénom valide</span>

      <p v-bind:style = "stylep">Nom</p>
      <i v-bind:style = "stylePic" class="fa fa-user-o" aria-hidden="true"></i>
      <input v-bind:style="stylei" name="name" v-on:keyup="verificationChar" @input="updateInfoUser(); name=name.toUpperCase()" placeHolder="Nom"
             v-model="name">
      <span id="alM1" style="opacity: 0; font-size: 10px; display: none">Veuillez entrer un nom valide</span>

      <p v-bind:style = "stylep">Age</p>
      <i v-bind:style = "stylePic" class="fa fa-birthday-cake" aria-hidden="true"></i>
      <input v-bind:style="stylei" name="birth" type="date" placeHolder="" v-model="birth" @input="updateInfoUser">
      <span><i id="calendar_icon" v-bind:style = "styleCal" class="fa fa-calendar" aria-hidden="true"></i> </span>

      <p v-bind:style = "stylep">Fonction</p>
      <i v-bind:style = "stylePic" class="fa fa-tag" aria-hidden="true"></i>
      <input v-bind:style="stylei" name="fonction" v-on:keyup="verificationChar" @input="updateInfoUser" maxlength="125" placeHolder="Poste"
             v-model="fonction">
      <span id="alM2" style="opacity: 0; font-size: 10px; display: none">Veuillez entrer une fonction valide</span>

      <p v-bind:style = "stylep">Expérience (en année)</p>
      <i v-bind:style = "stylePic" class="fa fa-tachometer" aria-hidden="true"></i>
      <input v-bind:style="stylei" name="experience" v-on:keyup="verificationChar" @input="updateInfoUser" placeHolder="Années d'expérience"
             v-model="experience">
      <span id="alM5" style="opacity: 0; font-size: 10px; display: none">Veuillez entrer un nombre d'années valide</span>

      <p v-bind:style = "stylep">Email</p>
      <i v-bind:style = "stylePic" class="fa fa-envelope-o" aria-hidden="true"></i>
      <input v-bind:style="stylei" name="email" type="email" v-on:keyup="verificationEmail" @input="updateInfoUser" placeHolder="mail@viseo.fr"
             v-model="email">
      <span id="alM6" style="opacity: 0; font-size: 10px; display: none">Veuillez entrer une adresse email valide</span>

      <p v-bind:style = "stylep">Téléphone</p>
      <i v-bind:style = "stylePic" class="fa fa-phone" aria-hidden="true"></i>
      <input v-bind:style="stylei" name="telephone" placeholder="tel" v-model="telephone" @input="updateInfoUser">
      <img v-bind:src="pictoTelSrc" v-bind:style="styleToTel">

      <p v-bind:style = "stylep">Langues</p>
      <i v-bind:style = "stylePic" class="fa fa-language" aria-hidden="true"></i>
      <input v-bind:style="stylei" name="languages" v-on:keyup="verificationChar" @input="updateInfoUser" maxlength="50" placeHolder="langues"
             v-model="languages">
      <span id="alM3" style="opacity: 0; font-size: 10px; display: none">Veuillez entrer une langue valide</span>

      <p v-bind:style = "stylep">Hobbies</p>
      <i v-bind:style = "stylePic" class="fa fa-gamepad" aria-hidden="true"></i>
      <input v-bind:style="stylei" name="hobbies" v-on:keyup="verificationChar" @input="updateInfoUser" maxlength="50" placeHolder="hobbies"
             v-model="hobbies">
      <span id="alM4" style="opacity: 0; font-size: 10px; display: none">Veuillez entrer un hobbie valide</span>
    </form>
  </div>
</template>

<script>


  var style = {
    height:"9px",
    color: "rgb(180,180,180)",
    'text-indent' : "15%",
    'font-family': "Caflisch Script Pro",
    'font-style': "italic",
    'font-size': "11px"
  };

  var styleInput = {
    border: "none",
    'border-bottom': "0.5px solid rgb(180,180,180)",
    position: "relative",
    left: "3%",
    width: "80%",
    'font-size':"90%",
    'font-family': "New Century Schoolbook, serif",

  };

  var stylePhoto ={
    height : "80px",
    width: "80px",
    display: "block",
    margin: "20px 0",
    position: "relative",
    left: "35%",
    'border-radius': "50px",
    '-moz-box-shadow': "0px 0px 10px #343434",
    '-webkit-box-shadow': "0px 0px 10px #343434",
    '-o-box-shadow': "0px 0px 10px #343434",
    'box-shadow': "0px 0px 10px #343434"
  };

  var styleMainDiv = {
    height: "100%",
    width: "100%",
    border: "0.5px solid rgb(220,220,220)",
    'border-radius': "15px",
    'margin-left': "2%"
  };

  var stylePicto = {
    position: "relative",
    left: "2%"
  };

  var stylePictoHeader = {
    position: "relative",
  };

  var styleHeader = {
    background: "rgb(215,215,215)",
    'text-align': "center",
    height:"1.7em",
    width: "100%",
    'padding-top':"2%",
    'border-top-right-radius': "8px",
    'border-top-left-radius': "8px",
    'font-family': "New Century Schoolbook, serif"
  };

  var styleA = {
    position: "relative",
    'text-align': "center"
  };

  var styleSpan ={
    'text-shadow': "0.1em 0.1em 0.2em black",
    color: "white",
    cursor: "pointer",
    display: "table",
    height: "50px",
    left: "38%",
    position: "absolute",
    top: "0",
    width: "70px",
    opacity: "0",
    '-webkit-transition': "opacity 500ms",
    '-moz-transition': "opacity 500ms",
    '-o-transition': "opacity 500ms",
    transition: "opacity 500ms",
    'border-radius':"50%",
    'z-index': "-2",
    'padding-top': "10%"
  };

  var styleDivImage = {
    position: "relative"
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

  export default{
    props: ['today'],
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
            inputFile.style.left ="20%";
            inputFile.style.height = "40%";
            text.style.opacity=0;
          }
        }
        document.getElementById('inputFile').addEventListener('change', this.handleFileSelect, false);
      },
      handleFileSelect: function () {
        var file = event.target.value;
        let tab = file.split("\\");
        let srcChanging = "../static/"+tab[tab.length-1];
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
      verificationChar: function () {
        let toShow = false;
        for(let letter of event.target.value){
          if(!((letter.charCodeAt(0)>=97 && letter.charCodeAt(0)<=122) ||(letter.charCodeAt(0)>=65 && letter.charCodeAt(0)<=90)||
            letter.charCodeAt(0)==32 || letter.charCodeAt(0)==39 || letter.charCodeAt(0)==46 || letter.charCodeAt(0)==45||
            (letter.charCodeAt(0)>=232 && letter.charCodeAt(0)<=235))){
            toShow = true;
          }
        }
        let selectedInput="";
        switch (event.target.name){
          case "firstName":
            selectedInput = "alM";
            break;
          case "name":
            selectedInput ="alM1";
            break;
          case "fonction":
            selectedInput ="alM2";
            break;
          case "languages":
            selectedInput ="alM3";
            break;
          case "hobbies":
            selectedInput ="alM4";
            break;
          case "experience":
            selectedInput ="alM5";
            break;
        }
        let alertMessage = document.getElementById(selectedInput);
        if(toShow==false || event.target.name == "experience"){
          alertMessage.style.display="none";
        }else{
          alertMessage.style.display="block";
          alertMessage.style.opacity=1;
          alertMessage.style.color="rgb(255,0,0)";
          alertMessage.style.textAlign= "center";
        }
        if(event.charCode==0){
          this.validateForm();
        }
      },
      verificationEmail: function () {
        let toShow= true;
        for(let letter of event.target.value){
          if(letter.charCodeAt(0)==64)toShow=false;
        }
        let alertMessage = document.getElementById("alM6");
        if(toShow){
          alertMessage.style.display="block";
          alertMessage.style.opacity=1;
          alertMessage.style.color="rgb(255,0,0)";
          alertMessage.style.textAlign= "center";
        }else{
          alertMessage.style.display="none";
        }
      },
      validateForm:function () {
        let toExperienceInput = () =>{
          let toShow = true;
          for(let letter of event.target.value){
            if(letter.charCodeAt(0)>=48 && letter.charCodeAt(0)<=57){
              toShow = false;
            }
          }
          return toShow;
        };
        let selectedInput="";
        switch (event.target.name){
          case "firstName":
            selectedInput = "alM";
            break;
          case "name":
            selectedInput ="alM1";
            break;
          case "fonction":
            selectedInput ="alM2";
            break;
          case "hobbies":
            selectedInput = "alM4";
            break;
          case "languages":
            selectedInput ="alM3";
            break;
          case "experience":
            selectedInput ="alM5";
            break;
        }
        let alertMessage = document.getElementById(selectedInput);
        if(((event.target.value.length<2 || event.target.value.length>50) &&
          (event.target.name =="langues" || event.target.name == "hobbies"))||
          (event.target.name=="experience" && (toExperienceInput() ||
          (event.target.value.length<2  || event.target.value.length>50)))||
          ((event.target.value.length<2 || event.target.value.length>125) &&
          (event.target.name =="prenom" || event.target.name == "nom" || event.target.name == "fonction"))){

            alertMessage.style.display="block";
            alertMessage.style.opacity=1;
            alertMessage.style.color="rgb(255,0,0)";
            alertMessage.style.textAlign= "center";
        }
      },
      updateInfoUser: function(){
        console.log(this.infoUser.birth)
        let infoPerso = {
          name: this.infoUser.name,
          firstName: this.infoUser.firstName,
          birth: this.infoUser.birth,
          fonction: this.infoUser.fonction,
          experience: this.infoUser.experience,
          email: this.infoUser.email,
          telephone: this.infoUser.telephone,
          hobbies: this.hobbies,
          languages: this.infoUser.languages,
          picture: this.infoUser.picture,
          age: this.infoUser.age,
        };
        console.log(event.target.value, 'putain de merde')
        for(let field in infoPerso){
          if(field==event.target.name){
            if(field=='name'){
              infoPerso[field]=event.target.value.toUpperCase();
            }else if(field=='picture'){
              infoPerso[field]=event.target.value.split('\\').slice(-1)[0];
            }else if(field=='birth'){
              infoPerso[field]=event.target.value;
              console.log(Math.floor((new Date(this.today).getTime())),new Date(infoPerso[field]).getTime(),(1000*3600*24*365),infoPerso[field],field)
              infoPerso['age']=Math.floor((new Date(this.today).getTime()-new Date(infoPerso[field]).getTime())/(1000*3600*24*365));
            }else{
              infoPerso[field]=event.target.value;
            }
          }
          console.log(infoPerso[field])
        }




//          let infoPerso = {
//            name: this.name,
//            firstName: this.firstName,
//            birth: this.birth,
//            fonction: this.fonction,
//            experience: this.experience,
//            email: this.email,
//            telephone: this.telephone,
//            hobbies: this.hobbies,
//            languages: this.languages,
//            picture: this.photoProfileSrc,
//            age: '',
//          };
//        for(let field in infoPerso){
//          if(field==event.target.name){
//            if(field=='picture'){
////                console.log(event.target.value.split('\\').slice(-1)[0], 'picture')
//              infoPerso[field]=event.target.value.split('\\').slice(-1)[0];
//            }else if(field=='birth'){
//              infoPerso['age']=Math.floor((new Date(this.today).getTime()-new Date(infoPerso[field]).getTime())/(1000*3600*24*365));
//            }else{
//              infoPerso[field]=event.target.value;
//            }
//          }
//          console.log(infoPerso[field])
//        }

        this.$emit('infoUser', infoPerso);
      }
    },
    data: function () {
      return {
        occur:0,
        title: 'Informations personnelles',
        stylep: style,
        stylei: styleInput,
        stylePh: stylePhoto,
        styleDv: styleMainDiv,
        stylePic: stylePicto,
        stylePicT:stylePictoHeader,
        styleHead: styleHeader,
        stylea: styleA,
        styleSp: styleSpan,
        styleDiv2: styleDivImage,
        styleToTel:stylePictoTel,
        styleCal:stylePictoCal,
        photoProfileSrc: "../../static/viseo-logo.png",
        pictoTelSrc:"../../static/flag-fr.png",

        name: this.infoUser.name,
        firstName: this.infoUser.firstName,
        birth: this.infoUser.birth,
        fonction: this.infoUser.fonction,
        experience: this.infoUser.experience,
        email: this.infoUser.email,
        telephone: this.infoUser.telephone,
        hobbies: this.infoUser.hobbies,
        languages: this.infoUser.languages,
        picture: this.infoUser.picture
      };
    },
    props:["infoUser","today"]
  }
</script>

<style>

</style>
