<template>
  <div class="containerForm">
    <div class="containerColumn">
      <div class="divForm">
        <label v-bind:class="labelTitleClass">Titre de la mission</label>
        <input id="Title Mission" v-model="nameMission" type='text' class="inputText" @focus="setFocusLabelClass(1)"
               @keyup="updateBlock(currentBlock)" @blur="changeLabelClass(nameMission,1)">
        <i class="fa fa-suitcase picto"></i>
      </div>

      <div id="Type Mission" class="divRadio">
        <label id="Type Title">Type</label>
        <div class="radio"><input id="Mission Form" type="radio" name="typeMission" value="Mission" checked  v-on:click="updateBlock(currentBlock)">Mission</div>
        <div class="radio"><input id="Séminaire Form" type="radio" name="typeMission" value="Séminaire"  v-on:click="updateBlock(currentBlock)">Séminaire</div>
        <p></p>
      </div>


      <div class="divForm" style="margin-top:2em">
        <label id="Client" v-bind:class="labelClientClass">Client</label>
        <input id="Client Form" v-model="nameClient" type="text" v-on:keyup="updateBlock(currentBlock)" class="inputText"
               @focus="setFocusLabelClass(2)" @blur="changeLabelClass(nameClient,2)">
        <i class="fa fa-id-card-o picto"></i>
      </div>
      <span class="messageError" v-if="client == ''">Veuillez entrer un client</span>
      <sector :client="client" :domain="domain" @updateSector="updateSector"></sector>
    </div>

    <div class="containerColumn">
      <div id="Calendar" class="calendarDiv">
        <div id="StartCalendar" class="start">
          <label id="Start Title">Début</label>
          <div class="inputCalendar">
            <template v-if="beginDate!=''">
              <input id="Start Calendar Date" :value="beginDate" class="inputDate" type="date" v-on:click="updateBlock(currentBlock)" @input="updateBlock(currentBlock)"><i class="fa fa-calendar cal"></i>
            </template>
            <template v-else="endDate!=''">
              <input id="Start Calendar Date" class="inputDate" type="date" @input="updateBlock(currentBlock)"><i class="fa fa-calendar cal"></i>
            </template>
          </div>
        </div>
        <span class="messageError" v-if="beginDate == ''">Veuillez entrer une date de début</span>
        <div id="EndCalendar" class="end">
          <label id="Fin Title">Fin</label>
          <div class="inputCalendar">
            <template v-if="endDate!=''">
              <input v-if="endDate=='now'" id="End Calendar Date" :value="today" class="inputDate" type="date" v-on:click="updateBlock(currentBlock)" @input="updateBlock(currentBlock)">
              <input v-else id="End Calendar Date" :value="endDate" class="inputDate" type="date" v-on:click="updateBlock(currentBlock)" @input="updateBlock(currentBlock)">
              <i class="fa fa-calendar cal"></i>
            </template>
            <template v-else="endDate!=''">
              <input id="End Calendar Date" class="inputDate" type="date" @input="updateBlock(currentBlock)"><i class="fa fa-calendar cal"></i>
            </template>
          </div>
          <div v-if="endDate=='now'"><input id="Until Now Box" type="checkbox" checked @click="updateBlock(currentBlock)">Jusqu'à ce jour</div>
          <div v-else><input id="Until Now Box" type="checkbox" @click="updateBlock(currentBlock)">Jusqu'à ce jour</div>
        </div>
      </div>


      <div class="divTextArea">
        <label id="descriptionLabel" v-bind:class="labelDescriptionClass">Description</label>
        <textarea id="Description" v-model="descriptionMission" @input="updateBlock(currentBlock)" class="inputTextArea" rows="4"
                  @focus="setFocusLabelClass(3)" @blur="changeLabelClass(descriptionMission,3)"/>
        <i class="fa fa-pencil-square-o picto"></i>
      </div>
      <span class="messageError" v-if="descriptionMission == ''" style="display:block; position: relative; top: 3em;">Veuillez entrer une description de mission</span>
    </div>
  </div>
</template>


<script>
  import { bus } from '../../EventBus.js';
  import fieldActivity from './fieldActivity.vue'

  export default{
    components:{
      sector: fieldActivity
    },
    data: function(){
      return {
        labelTitleClass:this.titleMission!=""?"label-full":"label-empty",
        nameMission:this.titleMission,
        labelClientClass:this.client!=""?"label-full":"label-empty",
        nameClient:this.client,
        labelDescriptionClass:this.description!=""?"labelDescription-full":"labelDescription-empty",
        descriptionMission:this.description
      }
    },
    methods:{
      updateBlock(currentBlock){
        if(event.target.id=='Until Now Box' && event.target.checked){
          document.getElementById('End Calendar Date').value=this.today;
        }else if(event.target.id=='End Calendar Date'){
          if(event.target.value==this.today){
            document.getElementById('Until Now Box').checked=true;
          }else{
            document.getElementById('Until Now Box').checked=false;
          }
        }
        bus.$emit('changeBlock',currentBlock);
        this.$emit('updateProps');
      },
      toggleShowMenu() {
        this.showMenu=!this.showMenu;
      },
      changeLabelClass(text,numLabel){
        if(text!=""){
          if(numLabel==1) this.labelTitleClass = "label-full";
          else if(numLabel==2) this.labelClientClass = "label-full";
          else if(numLabel==3) this.labelDescriptionClass = "labelDescription-full";
        }
        else{
          if(numLabel==1) this.labelTitleClass = "label-empty";
          else if(numLabel==2) this.labelClientClass = "label-empty";
          else if(numLabel==3) this.labelDescriptionClass = "labelDescription-empty";
        }
      },
      setFocusLabelClass(numLabel){
        if(numLabel==1) this.labelTitleClass = "label-full";
        else if(numLabel==2) this.labelClientClass = "label-full";
        else if(numLabel==3) this.labelDescriptionClass = "labelDescription-full";
      },
      updateSector(sector){
        this.$emit('updateSector',sector);
      }
    },
    props: ['beginDate','endDate','titleMission','description','client','typeM','currentBlock','today','domain'],
    watch: {
      titleMission: function (value,old) {
        this.nameMission=this.titleMission;
        this.labelTitleClass=this.titleMission!=""?"label-full":"label-empty";
      },
      client: function (value,old) {
        this.labelClientClass=this.client!=""?"label-full":"label-empty";
        this.nameClient=this.client;
      },
      description: function (value,old) {
        this.labelDescriptionClass=value!=""?"labelDescription-full":"labelDescription-empty";
        this.descriptionMission=value
      }
    }
  }
