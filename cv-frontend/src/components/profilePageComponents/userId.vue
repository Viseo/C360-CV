<template>
  <div class="userProfile">
    <div class="bannerProfile">
      2. Mes identifiants
    </div>
    <div class="contentDetails">
      <div class="details">
        <div class="divInputProfile">
          <label class="labelProfile">Ancien mot de passe</label>
          <div>
            <i class="fa fa-lock pictoProf"></i>
            <input v-if="typePass1=='password'" type="password" v-model="oldPass" class="inputProfile">
            <input v-else type="text" v-model="oldPass" class="inputProfile">
            <i class="fa fa-eye pictoEye" @click="typePass1=toggleType(typePass1)"></i>
          </div>
        </div>
        <div class="divInputProfile">
          <label class="labelProfile">Email</label>
          <div>
            <i class="fa fa-envelope pictoProf"></i>
            <input type="email" class="inputProfile">
          </div>
        </div>

      </div>
      <div class="details">
        <div class="divInputProfile">
          <label class="labelProfile">Nouveau mot de passe</label>
          <div>
            <i class="fa fa-lock pictoProf"></i>
            <input v-if="typePass2=='password'" type="password" v-model="newPass" class="inputProfile">
            <input v-else type="text" v-model="newPass" class="inputProfile">
            <i class="fa fa-eye pictoEye" @click="typePass2=toggleType(typePass2)"></i>
          </div>
        </div>
      </div>
      <div class="details">
        <div class="divInputProfile">
          <label class="labelProfile">Confirmation mot de passe</label>
          <div>
            <i class="fa fa-lock pictoProf"></i>
            <input v-if="typePass3=='password'" type="password" v-model="confPass" class="inputProfile">
            <input v-else type="text" v-model="confPass" class="inputProfile">
            <i class="fa fa-eye pictoEye" @click="typePass3=toggleType(typePass3)"></i>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default{
      data:function(){
          return{
              typePass1: 'password',
              typePass2: 'password',
              typePass3: 'password',
              oldPass: '',
              newPass: '',
              confPass: ''
          }
      },
      methods:{
            toggleType(old){
                console.log(this.typePass1,this.typePass2,this.typePass3)
                if(old=='password')return 'text';
                else return 'password';
            }
      }

  }
</script>

<style>
  .pictoEye{
    position: relative;
    left:-1.5em;
    top: .3em;
  }

</style>
