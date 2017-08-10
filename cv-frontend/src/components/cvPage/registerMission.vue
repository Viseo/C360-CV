<template>
  <div class="containerForm">
    <div class="lineInfo">
      <div class="inputDiv">
        <i class="fa fa-suitcase fa-2x"></i>
        <div>
          <label v-bind:class="labelTitleClass">Titre de la mission</label>
          <input id="Title Mission" v-model="nameMission" type='text' class="inputText" @focus="setFocusLabelClass(1)"
                 @keyup="updateBlock(currentBlock)" @blur="changeLabelClass(nameMission,1)">
        </div>
      </div>

      <div class="inputDiv">
        <i class="fa fa-file-text fa-2x"></i>
        <div>
          <label id="Type Title">Type</label>
          <select v-model="typeMission" v-on:click="updateBlock(currentBlock)" :value="typeM">
            <option>Mission</option>
            <option>Séminaire</option>
          </select>
        </div>
      </div>

      <div id="StartCalendar" class="inputDiv">
        <i class="fa fa-calendar fa-2x"></i>
        <div>
          <label id="Start Title">Début</label>
          <div class="inputCalendar">
            <template v-if="beginDate!=''">
              <input id="Start Calendar Date" :value="beginDate" v-model="beginInput" class="inputDate" type="date" v-on:click="updateBlock(currentBlock)" @input="updateBlock(currentBlock)">
            </template>
            <template v-else="endDate!=''">
              <input id="Start Calendar Date" class="inputDate" type="date" @input="updateBlock(currentBlock)">
            </template>
          </div>
        </div>

      </div>
      <span class="messageError" v-if="beginDate == ''">Veuillez entrer une date de début</span>

      <div id="EndCalendar" class="inputDiv">
        <i class="fa fa-calendar fa-2x cal"></i>
        <div>
          <label id="Fin Title">Fin</label>
          <div class="inputCalendar">
            <template v-if="endDate!=''">
              <input id="End Calendar Date" v-model="endInput" :value="endDate" class="inputDate" type="date" v-on:click="updateBlock()" @input="updateBlock()">
            </template>
          </div>
          <div v-if="endDate=='now'" id="checkboxNow"><input id="Until Now Box" type="checkbox" checked>Jusqu'à ce jour</div>
          <div v-else id="checkboxNow"><input id="Until Now Box" type="checkbox" @click="endInput=today">Jusqu'à ce jour</div>
        </div>
      </div>
    </div>

    <div class="lineClient">
      <div class="clientDiv">
        <i class="fa fa-id-card-o fa-2x"></i>
        <div>
          <label id="Client" v-bind:class="labelClientClass">Client</label>
          <input id="Client Form" v-model="clientMission" type="text" v-on:keyup="updateBlock()" class="inputText"
                 @focus="setFocusLabelClass(2)" @blur="changeLabelClass(clientMission,2)">
        </div>
      </div>
      <div class="listSector">
        <span class="messageError" v-if="client == ''">Veuillez entrer un client</span>
        <sector :client="client" :domain="domain" @updateSector="updateSector"></sector>
      </div>
    </div>

    <div class="lineDesc">
      <div class="descDiv">
        <i class="fa fa-pencil-square-o fa-2x"></i>
        <div>
          <label id="descriptionLabel" v-bind:class="labelDescriptionClass">Description</label>
          <textarea id="Description" v-model="descriptionMission" @input="updateBlock(currentBlock)" class="inputTextArea" rows="4"
                    @focus="setFocusLabelClass(3)" @blur="changeLabelClass(descriptionMission,3)"/>
          <span class="messageError" v-if="descriptionMission == ''" style="display:block; position: relative; top: 3em;">Veuillez entrer une description de mission</span>
        </div>
      </div>
    </div>
    <div class="containerColumn">



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
    props: ['beginDate','endDate','titleMission','description','client','typeM','currentBlock','today','domain'],
    data: function() {
      return {
        labelTitleClass: this.titleMission != "" ? "label-full" : "label-empty",
        nameMission: this.titleMission,
        labelClientClass: this.client != "" ? "label-full" : "label-empty",
        clientMission: this.client,
        labelDescriptionClass: this.description != "" ? "labelDescription-full" : "labelDescription-empty",
        descriptionMission: this.description,
        beginInput: "",
        endInput: "",
        typeMission:""
      }
    },
    methods:{
      updateBlock(){
        if(event.target.id=='Until Now Box' && event.target.checked){
          document.getElementById('End Calendar Date').value=this.today;
        }else if(event.target.id=='End Calendar Date'){
          if(event.target.value==this.today){
            document.getElementById('Until Now Box').checked=true;
          }else{
            document.getElementById('Until Now Box').checked=false;
          }
        }
        this.$emit('updateProps',this.nameMission,this.clientMission,this.beginInput,this.endInput,this.descriptionMission,this.typeMission);
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
    watch: {
      titleMission: function () {
        this.nameMission=this.titleMission;
        this.labelTitleClass=this.titleMission!=""?"label-full":"label-empty";
      },
      client: function () {
        this.labelClientClass=this.client!=""?"label-full":"label-empty";
        this.clientMission=this.client;
      },
      description: function (value) {
        this.labelDescriptionClass=value!=""?"labelDescription-full":"labelDescription-empty";
        this.descriptionMission=value
      },
      beginDate: function(value){
          this.beginInput=value;
      },
      endDate: function(value){
        this.endInput=value;
      },
      typeM:function(value){
        this.typeMission=value;
      }
    }
  }
</script>

<style>
  .containerForm label{color: dimgrey; font-family: Arial, cursive; font-weight: bold; transition: all 0.5s; margin-left: 0.2em}
  .label-full {position: absolute; top: 0em; left: 2.2em}
  .label-empty{position: absolute; top: 0.8em; left: 1.6em; font-size : 1.5em;}
  .labelDescription-full {position: absolute; top: -1em; left: 2.2em}
  .labelDescription-empty{position: absolute; top: 0em; left: 1.6em; font-size : 1.5em;}

  .containerForm{display: flex; flex-direction: column; position: relative}
  /*.containerForm label{position: relative; top: -0.5em; left: 0.5em}*/

  .lineInfo{display: flex; flex-direction: row; justify-content: space-between; align-items: center; height: 3em; margin: 1% 0 1% 2%;}

  .inputDiv{display: flex; flex-direction: row; align-items: flex-end; flex-grow: 1; flex-basis: 25%; position: relative;height: 3em}
  .inputDiv > div{display: flex; flex-direction: column; width: 100%}
  .inputDiv input{border: none; border-bottom: 1px solid dimgray; margin-left: 8px; width: 80%; height: 2em; z-index: 1; background: transparent;}
  .inputDiv select{border: none; border-bottom: 1px solid dimgrey; margin-left: 8px; width: 80%; height: 2em; z-index: 1; background: transparent;}
  #checkboxNow{display:flex; flex-direction:row; justify-content: center; align-items: center; position: absolute; top: 2.5em}
  #checkboxNow input{width: auto}


  .lineClient{display: flex; flex-direction: row; align-items: center; height: 3em; margin: 0% 0 1.7% 2%;}
  .clientDiv{display: flex; flex-direction: row; justify-content: flex-start; align-items: flex-end; width: 50%; flex-grow: 3; flex-basis: 50%; position: relative; height: 3em}
  .clientDiv > div{display: flex; flex-direction: column; width: 100%}
  .clientDiv input{border: none; border-bottom: 1px solid dimgray; margin-left: 8px; width: 95%; height: 2em; z-index: 1; background: transparent;}
  .clientDiv span{display:flex; flex-direction:row; justify-content: center; align-items: center; position: absolute; top: 3em;}

  .listSector{display: flex; flex-grow: 3; flex-basis: 50%; }

  .lineDesc{display: flex; flex-direction: row; align-items: center; height: 5em; margin: 0% 0 1% 2%;}
  .descDiv{display: flex; flex-direction: row; justify-content: flex-start; align-items: center; width: 50%; flex-grow: 3; flex-basis: 43%; position: relative; height: 5em}
  .descDiv > div{display: flex; flex-direction: column; width: 100%}
  .descDiv textarea{border: none; border-bottom: 1px solid dimgray; margin-left: 8px; width: 95%; z-index: 1; background: transparent; height: 5em}
  .descDiv span{display:flex !important; flex-direction:row; justify-content: center; align-items: center; position: absolute !important; top: 5em !important; }



</style>
