<template>
    <div class="display">
        <banner></banner>
        <div class="content" :style="styleContent">
            <div class="topcontent" :style="styleTopContent">
                <div id="Inscription" :style="styleInscription" @click="inscriptionClick"><p>Inscription</p></div>
                <div id="Connexion" :style="styleConnexion" @click="connexionClick"><p>Connexion</p></div>
            </div>
            <div id="InscriptionContent" v-show="chosenForm==='inscription'">
                <formhome
                  :listitems="signInForm"
                  :styl="style"
                  @login="register"
                  @check="inputManager">
                </formhome>
            </div>
            <div id="ConnexionContent" :style="styleConnexionContent" v-show="chosenForm==='connexion'">
                <formhome
                  :listitems="logInForm"
                  :styl="style"
                  @login="login"
                  @check="inputManager">
                </formhome>
              <div id="connexion-failed" v-if="connexionFailed">Utilisateur inconnu</div>
            </div>
        </div>
    </div>
</template>

<script>
  import formHome from './homePage/formHome.vue'
  import banner from "./banner.vue"
  import axios from 'axios'

  export default {
    data:function (){
      return{
        signInForm: [
          {id: 0, type: 'text', placeholder: 'ABC1234',label: 'Code de login', fa: 'fa fa-th',name: 'signInForm'},
          {id: 1, type: 'text', placeholder: 'DUPONT', label: 'Nom', fa: 'fa fa-user',name: 'signInForm'},
          {id: 2, type: 'text', placeholder: 'Eric', label: 'Prénom', fa: 'fa fa-user',name: 'signInForm'},
          {id: 3, type: 'email', placeholder: 'eric.dupont@viseo.com', label: 'Email', fa: 'fa fa-envelope', name: 'signInForm'},
          {id: 4, type: 'password', placeholder: '••••••', label: 'Mot de passe', fa: 'fa fa-lock', eye: 'fa fa-eye', value: '',name: 'signInForm'},
          {id: 5, type: 'password', placeholder: '••••••', label: 'Confirmation du mot de passe', fa: 'fa fa-lock', eye: 'fa fa-eye', value: '',name: 'signInForm'},
          {id: 6, type: 'submit', value: 'S\'inscrire'}
        ],

        logInForm: [
          {id: 0, type: 'email', placeholder: 'eric.dupont@viseo.com', label: 'Email', fa: 'fa fa-envelope', name: 'logInForm'},
          {id: 1, type: 'password', placeholder: '••••••', label: 'Mot de passe', fa: 'fa fa-lock', eye: 'fa fa-eye', value: '',name: 'logInForm'},
          {id: 2, type: 'checkbox', value: 'keepConnected', checked: 'false', checkMsg: 'Rester connecté', href: '#'},
          {id: 4, type: 'submit', value: 'Se connecter'}
        ],

        formChecked:{
          signIn: {
            "login": false,
            "nom": false,
            "prenom": false,
            "email": false,
            "password": false,
            "checkpassword": false,
          },
          logIn: {
            "email": false,
            "password": false
          }
        },
        connexionFailed : false,

        chosenForm: 'inscription',

        style: {
          styleGlobal: {
            'margin-top': '20px',
            'font-family': 'Arial',
            'font-size': '0.8em',
            'font-weight': 'bold',
            position: 'relative',
            display: 'flex',
            'flex-direction': 'column',
            'align-items': 'center'
          },
          divForm: {
            position: 'relative',
          },

          styleForm: {
            padding: '1em 0px 1em 2.3em',
            width: '27em',
            border: '1px solid #ccc',
            'border-radius': '4px',
            'box-sizing': 'border-box',
            'margin-top': '-0.6em',
            'box-shadow': 'inset 0 0.01em 0.02em rgba(0,0,0,.39)',
            height: '2.5em'
          },

          styleSubmit: {
            // padding: '1em 0px 1em 2.3em',
            'font-size': '1.04em',
            'font-weight': 'bold',
            width: '27em',
            border: '0px solid #ccc',
            'border-radius': '4px',
            'background-color' : 'rgb(254,204,19)',
            'text-align': 'center',
            color: 'white',
            'margin-bottom': '2em',
            'box-shadow': '0 0.01em 0.02em rgba(0,0,0,.39)',
            height: '2.5em'

          },

          styleLabel: {
            display: 'flex',
            'align-items': 'left',
            'margin-bottom': '15px'
          },

          styleConfig: {
            display: 'flex',
            'flex-orientation': 'row',
            'align-items': 'center',
            'justify-content': 'space-between',
            'margin-bottom': '15px'
          },

          stylePicto: {
            position: 'absolute',
            'left': '1em',
            'top': '0px',
            'z-index': '10'
          },

          styleEye: {
            position: 'absolute',
            'right': '10px',
            'top': '0px',
            'z-index': '10'
          },

          styleMessage: {
            display: 'flex',
            'height':'1em',
            'font-size' : '1em',
            'margin-bottom':'3%',
            'color': 'rgb(182,18,47)',
            'font-weight': 'normal',
          }
        },

        styleContent: {
          display: 'flex',
          'flex-direction': 'column',
          width:'50%',
          'margin-left':'25%',
          'box-shadow': '0 2px 5px rgba(0,0,0,0.14), 0 2px 5px rgba(0,0,0,0.24)'
        },

        styleTopContent:{
          display: 'flex',
          'flex-direction': 'row',
          'align-items': 'center',
          'background-color' : 'rgb(255,255,255)',
          'font-family': 'Arial',
          'font-size': '1em',
        },

        styleInscription:{
          display: 'flex',
          flex: '1',
          'justify-content': 'center',
          'background-color' : 'rgb(254,204,19)',
          'color':'rgb(255,255,255)',
        },

        styleConnexion:{
          display: 'flex',
          flex: '1',
          'justify-content': 'center',
          'background-color' : 'rgb(235,235,235)',
          'color':'rgb(120,120,120)',

        },

        styleConnexionContent:{
          display:'none',
        }
      }
    },
    methods:{
      inscriptionClick : function(){
        let inscription=document.getElementById("Inscription");
        let connexion=document.getElementById("Connexion");

        inscription.style.color='rgb(255,255,255)';
        inscription.style.backgroundColor='rgb(254,204,19)';

        connexion.style.color='rgb(120,120,120)';
        connexion.style.backgroundColor='rgb(235,235,235)';

        this.chosenForm==='connexion'?this.chosenForm='inscription':{};
      },

      connexionClick : function(){
        let inscription=document.getElementById("Inscription");
        let connexion=document.getElementById("Connexion");

        inscription.style.color='rgb(120,120,120)';
        inscription.style.backgroundColor='rgb(235,235,235)';

        connexion.style.color='rgb(255,255,255)';
        connexion.style.backgroundColor='rgb(254,204,19)';

        this.chosenForm==='inscription'?this.chosenForm='connexion':{};
      },

      inputManager : function(label,form){
        if(label==="Code de login")
        {
          let value = document.getElementById(label+form).value;
          let re = new RegExp("^[A-Z]{3,3}[0-9]{4,4}$");
//          if(value===''){
//            this.formChecked.signIn.login=false;
//            document.getElementById("message"+label+form).innerHTML="";
//          }
//          else if(re.test(value)){
            if(re.test(value)){
              this.formChecked.signIn.login=true;
              document.getElementById("message"+label+form).innerHTML="";
            }
//            else {
//              this.formChecked.signIn.login=false;
//              document.getElementById("message"+label+form).innerHTML="Le login doit etre contenu entre 3 et 20 caractères";
//            }
//          }
          else{
            this.formChecked.signIn.login=false;
            document.getElementById("message"+label+form).innerHTML="Veuillez entrer un code de login valide";

          }
        }
        else if(label=="Nom")
        {
          let value = document.getElementById(label+form).value;
          let re = new RegExp("^[a-zA-Z'éèÉ. -]*$");
          if(value===''){
            this.formChecked.signIn.nom=false;
            document.getElementById("message"+label+form).innerHTML="";
          }
          else if(re.test(value)){
            if(value.length>=2&&value.length<=30){
              this.formChecked.signIn.nom=true;
              document.getElementById("message"+label+form).innerHTML="";
            }
            else {
              this.formChecked.signIn.nom=false;
              document.getElementById("message"+label+form).innerHTML="Veuillez entrer un nom valide";
            }
          }
          else{
            this.formChecked.signIn.nom=false;
            document.getElementById("message"+label+form).innerHTML="Caractères non autorisés";

          }
        }

        else if(label==="Prénom")
        {
          let value = document.getElementById(label+form).value;
          let re = new RegExp("^[a-zA-Z'éèÉ. -]*$");

          if(value===''){
            this.formChecked.signIn.prenom=false;
            document.getElementById("message"+label+form).innerHTML="";
          }
          else if(re.test(value)){
            if(value.length>=2&&value.length<=30){
              this.formChecked.signIn.prenom=true;
              document.getElementById("message"+label+form).innerHTML="";
            }
            else {
              this.formChecked.signIn.prenom=false;
              document.getElementById("message"+label+form).innerHTML="Veuillez entrer un prénom valide";
            }
          }
          else{
            this.formChecked.signIn.prenom=false;
            document.getElementById("message"+label+form).innerHTML="Caractères non autorisés";

          }
        }

        else if(label==="Email")
        {
          let value=document.getElementById(label+form).value;
          let re = new RegExp((/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9\u00C0-\u017F]+\.)+[a-zA-Z\-0-9\u00C0-\u017F]{1,}))$/));
          if(form=="signInForm") {
            if (value === '') {
              this.formChecked.signIn.email = false;
              document.getElementById("message" + label + form).innerHTML = "";
            }
            //master regex
            if (re.test(value)) {
              this.formChecked.signIn.email = true;
              document.getElementById("message" + label + form).innerHTML = "";
            }
            else {
              this.formChecked.signIn.email = false;
              document.getElementById("message" + label + form).innerHTML = "Adresse invalide";
            }
          }
          else{
            if (value === '') {
              this.formChecked.logIn.email = false;
              document.getElementById("message" + label + form).innerHTML = "";
            }
            //master regex
            if (re.test(value)) {
              this.formChecked.logIn.email = true;
              document.getElementById("message" + label + form).innerHTML = "";
            }
            else {
              this.formChecked.logIn.email = false;
              document.getElementById("message" + label + form).innerHTML = "Veuillez entrer une adresse valide";
            }
          }
        }
        else if(label==="Mot de passe")
        {
          let value=document.getElementById(label+form).value;

          if(form=="signInForm") {
            let passwordvalue = document.getElementById("Confirmation du mot de passe"+form).value;
            if (value === '') {
              this.formChecked.signIn.password = false;
              document.getElementById("message" + label+ form).innerHTML = "";
            }
            else if (value.length >= 6) {
              this.formChecked.signIn.password = true;
              document.getElementById("message" + label+ form).innerHTML = "";
              document.getElementById("message" + "Confirmation du mot de passe"+form).innerHTML = "";
            }
            else if (value === passwordvalue) {
              this.formChecked.signIn.checkpassword = true;
              document.getElementById("message" + "Confirmation du mot de passe"+form).innerHTML = "";
            }
            else {
              this.formChecked.signIn.password = false;
              document.getElementById("message" + label+ form).innerHTML = "Veuillez entrer au moins 6 caractères";
              if (value != passwordvalue) {
                this.formChecked.signIn.checkpassword = false;
                document.getElementById("message" + "Confirmation du mot de passe"+form).innerHTML = "Les passwords sont différents";
              }
            }
          }
          else{

            if (value === '') {
              this.formChecked.logIn.password = false;
              document.getElementById("message" + label+ form).innerHTML = "";
            }
            else if (value.length >= 6) {
              this.formChecked.logIn.password = true;
              document.getElementById("message" + label+ form).innerHTML = "";
            }
            else {
              this.formChecked.logIn.password = false;
              document.getElementById("message" + label+ form).innerHTML = "Le mot de passe doit avoir au minimum 6 caractères";

            }
          }
        }
        else if(label==="Confirmation du mot de passe")
        {
          let passwordvalue = document.getElementById("Mot de passe"+form).value;
          let value = document.getElementById(label+ form).value;
          if(value===''){
            this.formChecked.signIn.checkpassword=false;
            document.getElementById("message"+label+ form).innerHTML="";
          }
          else if(value===passwordvalue){
            this.formChecked.signIn.checkpassword=true;
            this.formChecked.signIn.password=true;
            document.getElementById("message"+label+ form).innerHTML="";
          }
          else{

            this.formChecked.signIn.checkpassword=false;
            document.getElementById("message"+label+ form).innerHTML="La confirmation du mot de passe n'est pas valide";

          }
        }
      },
      checkSubmitRegister : function(){
          let a=0;
          console.log('test')
          for(let i in this.formChecked.signIn){
            if(this.formChecked.signIn[i]===false){
                console.log(this.formChecked.signIn[i])
              a++;
            }
          }

          if(a===0){
            return true;
          }
          else{
            return false;
          }
      },
      checkSubmitConnexion: function(){
        let a=0;

        for(let i in this.formChecked.logIn){
          if(this.formChecked.logIn[i]===false){
            a++;
          }
        }

        if(a===0){
          return true;
        }
        else{
          return false;
        }
      },
      toggleShowMenu: function() {
        this.showMenu=!this.showMenu;
      },
      login: function () {
        //Need Check
        if(this.checkSubmitConnexion()) {
          let mail = document.getElementById("EmaillogInForm").value;
          let password = document.getElementById("Mot de passelogInForm").value;

          axios.get('http://cv360-dev.lan:8061/api/login', {
            params: {
              mail: mail,
              password: password
            }
          })
          .then((response) => {
//            console.log(response, 'coucou')

            if (response) {
//              alert('okok')
              window.location.href = '/mycv';
            }
            else {
              this.connexionFailed = true;
            }
          })
          .catch((error) => {
            console.log(error, "pouf");
          });

//          let xhr = new XMLHttpRequest();
//          xhr.open('GET', 'http://cv360-dev.lan:8061/api/login',true);
//          xhr.setRequestHeader("Content-type", "application/json");
//          xhr.send(data);
        }
        else{
            alert("Remplir tous les champs");
        }

      },
      register: function(){
        if(this.checkSubmitRegister()){
          let code = document.getElementById("Code de loginsignInForm").value;
          let name = document.getElementById("NomsignInForm").value;
          let firstName = document.getElementById("PrénomsignInForm").value;
          let password = document.getElementById("Mot de passesignInForm").value;
          let mail = document.getElementById("EmailsignInForm").value;

          let data = {
            login:code,
            lastName:name,
            firstName:firstName,
            password:password,
            mail:mail
          };

          axios.post('/api/register', data)
            .then((response)=>{
              if(response){
                console.log("registered");
              }
              else{
                console.log("this person already has an account");
              }
            })
            .catch((error)=>{
              console.log(error);
            });
        }
        else{
            alert("Remplir tous les champs correctement");
        }
      }
    },
    components:{
      banner:banner,
      formhome:formHome
    }
  }

</script>

<style>
  #connexion-failed{
    text-align:center;
    color:rgb(255,10,50);
     margin-bottom: 1em;
  }
</style>
