<template>

  <div>
    <div class="toPDF">
      <div class="savePDF" @click="updateUserProfileOnly">Sauvegarder</div>
      <div class="downloadPDF" @click="printPDF">Télécharger le PDF</div>
    </div>
    <div style="text-align: center;">
      <span id="toValidate" style="color:red;" v-show="saveSuccessfully==0">
        Veuillez remplir correctement le formulaire</span>
      <span style="color:green;" v-show="saveSuccessfully==1">Enregistrement avec succès!</span>
    </div>
  </div>

</template>

<script>


  export default {
    props:['infoUser'],
    data:function(){
      return{
        saveSuccessfully:undefined
      }
    },
    methods:{
      updateUserProfileOnly:function(){
        console.log(this.infoUser);
        if(this.infoUser.mail == "" || this.infoUser.firstName == "" || this.infoUser.lastName == ""){
          this.saveSuccessfully=0;
        }
        else{
          this.saveSuccessfully=1;
        }
//        axios.post(config.server + '/api/updateUser', user)
//          .then((response)=>{
//            console.log(response);
//          })
//          .catch((error)=> {
//            console.log(error);
//          });
      },
      printPDF: function () {
          let toShow = false;
          let messageError = document.getElementById("toValidate");
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
              messageError.style.display = "none";
              var printContents = document.getElementById('PDF').innerHTML;
              var originalContents = document.body.innerHTML;
              document.body.innerHTML = printContents;
              window.print();
              document.body.innerHTML = originalContents;
              location.reload();
          }

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
