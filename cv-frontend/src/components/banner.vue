<template>
  <div>
    <div class="banner" v-bind:style="styleBanner">
      <div  class="bannerText" v-bind:style="styleBannerText">Collaborateur 360</div>
      <div class="menus">
        <div>{{page}}</div>
        <div>
          <div @click="toggleShowMenuProfil" style="cursor: pointer;"> {{userName}} </div>
          <menuProfil :showMenuProfil="showMenuProfil"  @toggledMenuProfil="toggleShowMenuProfil" @signOut="signOut"></menuProfil>
        </div>
        <div @click="toggleShowMenu"  class="bannerIcon">
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
  import axios from 'axios'
  import config from '../config/config'

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
          cursor: 'pointer'
        }
      }
    },

    computed: {
      userName: function(){
        return this.$store.state.userLogged.firstName + ' ' +  this.$store.state.userLogged.lastName;
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
        localStorage.removeItem("token");
        this.$store.commit('resetStore');
        this.$router.push('/');
      }
    },
    beforeMount:function(){
      var d = window.location.href.indexOf("?user=");
      if(d != -1){
        var token = window.location.href.slice(d+6,window.location.href.length);
        if(token != null && token!= 'undefined'){
          axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
          console.log(token);
          axios.post(config.server + "/api/getuserifalreadyconnectedelsewhere",token).then((response) => {
            this.$store.commit('resetStore');
            localStorage.setItem('token',response.data);
            this.$store.commit('setToken', response.data);
            if(this.$store.state.userLogged.admin){
              this.$router.push('/admincv');
            }
            else{
              this.$router.push('/mycv');
            }
          }, response => {
            console.log(response);
          });
        }
      }
      if (localStorage.getItem('token') != null){
        this.$store.commit('setToken', localStorage.getItem('token'));
        this.$store.dispatch('checkIfTokenValide');
      }
      else{
        this.$store.commit('resetStore');
        this.$router.push('/');
      }

    },
    props:['page']
  }

</script>

<style>
  .menus{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    padding-right: 5%;
    width: 30%;
  }

  .bannerIcon{
    margin-left: 3em;
  }
</style>
