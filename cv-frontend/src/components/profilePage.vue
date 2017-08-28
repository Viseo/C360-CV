<template>
  <div>
    <banner></banner>
    <div class="mainContainer">
      <div class="containerProfile">
        <user-info></user-info>
        <id-user></id-user>
        <div class="buttonsProfile">
          <div class="buttons">Enregistrer</div>
          <div class="buttons">Annuler</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import userDetails from './profilePageComponents/userDetails.vue';
  import banner from './banner.vue';
  import identifiant from './profilePageComponents/userId.vue'
  export default {

    components: {
      userInfo: userDetails,
      banner: banner,
      idUser: identifiant
    },
    data: function () {
      return{

      }
    }
  }

</script>

<style>
  .mainContainer{
    display: flex;
    justify-content: center;
  }
  .containerProfile{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .buttonsProfile{
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    width: 30em;
  }
  .buttons{
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #E67E22;
    width: 13em;
    height: 1.8em;
    border-radius: 3px;
    color:white;
  }
</style>
