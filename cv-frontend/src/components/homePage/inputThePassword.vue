<template>
  <div>
    <label v-if="label" :style="styl.styleLabel">{{label}}</label>
    <div :style="styl.divForm">
      <span v-if="fa"><i :class="fa" :style="styl.stylePicto"></i></span>
      <input v-if="type === 'password' && label === 'Mot de passe'" v-model="password"
             type="password" :id="label+''+form" :placeholder="placeholder" :style="styl.styleForm"
             v-on:keyup="checkInput(label)">

      <input v-else-if="type === 'password' && label=='Confirmation du mot de passe'" v-model="password"
             type="password" :id="label+''+form" :placeholder="placeholder" :style="styl.styleForm"
             v-on:keyup="checkInput(label)">

      <input v-else-if="type === 'text' && label=='Mot de passe'" v-model="password"
             type="text" :id="label+''+form" :placeholder="placeholder" :style="styl.styleForm"
             v-on:keyup="checkInput(label)">

      <input v-else-if="type === 'text' && label=='Confirmation du mot de passe'" v-model="password"
             type="text" :id="label+''+form" :placeholder="placeholder" :style="styl.styleForm"
             v-on:keyup="checkInput(label)">

      <span :class="eye" :style="styl.styleEye" v-on:click="toggleEye"></span>
      <span v-if="type!='submit'" :id="'message' + label + form" :style="styl.styleMessage"></span>
    </div>
  </div>
</template>

<script>
  export default {
    props: ['type','label','placeholder','styl','fa','form'],
    data: function() {
      return {
        eye: 'fa fa-eye',
        password: ''
      }
    },
    methods:{
      toggleEye: function () {
        if(this.eye==='fa fa-eye'){this.eye='fa fa-eye-slash';this.$emit('showPassword')}
        else{this.eye='fa fa-eye';this.$emit('showPassword')};
      },
      checkInput : function(){
        this.$emit("checkInput");
      }
    }
  }
</script>

<style>

</style>
