<template>
  <div :style="styl.styleConfig">
    <div><input :type="type"  :value="value" :style="styl.styleCheck" :checked="checked">{{checkMsg}}</div>
    <a :href="href">Mot de passe oublié</a>
  </div>
</template>

<script>
  export default {
    props: ['value','type','styl','href','checkMsg','checked']
  }
</script>

<style>

</style>
