<template>
  <div id="collab-menu">
    <div class="titleCollabMenu"><span>Collaborateurs</span></div>
    <div class="zone-download-all">
      <div class="download-all">Télécharger tous les CV</div>
      <i class="fa fa-download pictoThumb"></i>
    </div>
    <div class="containerCollab">
      <div class="all-collabs">
        <div v-for="(item,index) in collaborators">
          <thumbnail :lastName="item.lastName" :firstName="item.firstName" :position="item.position"
                     :mail="item.mail" :telephone="item.telephone" :birth="item.date_birth"
                     :hobbies="item.hobbies" :languages="item.languages" :missions="item.missions"
                     :experience="item.experience" :picture="item.picture" @showPDF="showPDF" @downloadPDF="downloadPDF"></thumbnail>
        </div>
      </div>
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
      showPDF(infoUser){
        this.$emit('showPDF', infoUser);
      },
      downloadPDF(infoUser){
        this.$emit('downloadPDF', infoUser);
      }
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
    display: flex;
    flex-direction: column;
    font-family: Arial, serif;
    background-color: #F2F3F4;

    /*float:right;*/
    /*display:block;*/
    /*height:40em;*/
    /*width:75em;*/
    /*margin:10px;*/
  }

  .containerCollab{
    height:33.5em;
    padding-left: 4em;
    overflow-Y: scroll;

  }

  .all-collabs{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: flex-start;
  }

  .titleCollabMenu{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 2.35em;
    background-color: #3498DB;
    color: white;
  }

  .zone-download-all{
    display: flex;
    justify-content: flex-end;
    align-items: center;
    height: 2em;
    padding:1em 7em 0 0;
  }
  .download-all{
    display: flex;
    align-items: center;
    padding-right: 1em ;
    font-size: 13px;
    font-family: Calibri, serif;
    font-weight: bold;
    cursor: pointer;
  }

</style>
