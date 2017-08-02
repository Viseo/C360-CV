<template>
  <span class="thumbnail-style">
    <img v-bind:src="picture" class="collaborator-picture">
    <div class="collaborator-description">
      <div>{{ firstName }}</div>
      <div>{{ name }}</div>
      <div style="color:hotpink">Consultant {{ poste }}</div>
      <div style="color:deepskyblue">{{ experience }} ans d'expérience</div>
    </div>
    <div class="cv-zone">
      <div class="fa fa-binoculars icon-cv"></div>
      <div>Aperçu</div>
      <div class="fa fa-download icon-cv" style="margin-top:0.4em"></div>
      <div>Télécharger</div>
    </div>
  </span>
</template>

<script>

  export default{
    methods:{

    },
    data: function () {
      return {

      };
    },
    props:[
      'name',"firstName","poste","experience","picture"
    ]
  }
</script>

<style>
  .collaborator-picture{
    float:left;
    height:4em;
    width:4em;
    border-radius: 4em;
    margin-top:0.5em;
  }

  .collaborator-description {
    float: left;
    margin-left:1em;
  }

  .thumbnail-style{
    float: left;
    display:block;
    width:19em;
    height:5em;
    border:1px solid lightgray;
    border-radius: 1em;
    margin-left:2em;
    margin-right:2em;
    margin-top:2em;
    padding:0.5em;
  }

  .cv-zone{
    display:flex;
    flex-direction: column;
    text-align: center;
    border-left:1px solid black;
    float:right;
    padding-right:0.2em;
    padding-left:0.3em;
    padding-bottom:0.5em;
  }

  .icon-cv{
    display:block;
  }
</style>