</script>

<style>
  .inputText{
    padding: 0.5em 0px 0.5em 2.3em;
    width: 100%;
    border: none;
    border-bottom: 2px solid #ccc;
    height: 1em;
    z-index:1;
    background: transparent;
  }

  .inputTextArea{
    padding: 0.5em 0px 0.5em 2.3em;
    width: 100%;
    border: none;
    border-bottom: 2px solid #ccc;
    z-index:1;
    background: transparent;
  }
  .inputDate{
    padding: 0.5em 0em 0.5em 0em;
    width: 20em;
    border: none;
    border-bottom: 1px solid #ccc;
    margin-top: -0.6em;
    height: 1em
  }
  .picto{
    height: 2em;
    position: relative;
    left: -33.5em;
    top: 0.2em
  }
  .divForm{
    height: 35px;
    display: flex;
    position: relative;
    flex-direction: row;
    margin-bottom:0.2em;
    margin-top:1em;
    width: 35em;
    align-items: center;
    justify-content: flex-start;
  }
  .divTextArea{
    height: 35px;
    display: flex;
    position: relative;
    flex-direction: row;
    margin-bottom:0.2em;
    margin-top:1em;
    width: 35em;
    align-items: flex-start;
    justify-content: flex-start;
  }
  .divRadio{
    height: 35px;
    display: flex;
    position: relative;
    flex-direction: row;
    margin-bottom:0.2em;
    margin-top:1em;
    width: 35em;
    align-items: center;
    justify-content: flex-start;
    /*border: 1px solid black*/
  }
  .containerForm{
    display: flex;
    flex-direction: row;
    align-items: left;
    height: 15em;
    justify-content: space-between;
  }
  .containerColumn{
    margin-top:3vh;
    display: flex;
    width : 50em;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;
  }
  .calendarDiv{
    height: 5em;
    display: flex;
    position: relative;
    flex-direction: row;
    margin-bottom:0.2em;
    width: 40em;
    align-items: center;
    /*border: 1px solid black*/
  }
  .cal{
    height: 1em;
    position: relative;
    left: -1em;
    top: -0.2em;
    pointer-events: none;
    background-color: white;
  }
  .inputCalendar{
    display: flex;
    flex-direction: row;
  }
  textarea {
    padding-left: 2.3em;
    resize: none;
    overflow: hidden;
  }
  .start{
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 5em;
    margin-right : 3em;
  }
  .end{
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 5em;
    align-self: flex-end;
  }
  /*.start label{*/
  /*padding-right: 5em ;*/
  /*}*/
  .radio{
    padding-left: 5em;
  }
  ::-webkit-input-placeholder {
    position: relative;
    left: -0.55em;
    text-align: center;
  }
  :-ms-input-placeholder {
    position: relative;
    left: -0.55em;
    text-align: center;
  }
  .containerForm textarea:focus {
    border-bottom: 2px solid dodgerblue;
    border-top:none;
    border-right:none;
    border-left:none;
  }
  .containerForm textarea{
    outline: none;
  }
  label{
    color: dodgerblue;
    font-family: Bradley Hand ITC, cursive;
    font-weight: bold;
    font-size: 3vh;
    transition: bottom 0.5s, left 0.5s, font-size 0.5s;
  }
  .label-full {
    position:absolute;
    bottom : 4vh;
    left:0vw;
    font-size : 1.5em;
  }
  .label-empty{
    bottom : 0.5vh;
    position:absolute;
    left:2vw;
    font-size : 2em;
  }
  .labelDescription-full {
    position:absolute;
    top : -1em;
    left:0vw;
    font-size : 1.5em;
  }
  .labelDescription-empty{
    top : -0.3em;
    position:absolute;
    left:2vw;
    font-size : 2em;
  }
  .containerForm input{
    outline: none;
  }
  .messageError{
    font-size: 14px;
    color: red;
    text-align: center;
  }
  .containerForm input[type=text]:focus {
    border-bottom: 2px solid dodgerblue;
    border-top:none;
    border-right:none;
    border-left:none;
  }
</style>
