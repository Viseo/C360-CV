<template>
  <div :style="styl.divForm">
    <input :value="value" type="button" :style="styl.styleSubmit" v-on:click="checkAndLogin">
  </div>
</template>

<script>
  export default {
    props: ['value','styl'],
    methods: {
        checkAndLogin:function(){
          this.$emit("checkAndLogin");
        }
    }
  }
</script>

<style>

</style>
