<template>
  <div>
    <banner></banner>
    <div style="margin-top: 2%">
      <div>
        <user-info></user-info>
      </div>
      <div style="margin-top: 1em">
        <id-user></id-user>
      </div>
      <div style="height: 100px; width: 600px; margin-left: auto; margin-right: auto; margin-top: 10px">
        <button style="margin-left: 100px">Enregistrer</button>
        <button style="margin-left: 100px">Annuler</button>
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
