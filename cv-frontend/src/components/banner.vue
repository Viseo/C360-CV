<template>
  <div>
    <div class="banner" v-bind:style="styleBanner">
      <div  class="bannerText" v-bind:style="styleBannerText" v-on:click="signOut">Collaborateur 360</div>
      <div v-on:mouseover="toggleShowMenu"  class="bannerIcon" v-bind:style="styleBannerIcon">
        <span class="GestionCV">Gestion des CV</span><img class="user" src="../../static/png/user.png"/><span class="Roland">Roland Houssou</span>
        <i class="fa fa-th" v-bind:style="styleColorIcon"></i>
      </div>

    </div>
    <dropDownMenu :showMenu="showMenu" @toggledMenu="toggleShowMenu"></dropDownMenu>
  </div>
</template>

<script>
  import dropdownMenu from './homePage/dropdownMenu.vue'
  import menuProfil from './homePage/menuProfil.vue'

  export default {
    components: {
      dropDownMenu: dropdownMenu,
      menuProfil: menuProfil,
    },
    data: function () {
      return {
        showMenu: false,
        showMenuProfil: false,
        styleBanner: {
          display:'flex',
          'flex-orientation': 'row',
          'justify-content': 'space-between',
          'align-items': 'center',
          'background-color' : 'rgb(255,255,255)',
          'margin-bottom':'1%',
          'font-family': 'Arial',
          'color' : 'rgb(255,146,0)',
        },
        styleBannerText:{
          'padding-top':"1%",
          'padding-left':"1%",
          'padding-bottom':"1%",
          'color' : 'rgb(0,10,255)',
        },
        styleBannerIcon:{
        },
        styleColorIcon:{
          color:'rgb(0,10,255)',
          position: 'relative',
          right: '40px',
        },
      }
    },
    methods:{
      toggleShowMenu: function() {
        this.showMenu=!this.showMenu;
      },
      toggleShowMenuProfil: function() {
        this.showMenuProfil=!this.showMenuProfil;
      },
      signOut:function(){
        this.$session.destroy();
        window.location.href = '/';
      }
    }
  }

</script>

<style>
  .menus{
    display: flex;
    flex-direction: row;
    align-items: center;
    padding-right: 5%;
  }

  .bannerIcon{
    margin-left: 3em;
  }
</style>
