<template>
  <div class="thumbnail">
    <img :src="picture" class="pictureCollab">
    <div class="pitchCollab">
      <div class="name">{{ firstName+' '+lastName }}</div>
      <div class="other">
        <div>{{"Consultant "+ position}}</div>
        <div>{{ experience+" ans d'expérience"}}</div>
      </div>
    </div>
    <div class="verticaline"></div>
    <div class="actionsCV">
      <div class="preview">
        <i class="fa fa-eye fa-2x pictoThumb" @click="preparePDF('show')"></i>
        <span>Aperçu</span>
      </div>
      <div class="preview">
        <i class="fa fa-download fa-2x pictoThumb" @click="preparePDF('download')"></i>
        <span>Télécharger</span>
      </div>
    </div>
  </div>
</template>

<script>
//  import PDFDocument from '../../../pdfkit.js'
//  import blobStream from '../../../blob-stream.js'
  export default{
    methods:{
      preparePDF(action){
          this.infoUser.firstName=this.firstName;
          this.infoUser.lastName=this.lastName;
          this.infoUser.experience=this.experience;
          this.infoUser.birth=this.birth;
          this.infoUser.languages=this.languages;
          this.infoUser.position=this.position;
          this.infoUser.mail=this.mail;
          this.infoUser.hobbies=this.hobbies;
          this.infoUser.telephone=this.telephone;
          this.infoUser.picture=this.picture;
          this.infoUser.missions=this.missions;
          action=='download'?this.downloadPDF():this.$emit('showPDF', this.infoUser);
      },
      prepareHeader(){
          for(let field of this.infoUser){

          }
      },
      downloadPDF(){
        this.$emit('downloadPDF', this.infoUser);
        setTimeout(function () {
          var printContents = document.getElementById('PDF').innerHTML;
          var originalContents = document.body.innerHTML;
          document.body.innerHTML = printContents;
          window.print();
          document.body.innerHTML = originalContents;
          location.reload();
        },300)

      }
    },
    data: function () {
      return {
          infoUser: {
              firstName:'',
              lastName: '',
              experience: '',
              birth: '',
              languages: '',
              position: '',
              mail: '',
              hobbies: '',
              telephone: '',
              picture: '',
              missions: {}
          },
      };
    },
    props:[
      'lastName',"firstName","experience","birth","position","picture","languages","mail","hobbies","missions","telephone"
    ]
  }
</script>

<style>
  .thumbnail{
    display: flex;
    flex-direction: row;
    width: 18em;
    height: 5em;
    background-color: white;
    margin: 1em
  }

  .pictureCollab{
    display: flex;
    width: 25%;
  }

  .pitchCollab{
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: flex-start;
    padding-left: 0.5em;
    width: 10em;
    color: dimgrey;
    flex-basis: 50%;
  }

  .actionsCV{
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    flex-basis: 25%;
    margin: 0 0.5em;
  }

  .verticaline{
    display: flex;
    align-self: center;
    border-color: dimgrey;
    border-left: 2px solid dodgerblue;
    height: 80%;
    margin-left: 0.5em;
  }

  .name{
    font-size: 15px;
    font-weight: bold;
  }

  .other{
    font-size: 11px;
    color: dodgerblue;
    font-weight: bold;
  }

  .preview{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    font-size: 12px;
  }

  .pictoThumb{
    cursor: pointer;
  }

  /*.collaborator-picture{*/
    /*float:left;*/
    /*height:4em;*/
    /*width:4em;*/
    /*border-radius: 4em;*/
    /*margin-top:0.5em;*/
  /*}*/

  /*.collaborator-description {*/
    /*float: left;*/
    /*margin-left:1em;*/
  /*}*/

  /*.thumbnail-style{*/
    /*float: left;*/
    /*display:block;*/
    /*width:19em;*/
    /*height:5em;*/
    /*border:1px solid lightgray;*/
    /*border-radius: 1em;*/
    /*margin-left:2em;*/
    /*margin-right:2em;*/
    /*margin-top:2em;*/
    /*padding:0.5em;*/
  /*}*/

  /*.cv-zone{*/
    /*display:flex;*/
    /*flex-direction: column;*/
    /*text-align: center;*/
    /*border-left:1px solid black;*/
    /*float:right;*/
    /*padding-right:0.2em;*/
    /*padding-left:0.3em;*/
    /*padding-bottom:0.5em;*/
  /*}*/

  /*.icon-cv{*/
    /*display:block;*/
  /*}*/
</style>
