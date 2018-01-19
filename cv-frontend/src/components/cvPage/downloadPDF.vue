<template>

  <div>
    <div class="toPDF">
      <div class="savePDF" @click="updateUserProfileOnly">Sauvegarder</div>
      <div class="downloadPDF" @click="printPDF">Télécharger le PDF</div>
    </div>
  </div>

</template>

<script>

  import axios from 'axios'
  import config from '../../config/config'

  export default {
    props:['infoUser','languages'],
    data:function(){
      return{
      }
    },
    methods:{
      toDate:function(dateStr) {
        var parts = dateStr.split("-");
        return new Date(parts[0], parts[1] - 1, parts[2]);
      },
      updateUserProfileOnly:function(){
        console.log(this.infoUser);
        if(this.infoUser.firstName == "" || this.infoUser.lastName == "" || this.infoUser.birth_date == ''
            ||this.infoUser.position == '' || this.infoUser.experience == '' || !isFinite(this.infoUser.experience)
            || this.infoUser.telephone == ''){
          this.showWarning();
        }
        else{
          var newLanguages = [];
          let indexToRemove= []
          let k=0;
          for (let i=0;i<this.infoUser.languages.length;i++){
            if(!this.infoUser.languages[i].hasOwnProperty("id")){
              //check if there r any newly added languages
              console.log("detect new language! ");
              for(k=0;k<this.languages.length;k++){
                //check if the newly added language is existed in db (toLowerCase)
                if((this.infoUser.languages[i].label.toLowerCase() === this.languages[k].label.toLowerCase())){
                  console.log("Not new language!");
                  break;
                }
              }
              if(k > this.languages.length){
                //if the newly added language is really a new one, then add it to TOPOST list
                newLanguages.push(this.infoUser);
                indexToRemove.push(i);
              }
              else{
                //if the newly added language already exist, then add the exist one to inforUser
                //which is ready to update
                this.infoUser.languages[i] = this.languages[k];
              }
            }
          }

          for (let number in indexToRemove){
            this.infoUser.languages.slice(number,1);
          }
          if (newLanguages.length >0){
            axios.post(config.server + "/api/languages", newLanguages).then(response =>{
              for (let item in response.data){
                this.infoUser.push(item);
              }
              // now all newly added languages are well registered. we can continue to update our user
              var userTosave = this.infoUser;
              userTosave.birth_date = this.toDate(userTosave.birth_date);
              axios.post(config.server + '/api/updateOnlyUserProfile', userTosave)
                .then((response)=>{
                  console.log(response.data);
                  this.$store.commit("setUserWithoutMission",response.data);
                  this.showSuccess();
                })
                .catch((error)=> {
                  this.showError();
                  console.log("error: " + error);
                });
            }).catch(e =>{
              this.showError();
              console.log("error: " + e);
            });
          }
          else{
            var userTosave = this.infoUser;
            axios.put(config.server + '/api/updateOnlyUserProfile', userTosave)
              .then((response)=>{
                console.log(response.data);
                this.$store.commit("setUserWithoutMission",response.data);
                this.showSuccess();
              })
              .catch((error)=> {
                this.showError();
                console.log("error: " + error);
              });
          }

        }

      },
      printPDF: function () {
          let toShow = false;
          /*
          Test!!!!!!
          for(let mission of this.missions){
              if((mission.description == "" ||mission.name == "" || mission.beginDate == "" || mission.client == "" || mission.type == "")){
                  toShow = true;
                  messageError.style.opacity=1;
                  messageError.style.display="block";
                  messageError.style.color="red";
                  messageError.style.textAlign="center";
                  this.getInfoMission(mission.id);
                  break;
              }
          }
          */
          if(!toShow){
              var printContents = document.getElementById('PDF').innerHTML;
              var originalContents = document.body.innerHTML;
              document.body.innerHTML = printContents;
              window.print();
              document.body.innerHTML = originalContents;
              location.reload();
          }

      }
    },
    notifications: {
      showError: { // You can have any name you want instead of 'showLoginError'
        title: 'La mise à jour a échoué',
        message: 'Impossible de mettre à jour l\'utilisateur',
        type: 'error' // You also can use 'VueNotifications.types.error' instead of 'error'
      },
      showWarning: {
        title: 'Données invalides',
        message: 'Veuillez compléter toutes les données nécessaire',
        type: 'warn'
      },
      showSuccess: {
        title: 'Succès',
        message: 'La mise à jour a été bien enregistrée',
        type: 'success'
      }
    }
  }

</script>

<style>
  .savePDF{
    width: 50%;
    text-align: center;
    border: 1px solid lightskyblue;
    border-radius: 8px;
    margin-right: 2%;
    font-size: 80%;
    padding: 5px;
    cursor: pointer;
  }
  .downloadPDF{
    width: 50%;
    text-align: center;
    border: 1px solid pink;
    border-radius: 8px;
    font-size: 80%;
    padding: 5px;
    cursor: pointer;
  }
  .toPDF{
    display: flex;
    flex-direction: row;
    margin-left: 2%;
    margin-top: 5%;
  }
</style>
