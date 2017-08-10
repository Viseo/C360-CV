<template>
  <div class="thumbnail">
    <img :src="picture" class="pictureCollab">
    <div class="pitchCollab">
      <div class="name">{{ firstName+' '+name }}</div>
      <div class="other">
        <div>{{"Consultant "+ fonction}}</div>
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
  <!--<span class="thumbnail-style">-->
    <!--<img v-bind:src="picture" class="collaborator-picture">-->
    <!--<div class="collaborator-description">-->
      <!--<div>{{ firstName }}</div>-->
      <!--<div>{{ name }}</div>-->
      <!--<div style="color:hotpink">Consultant {{ fonction }}</div>-->
      <!--<div style="color:deepskyblue">{{ experience }} ans d'expérience</div>-->
    <!--</div>-->
    <!--<div class="cv-zone">-->
      <!--<div class="fa fa-binoculars icon-cv" @click="preparePDF('show')"></div>-->
      <!--<div>Aperçu</div>-->
      <!--<div class="fa fa-download icon-cv" style="margin-top:0.4em" @click="preparePDF('download')"></div>-->
      <!--<div>Télécharger</div>-->
    <!--</div>-->
  </span>
</template>

<script>
//  import PDFDocument from '../../../pdfkit.js'
//  import blobStream from '../../../blob-stream.js'
  export default{
    methods:{
      preparePDF(action){
          this.infoUser.firstName=this.firstName;
          this.infoUser.name=this.name;
          this.infoUser.experience=this.experience;
          this.infoUser.birth=this.birth;
          this.infoUser.languages=this.languages;
          this.infoUser.fonction=this.fonction;
          this.infoUser.email=this.email;
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
        // create a document and pipe to a blob
//        PDFDocument = require('pdfkit')
//
//        var doc = new PDFDocument();
//        var stream = doc.pipe(blobStream());
//
//// draw some text
//        doc.margin
//        doc.fontSize(25)
//          .text('Here is some vector graphics...', 100, 80);
//
//// some vector graphics
//        doc.save()
//          .moveTo(100, 150)
//          .lineTo(100, 250)
//          .lineTo(200, 250)
//          .fill("#FF3300");
//
//        doc.circle(280, 200, 50)
//          .fill("#6600FF");
//
//// an SVG path
//        doc.scale(0.6)
//          .translate(470, 130)
//          .path('M 250,75 L 323,301 131,161 369,161 177,301 z')
//          .fill('red', 'even-odd')
//          .restore();
//
//// and some justified text wrapped into columns
//        doc.text('And here is some wrapped text...', 100, 300)
//          .font('Times-Roman', 13)
//          .moveDown()
//          .text('hola, que tal', {
//            width: 412,
//            align: 'justify',
//            indent: 30,
//            columns: 2,
//            height: 300,
//            ellipsis: true
//          });
//        doc.addPage();
//        doc.text('And here is some wrapped text...')
//          .font('Times-Roman', 13)
//          .moveDown()
//          .text('coucou comment ca va', {
//            width: 412,
//            align: 'justify',
//            indent: 30,
//            columns: 3,
//            height: 300,
//            ellipsis: true
//          });
//
//        doc.text('I understood how it works').moveDown(5);
//        doc.text('I understood how it works').moveDown();
//
//
//// end and display the document in the iframe to the right
//        doc.end();
//        stream.on('finish', function() {
//          let blob = stream.toBlob('application/pdf');
//          var url = window.URL.createObjectURL(blob);
//          var p = document.createElement("a");
//          document.body.appendChild(p);
//          p.setAttribute("href", url);
//          p.setAttribute("download", 'test');
//// 	$("body").append(a);
//          console.log(p)
//          p.click();
//          window.URL.revokeObjectURL(url);
//          p.remove();
//        });
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
              name: '',
              experience: '',
              birth: '',
              languages: '',
              fonction: '',
              email: '',
              hobbies: '',
              telephone: '',
              picture: '',
              missions: {}
          },
      };
    },
    props:[
      'name',"firstName","experience","birth","fonction","picture","languages","email","hobbies","missions","telephone"
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

  /*.pictoThumb{*/
    /*font-size: ;*/
  /*}*/

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
