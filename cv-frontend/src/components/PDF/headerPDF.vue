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
              <p>{{infoUser.position}},</p>
              <p style="margin-left:4px;" v-if="infoUser.experience>1">{{infoUser.experience}} ans d'expérience</p>
              <p style="margin-left:4px;" v-if="infoUser.experience==1">{{infoUser.experience}} an d'expérience</p>
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
              <span class="netIcon" style="padding-right: 10px;"></span>
              <span v-for="(language,i) in infoUser.languages">{{language.label}}<span v-if="i != infoUser.languages.length-1">,</span>
              </span>
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
        infoUser: this.$store.state.userLogged
      }
    },
    methods: {
      computeAge: function () {
        try{
          var dateString  = this.infoUser.birth_date; //yyyy-mm-dd
          var year        = dateString.substring(0,4);
          var month       = dateString.substring(5,7);
          var day         = dateString.substring(8,10);
          var birthday    = new Date(year, month - 1, day);
          return Math.floor((new Date().getTime()-birthday)/(1000*3600*24*365));
        }
        catch(err){
          return null;
        }

      }
    }
  }
</script>

<style>

</style>
