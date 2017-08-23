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

  export default {
    components: {
      dropDownMenu: dropdownMenu
    },
    data: function () {
      return {
        showMenu: false,
        styleBanner: {
          display:'flex',
          'flex-orientation': 'row',
          'justify-content': 'space-between',
          'align-items': 'center',
//          'background-color' : 'rgb(255,204,19)',
          'background-color' : 'rgb(255,255,255)',
          'margin-bottom':'1%',
          'font-family': 'Arial',
        },
        styleBannerText:{
          'padding-top':"1%",
          'padding-left':"1%",
          'padding-bottom':"1%",
          'color' : 'rgb(0,10,255)',
        },
        styleBannerIcon:{
          'text-align':'right',
          'padding-top':"1%",
          'padding-right':"2%",
          'padding-bottom':"1%",
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
      signOut:function(){
        this.$session.destroy();
        window.location.href = '/';
      }
    }
  }

</script>

<style>
  .GestionCV{
    margin-right: 4em;
  }
  .user{
    height:2em;
    margin-right: 1em;

  }
  .Roland{
    margin-right: 5em;
  }

</style>
