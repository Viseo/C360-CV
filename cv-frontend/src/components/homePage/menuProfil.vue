<template>
  <transition name="fadeMenu">
    <div class="fadeMenu menuProfil" v-show="showMenuProfil" v-on:mouseleave="toggleShowMenuProfil">
      <a class="option" @click="goToMyCV">Espace CV</a>
      <a class="option" @click="goToMyProfile">Modifier mon profil</a>
      <a class="deconnexion" @click="signOut">Déconnexion</a>
    </div>
  </transition>
</template>

<script>
  import inputPassword from './inputThePassword.vue'
  import inputTextMail from './inputTextMail.vue'
  import inputSubmitButton from './inputSubmitButton.vue'
  import inputCheck from './inputCheck.vue'

  export default {
    data: function() {
      return {
        toggledShowMenuProfil: this.showMenuProfil,
      }
    },
    methods: {
      toggleShowMenuProfil: function () {
        this.toggledShowMenuProfil = !this.toggledShowMenuProfil;
        this.$emit('toggledMenuProfil');
      },
      signOut: function(){
          this.$emit('signOut');
      },
      goToMyCV: function () {
        window.location.href = '/mycv';
      },
      goToMyProfile: function(){
          window.location.href = '/myprofile'
      }
    },
    props: ['showMenuProfil']
  }

</script>

<style>
  .menuProfil{
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    padding-left:0.5em;
    width: 10em;
    height: 6em;
    border-radius: 5%;
    border: 1px solid #ccc;
    flex-wrap: wrap;
    box-shadow: 0.1em 0.2em 0.3em rgba(0,0,0,.15);
    background-color: white;
    z-index: 1000;
    position: absolute;
    cursor: pointer;
  }

</style>
