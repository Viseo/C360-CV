<template>
  <div :style="styl.styleGlobal" >
    <form>
      <div v-for="item in listitems" v-on:keyup.enter="checkAndLogin">
        <inputPassword  v-if="item.fa === 'fa fa-lock'"
                        :label="item.label" :placeholder="item.placeholder" :type="item.type"
                        :styl="styl" :fa="item.fa" :form="item.name"
                        v-on:checkInput="check(item.label,item.name)" @showPassword="changeTypePassword(item)">
        </inputPassword>
        <inputTextMail v-else-if="item.type === 'text' || item.type === 'email'"
                       :label="item.label" :placeholder="item.placeholder" :type="item.type" :form="item.name"
                       :styl="styl" :fa="item.fa"
                       v-on:checkInput="check(item.label,item.name)" ></inputTextMail>
        <inputSubmitButton v-else-if="item.type === 'submit' || item.type === 'button'"
                           :value="item.value" :type="item.type" :styl="styl"
                           v-on:checkAndLogin="checkAndLogin">
        </inputSubmitButton>
        <inputCheck v-else
                    :value="item.value" :styl="styl" :type="item.type" :href="item.href" :checkMsg="item.checkMsg" :checked="item.checked">
        </inputCheck>
      </div>
    </form>
  </div>
</template>

<script>
  import inputPassword from './inputThePassword.vue'
  import inputTextMail from './inputTextMail.vue'
  import inputSubmitButton from './inputSubmitButton.vue'
  import inputCheck from './inputCheck.vue'

  export default {
    components:{
      inputPassword:inputPassword,
      inputTextMail:inputTextMail,
      inputSubmitButton:inputSubmitButton,
      inputCheck:inputCheck
    },
    methods: {
      changeTypePassword: function(item) {
        if(item.type==='password'){item.type='text';}
        else{item.type='password'};
      },
      checkAndLogin:function(){
          this.$emit("login");
      },
      check:function(label,name){
          this.$emit("check",label,name);
      }
    },
    data: function () {
      return {
      }
    },
    props: ['listitems', 'styl','inputManager']
  }

</script>

<style>

</style>
