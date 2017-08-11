<template>
  <div>
    <div class="banner" v-bind:style="styleBanner">
      <div  class="bannerText" v-bind:style="styleBannerText">Collaborateur 360</div>
      <div class="menus">
        <div>
          <div @mouseover="toggleShowMenuProfil"> Choisissez une option </div>
          <menuProfil :showMenuProfil="showMenuProfil"  @toggledMenuProfil="toggleShowMenuProfil" @signOut="signOut"></menuProfil>
        </div>
        <div v-on:mouseover="toggleShowMenu"  class="bannerIcon">
          <i class="fa fa-th" v-bind:style="styleColorIcon"></i>
        </div>
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
        },
        styleBannerIcon:{
        },
        styleColorIcon:{
          color:'rgb(255,146,0)',
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
