<template>
  <div>
    <div style="opacity: 1; z-index:9999999">
      <div class="header">
        <div class="contentHeader">
          <div class="colonnesHeader">
            <div class="bannerCV"><p>{{infoUser.firstName}} {{infoUser.lastName}}</p></div>
            <div class="photo">
              <div><img :src="'../../static/'+infoUser.picture" class="photoProfil"></div>
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
              <p>{{infoUser.age}} ans</p>
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

    }
  }
</script>

<style>

  /*.header{*/
      /*display: flex;*/
      /*flex-direction: column;*/
      /*width: 900px;*/
  /*}*/
  /**/
  /*.bannerCV p{*/
    /*margin-left: 0.5em;*/
    /*margin-top: 0.5em;*/
    /*margin-bottom: 0.5em;*/
  /*}*/

  /*.contentHeader{*/
    /*display: flex;*/
    /*flex-direction: row;*/
    /*width: auto;*/
    /*background-color: white;*/
  /*}*/

  /*.colonnesHeader{*/
    /*display: flex;*/
    /*flex-direction: column;*/
    /*flex-grow: 3;*/
    /*flex-basis: 37.5%;*/
    /*align-items: flex-start;*/
    /*justify-content: space-between;*/
  /*}*/
  /*.photo{*/
    /*display: flex;*/
    /*justify-content: center;*/
    /*align-items: center;*/
    /*flex-grow: 2;*/
    /*flex-basis: 25%;*/
  /*}*/
  /*.photoProfil{*/
    /*height : 9em;*/
    /*width: 8em;*/
    /*display: block;*/
    /*border-radius: 50%;*/
  /*}*/

  /*.userInfo{*/
    /*display: flex;*/
    /*flex-direction: row;*/
    /*align-items: center;*/
    /*height: 3.5em;*/
    /*width: auto;*/
  /*}*/
  /*.userInfo p{*/
    /*margin-left: 1em;*/
  /*}*/

  /*$icons: (*/
      /*briefcase: "../static/png/briefcase",*/
      /*phone: "../static/png/phone",*/
      /*globe: "../static/png/globe",*/
      /*cake: "../static/png/cake",*/
      /*enveloppe: "../static/png/enveloppe",*/
      /*gamePad: "../static/png/gamePad",*/
  /*);*/

  /*@mixin setIcon($icon) {*/
      /*content: url("../static/png/briefcase"+#{$icon});*/
  /*}*/
  /*.briefcase:before*/
  /*{*/
    /*@include setIcon('briefcase');*/
  /*}*/

  /*.briefcase{*/
    /*display: block;*/
    /*content: "";*/
    /*width: 3px;*/
    /*height: 5px;*/
    /*background: transparent url('./static/png/briefcase') no-repeat;*/
  /*}*/
  /**/
  /*.briefcas{*/
    /*width: 1.5em;*/
    /*height: 1.5em;*/
    /*content: url("../../../static/png/briefcase.png");*/
  /*}*/
  /*.gamePad{*/
    /*width: 1.5em;*/
    /*height: 1.5em;*/
    /*content: url("../../../static/png/gamePad.png");*/
  /*}*/
  /*.phone{*/
    /*width: 1.5em;*/
    /*height: 1.5em;*/
    /*content: url("../../../static/png/phone.png");*/
  /*}*/
  /*.globe{*/
    /*width: 1.5em;*/
    /*height: 1.5em;*/
    /*content: url("../../../static/png/net.png");*/
  /*}*/
  /*.enveloppe{*/
    /*width: 1.5em;*/
    /*height: 1.5em;*/
    /*content: url("../../../static/png/enveloppe.png");*/
  /*}*/
  /*.cake{*/
    /*width: 1.5em;*/
    /*height: 1.5em;*/
    /*content: url("../../../static/png/cake.png");*/
  /*}*/


  /*.fa-globe{*/
    /*color: red;*/
  /*}*/
</style>
