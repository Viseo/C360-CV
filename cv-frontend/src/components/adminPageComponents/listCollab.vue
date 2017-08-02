<template>
  <div id="collab-menu">
    <div class="title"><span>Collaborateurs</span></div>
    <div class="all-collabs">
      <div v-for="(item,index) in collaborators">
        <thumbnail :name="item.name" :firstName="item.firstName" :poste="item.poste"
                   :experience="item.experience" :picture="item.picture"></thumbnail>
      </div>
    </div>
    <div class="zone-download-all">
      <div class="download-all">Télécharger tous les CV</div>
    </div>
  </div>
</template>

<script>
  import thumbnail from "./thumbnail.vue"

  export default{
    components:{
      thumbnail:thumbnail
    },
    methods:{

    },
    data: function () {
      return {

      };
    },
    props:["collaborators"]
  }
</script>

<style>
  #collab-menu{
    float:right;
    display:block;
    height:40em;
    width:75em;
    margin:10px;
  }

  .all-collabs{
    overflow-Y: scroll;
    height:33em;
    width:75em;
    border-left:1.5px solid #D7D7D7;
  }

  .title{
    height: 2em;
    width: auto;
    background-color: #D7D7D7;
    border-top-left-radius: 20px;
    border-top-right-radius: 20px;
    text-align: center;
    line-height: 2em;
  }

  .zone-download-all{
    height: 5em;
    width: 75em;
    border-left:1.5px solid #D7D7D7;
    border-right:1.5px solid #D7D7D7;
    border-bottom:1.5px solid #D7D7D7;
    border-bottom-left-radius: 25px;
    border-bottom-right-radius: 25px;
    line-height: 5em;
    text-align: center;
  }

  .download-all{
    width:10em;
    height:2em;
    line-height:2em;
    display:inline-block;
    border:1.5px solid pink;
  }
</style>
