<template>
  <div class="containerForm">
    <div class="lineInfo">
      <div class="inputDiv">
        <i class="fa fa-suitcase fa-2x"></i>
        <div>
          <label v-bind:class="currentMission.title != '' ? 'label-full' : 'label-empty'">Titre de la mission*</label>
          <input id="Title Mission" v-model="currentMission.title" type='text' class="inputText" @focus="setFocusLabelClass(1)"
                 @blur="changeLabelClass(currentMission.title,1)">
        </div>
      </div>

      <div class="inputDiv">
        <i class="fa fa-file-text fa-2x"></i>
        <div>
          <label id="Type Title">Type*</label>
          <select v-model="currentMission.typeMissions.label">
            <option >Mission</option>
            <option>Séminaire</option>
          </select>
        </div>
      </div>

      <div id="StartCalendar" class="inputDiv">
        <i class="fa fa-calendar fa-2x"></i>
        <div>
          <label id="Start Title">Début*</label>
          <div class="inputCalendar">
            <input id="Start Calendar Date" v-model="currentMission.beginDate" class="inputDate" type="date" @input="checkBeginDate()">
          </div>
        </div>
      </div>

      <div id="EndCalendar" class="inputDiv">
        <i class="fa fa-calendar fa-2x cal"></i>
        <div>
          <label id="Fin Title">Fin*</label>
          <div class="inputCalendar">
            <input id="End Calendar Date" v-model="currentMission.endDate" class="inputDate" type="date" @input="checkEndDate()">
          </div>
          <div v-if="currentMission.endDate==today" id="checkboxNow"><input id="Until Now Box" type="checkbox" checked @click="currentMission.endDate='';">Jusqu'à ce jour</div>
          <div v-else id="checkboxNow"><input id="Until Now Box" type="checkbox" @click="currentMission.endDate=today;">Jusqu'à ce jour</div>
        </div>
      </div>
    </div>

    <div class="lineClient">
      <div class="clientDiv">
        <i class="fa fa-id-card-o fa-2x"></i>
        <div>
          <label id="Client" class="label-full">Client*</label>
          <div style="height:30px;margin-left:15px;">
            <button v-if="currentMission.client.label != undefined"
                    class="button button-primary button-pill button-small" @click="showClientModal = true">
              {{currentMission.client.label}}
            </button>
            <button class="button button-action button-circle button-small" @click="showClientModal = true" v-else>
              <i class="fa fa-plus"></i>
            </button>
          </div>
          <clientModal v-if="showClientModal" @close="showClientModal = false">
            <!--
              you can use custom content here to overwrite
              default content
            -->
            <!--<h3 slot="header">custom hdsdqeader</h3>-->
          </clientModal>
        </div>
      </div>
    </div>

    <div class="lineDesc">
      <div class="descDiv">
        <i class="fa fa-pencil-square-o fa-2x"></i>
        <div>
          <label id="descriptionLabel" v-bind:class="currentMission.description != '' ? 'labelDescription-full' : 'labelDescription-empty'">Description</label>
          <textarea id="Description" v-model="currentMission.description" class="inputTextArea" rows="4"
                    @focus="setFocusLabelClass(3)" @blur="changeLabelClass(currentMission.description,3)"/>
          <span class="messageError" v-if="currentMission.description == ''" style="display:block; position: relative; top: 3em;">Veuillez entrer une description de mission</span>
        </div>
      </div>
    </div>
    <div class="containerColumn">
    </div>
  </div>
</template>


<script>

  import clientModal from "../cvPage/clientModal.vue"

  export default{
    components:{
      clientModal:clientModal
    },
    data: function() {
      return {
        showClientModal: false
      }
    },
    props:['currentMission'],
    computed:{
      today:function(){
        let date = new Date();
        let thisDay, thisMonth;
        date.getDate() < 10 ? thisDay = '0' + date.getDate() : thisDay = date.getDate();
        date.getMonth() + 1 < 10 ? thisMonth = '0' + parseInt(date.getMonth() + 1) : thisMonth = parseInt(date.getMonth() + 1);
        return date.getFullYear() + '-' + thisMonth + '-' + thisDay;
      }
    },
    methods:{
      checkBeginDate(){
        if(this.currentMission.beginDate>this.currentMission.endDate && this.currentMission.endDate!=''){
          this.currentMission.endDate = this.currentMission.beginDate;
        }
      },
      checkEndDate(){
        if(this.currentMission.beginDate>this.currentMission.endDate && this.currentMission.beginDate!=''){
          this.currentMission.beginDate = this.currentMission.endDate;
        }
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
    }
//    watch: {
//      titleMission: function () {
//        this.nameMission=this.titleMission;
//        this.labelTitleClass=this.titleMission!=""?"label-full":"label-empty";
//      },
//      client: function () {
//        this.labelClientClass=this.client!=""?"label-full":"label-empty";
//        this.clientMission=this.client;
//      },
//      description: function (value) {
//        this.labelDescriptionClass=value!=""?"labelDescription-full":"labelDescription-empty";
//        this.descriptionMission=value
//      },
//      beginDate: function(value){
//          this.beginInput=value;
//      },
//      endDate: function(value){
//        this.endInput=value;
//      },
//      typeM:function(value){
//        this.typeMission=value;
//      }
//    }
  }
</script>

<style>
  .containerForm label{color: dimgrey; font-family: Arial, cursive; font-weight: bold; transition: all 0.5s; margin-left: 0.2em}
  .label-full {position: absolute; top: 0em; left: 2.2em}
  .label-empty{position: absolute; top: 0.8em; left: 1.6em; font-size : 1.4em;}
  .labelDescription-full {position: absolute; top: -1em; left: 2.2em}
  .labelDescription-empty{position: absolute; top: 0em; left: 1.6em; font-size : 1.5em;}

  .containerForm{display: flex; flex-direction: column; position: relative}

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
