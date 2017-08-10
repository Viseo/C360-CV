<template>
  <div style="overflow-y: scroll">
    <div class="logoDiv">
      <img class="viseoLogo" src="../../../static/logoviseo.png" >
    </div>
    <div class="line"></div>
    <headerPDF :infoUser="infoPerso"></headerPDF>
    <div class="titles">MISSIONS - PROJETS</div>
    <div v-for="mission in infoPerso.missions">
      <missionsPDF :infoMission="mission"></missionsPDF>
    </div>
  </div>
</template>

<script>
  import headerPDF from './headerPDF.vue'
  import missionsPDF from './missionsPDF.vue'
  export default {
      components: {
          headerPDF: headerPDF,
          missionsPDF: missionsPDF,
      },
      data: function () {
          return {
          }
      },
    props: ['infoPerso','infoMission'],
    methods: {
      closePDF: function(){
        this.$emit('closePDF');
      }
    }
  }
</script>

<style>

</style>
