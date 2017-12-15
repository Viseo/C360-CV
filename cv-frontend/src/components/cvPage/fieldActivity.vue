<template>
  <div>
    <div v-if="currentClient != ''" class="sectorContent">
      <span class="spanActivities" v-if=" currentDomain != '' " v-on:click="updateChamps"
            v-bind:style="applyStyle(fieldsOfActivities.indexOf(currentDomain))" >{{currentDomain}}</span>
      <span class="spanActivities" v-else="currentDomain == ''"  v-for="(sector,index) in fieldsOfActivities"
            v-show="toShow[index]" v-on:click="selectedSector(index)" v-bind:style="applyStyle(index)">{{sector}}</span>
    </div>
    <div v-else="currentClient == ''"></div>
  </div>
</template>

<script>

  export default{
    data: function () {
      return {
        fieldsOfActivities: ['ASSURANCE', 'BANQUE', 'COMMERCE', 'CONSEIL', 'DISTRIBUTION',
          'FINANCE', 'INDUSTRIE', 'LUXE', 'MEDIA', 'TELECOMS', 'TOURISME'],
        color: ['#66a3ff', '#00e6ac', '#ff00ff', '#808080', '#ffcccc', '#00b33c', '#1ad1ff',
          '#ffccff', '#ffd11a', '#8533ff', '#800080'],
        toShow: [true,true,true,true,true,true,true,true,true,true,true],
        sectorCurrent: "",
        currentDomain: this.client.domain,
        currentClient: this.client
      }
    },
    methods: {
      applyStyle: function (index) {
        return "color: "+ this.color[index];
      },
      selectedSector: function (index) {
        for(let i = 0; i<this.toShow.length;i++){
          if(i!=index){
            this.toShow.splice(i,1,!this.toShow[i]);
          }else{
            this.sectorCurrent = this.fieldsOfActivities[i];
            this.$emit('updateSector',this.sectorCurrent);
          }
        }
        this.updateChamps();
      },
      updateChamps: function () {
        this.currentDomain = '';
        for(let i=0;i<this.toShow.length;i++){
          this.toShow[i] = true;
        }
      }
    },
    //props: ['client','domain'],
    props: ['client']
//    watch:{
//      domain: function () {
//        this.currentDomain = this.domain;
//      },
//      client: function () {
//        this.currentClient = this.client;
//      }
//    }
  }
</script>

<style>
  .sectorContent{display: flex; flex-wrap: wrap}
  .spanActivities{
    font-size: 10px;
    font-weight: bold;
    margin-right: 3px;
  }
</style>
