<template>
  <div>
    <label v-if="label" :style="styl.styleLabel">{{label}}</label>
    <div :style="styl.divForm">
      <span v-if="fa"><i :class="fa" :style="styl.stylePicto"></i></span>
      <input :type="type" :id="label+''+form" :placeholder="placeholder" :style="styl.styleForm" v-on:input="checkInput(label)">
      <span v-if="type!='submit'" :id="'message' + label + form" :style="styl.styleMessage"></span>
    </div>
  </div>
</template>

<script>
  export default {
    props: ['type','label','placeholder','styl','fa','form'],
    methods:{
      checkInput : function(){
        this.$emit("checkInput");
      }
    }
  }
</script>

<style>

</style>
