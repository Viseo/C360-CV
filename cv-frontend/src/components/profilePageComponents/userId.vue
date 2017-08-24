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
            <input type="password" class="inputProfile">
            <i class="fa fa-eye pictoEye"></i>
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
            <input type="password" class="inputProfile">
            <i class="fa fa-eye pictoEye"></i>
          </div>
        </div>
      </div>
      <div class="details">
        <div class="divInputProfile">
          <label class="labelProfile">Confirmation mot de passe</label>
          <div>
            <i class="fa fa-lock pictoProf"></i>
            <input type="password" class="inputProfile">
            <i class="fa fa-eye pictoEye"></i>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default{

  }
</script>

<style>
  .pictoEye{
    position: relative;
    left:-1.5em;
    top: .3em;
  }

</style>
