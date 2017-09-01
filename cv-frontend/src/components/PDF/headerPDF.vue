<template>
  <div>
    <div style="opacity: 1; z-index:9999999">
      <div class="header">
        <div class="contentHeader">
          <div class="colonnesHeader">
            <div class="bannerCV"><p>{{infoUser.firstName}} {{infoUser.lastName}}</p></div>
            <div class="photo">
              <div><img :src="infoUser.picture" class="photoProfil"></div>
            </div>
          </div>
          <div class="colonnesHeader1">
            <div class="userInfo">
              <span class="luggageIcon"></span>
              <p>{{infoUser.position}}, {{infoUser.experience}} d'expérience</p>
            </div>
            <div class="userInfo">
              <span class="enveloppe"></span>
              <p>{{infoUser.mail}}</p>
            </div>
            <div class="userInfo">
              <span class="phone"></span>
              <p>{{infoUser.telephone}}</p>
            </div>
          </div>
          <div class="colonnesHeader2">
            <div class="userInfo">
              <span class="cake"></span>
              <p>{{computeAge()}} ans</p>
            </div>
            <div class="userInfo">
              <span class="netIcon"></span>
              <p>{{infoUser.languages}}</p>
            </div>
            <div class="userInfo">
              <span class="gamePad"></span>
              <p>{{infoUser.hobbies}}</p>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

</template>

<script>
  export default {
    data: function () {
      return {
      }
    },
    props: ['infoUser'],
    methods: {
      computeAge: function () {
        return Math.floor((new Date().getTime()-this.infoUser.birth)/(1000*3600*24*365));
      }
    }
  }
</script>

<style>

</style>
