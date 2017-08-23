<template>

  <div>
    <div class="toPDF">
      <div class="savePDF" @click="saveButton">Sauvegarder</div>
      <div class="downloadPDF" @click="printPDF">Télécharger le PDF</div>
    </div>
    <span id="toValidate" style="opacity: 0; font-size: 10px; display: none">Veuillez remplir correctement le formulaire</span>
  </div>

</template>

<script>


  export default {
    methods:{
      saveButton: function () {
        this.$emit("saveData");
      },
      getInfoMission: function (id) {
        this.$emit('getInfoMission',id);
      },
      printPDF: function () {
          let toShow = false;
          let messageError = document.getElementById("toValidate");
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
    },
    props:['missions','infoUser']
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
    background-color: #00ffff;
  }
  .downloadPDF{
    width: 50%;
    text-align: center;
    border: 1px solid pink;
    border-radius: 8px;
    font-size: 80%;
    padding: 5px;
    background-color: #ffd3e0;

  }
  .toPDF{
    display: flex;
    flex-direction: row;
    margin-left: 2%;
    margin-top: 5%;
  }
</style>
