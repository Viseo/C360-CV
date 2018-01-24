<template>
  <div class="userProfile">
    <div class="bannerProfile">
      1. Mes coordonnées
    </div>
    <div class="contentDetails">
      <div class="details">
        <div class="divInputProfile">
          <label class="labelProfile">Prénom</label>
          <div>
            <i class="fa fa-user pictoProf"></i>
            <input type="text" class="inputProfile" name="prenomProfile" v-on:keyup="verificationChar">
          </div>
          <span class="errorPr" id="prénomError">Veuillez entrer un prénom valide</span>
        </div>
        <div class="divInputProfile">
          <label class="labelProfile">Fonction</label>
          <div>
            <i class="fa fa-tag pictoProf"></i>
            <input type="text" class="inputProfile">
          </div>
        </div>
      </div>
      <div class="details">
        <div class="divInputProfile">
          <label class="labelProfile">Nom</label>
          <div>
            <i class="fa fa-user pictoProf"></i>
            <input type="text" class="inputProfile" name="nomProfile" v-on:keyup="verificationChar">
          </div>
          <span id="nomError" class="errorPr" >Veuillez entrer un nom valide</span>
        </div>
        <div class="divInputProfile">
          <label class="labelProfile">Business Unit</label>
          <div>
            <select class="selectProfile">
              <option disabled selected></option>
              <option>Viseo Technologies</option>
              <option>Viseo Digital</option>
              <option>Viseo Data & Process</option>
            </select>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default{
    methods: {
      verificationChar: function () {
        let toShow = false;
        for (let letter of event.target.value) {
          if (!((letter.charCodeAt(0) >= 97 && letter.charCodeAt(0) <= 122) || (letter.charCodeAt(0) >= 65 && letter.charCodeAt(0) <= 90) ||
            letter.charCodeAt(0) == 32 || letter.charCodeAt(0) == 39 || letter.charCodeAt(0) == 46 || letter.charCodeAt(0) == 45 ||
            (letter.charCodeAt(0) >= 232 && letter.charCodeAt(0) <= 235))) {
            toShow = true;
          }
        }
        let selectedInput = "";
        switch (event.target.name) {
          case "prenomProfile":
            selectedInput = "prénomError";
            break;
          case "nomProfile":
            selectedInput = "nomError";
            break;
        }
        let alertMessage = document.getElementById(selectedInput);
        if (toShow == false) {
          alertMessage.style.display = "none";
        } else {
          alertMessage.style.display = "block";
          alertMessage.style.opacity = 1;
          alertMessage.style.color = "rgb(255,0,0)";
          alertMessage.style.textAlign = "center";
        }
        if(event.charCode==0){
          this.validateForm();
        }
      },
      validateForm:function () {
        let selectedInput="";
        switch (event.target.name){
          case "prenomProfile":
            selectedInput = "prénomError";
            break;
          case "nomProfile":
            selectedInput = "nomError";
            break;
        }
        let alertMessage = document.getElementById(selectedInput);
        if((event.target.value.length<2 || event.target.value.length>125) &&
          (event.target.name =="prenomProfile" || event.target.name == "nomProfile")){
          alertMessage.style.display="block";
          alertMessage.style.opacity=1;
          alertMessage.style.color="rgb(255,0,0)";
          alertMessage.style.textAlign= "center";
        }
      }
    }
  }
</script>

<style>
  .userProfile{
    font-family: Calibri, serif;
    display: flex;
    flex-direction: column;
    align-items: center;
    border: 1px solid #A6ACAF ;
    width: 40em;
    margin-bottom: 2em;
  }

  .bannerProfile{
    display: flex;
    justify-content: flex-start;
    width: 100%;
    height: 1.5em;
    background-color: #A6ACAF;
    color: white;
  }

  .contentDetails{
    display: flex;
    width: 100%;
    height: 100%;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin: 1em 0;
  }

  .details{
    display: flex;
    width: 80%;
    justify-content: space-between;
  }

  .divInputProfile{
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    margin: 1em 0 1em 0em;
    position: relative;
  }

  .inputProfile{
    width: 15em;
    height: 2em;
    border: 1px solid #CACFD2;
    border-radius: 3px;
    box-shadow: inset 0 0.01em 0.02em rgba(0,0,0,.39);
    outline: none;
    padding-left: 2em;
  }
  .divInputProfile div{
    display: flex;
    flex-direction: row;
  }

  .selectProfile{
    width: 17em;
    height: 2.2em;
    border: 1px solid #CACFD2;
    border-radius: 3px;
    box-shadow: inset 0 0.01em 0.02em rgba(0,0,0,.39);
    outline: none;
  }

  .labelProfile{
    font-weight: bold;
    margin-bottom: 0.3em;
  }

  .pictoProf{
    position: absolute;
    left: .5em;
    top: 1.9em;
  }

  .errorPr{
    color: red;
    display: none;
    font-size: 14px;
  }


</style>
