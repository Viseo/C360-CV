<template>
  <transition :name="classes.classFade">
    <div :style="style.styleMenu" :class="classes.classFade" v-show="showMenu" v-on:mouseleave="toggleShowMenu">
      <div :style="style.styleDivImg">
        <img :style="style.styleImg" src="../../../static/png/GCV.png">
        <p :style="style.styleText">GCV</p>
      </div>
      <div :style="style.styleDivImg">
        <img :style="style.styleImg" src="../../../static/png/GCon.png">
        <p :style="style.styleText">GCom</p>
      </div>
      <div :style="style.styleDivImg">
        <img :style="style.styleImg" src="../../../static/png/GForm.png">
        <p :style="style.styleText">GF</p>
      </div>
      <div :style="style.styleDivImg">
        <img :style="style.styleImg" src="../../../static/png/GM.png">
        <p :style="style.styleText">GM</p>
      </div>
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
        toggledShowMenu: this.showMenu,
        classes: {
          classFade: 'fadeMenu'
        },
        style:
          {
            styleMenu: {
              display: 'flex',
              position: 'absolute',
              right: '1em',
              top: '2em',
              'flex-direction': 'row',
              width: '125px',
              height: 'auto',
              'border-radius': '5%',
              'border': '1px solid #ccc',
              'flex-wrap': 'wrap',
              'box-shadow': '0.1em 0.2em 0.3em rgba(0,0,0,.15)',
              'background-color': 'white',
              'z-index': '1000'
            },
            styleDivImg: {
              display: 'flex',
              'flex-direction': 'column',
              'align-items': 'center',
              width: '40px',
              height: '50px',
              'flex-grow': '1',
              'margin': '8px',
              cursor: 'pointer'

    },
            styleImg: {
              display: 'flex',
              width: '2em',
              height: '2em',
              'border-radius': '50%',
            },
            styleText: {
              'font-size': '12px',
              'font-family': 'Calibri',
              margin: '0'
            }
          }
      }
    },
    methods: {
      toggleShowMenu: function () {
        this.toggledShowMenu = !this.toggledShowMenu;
        this.$emit('toggledMenu');
      },
    },
    props: ['showMenu']
  }

</script>

<style>

</style>
