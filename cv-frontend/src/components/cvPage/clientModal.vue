<template>

    <transition name="clientModal">
      <!--attach a client-->
      <div class="clientModal-mask" v-if="newClientOrNot">
        <div class="clientModal-wrapper">
          <div class="clientModal-container">

            <div class="clientModal-header">
              <slot name="header">
                <h3>
                  Lier votre mission à un client
                  <button style="float:right"
                          class="clientModal-default-button button button-caution button-circle button-tiny"
                          @click="$emit('close')">
                    <i class="fa fa-times"></i>
                  </button>
                </h3>

              </slot>
            </div>

            <div class="clientModal-body">
              <slot name="body">
                <button class="button button-royal button-small button-rounded" v-for="client in clients"
                        style="margin-right: 15px;margin-bottom: 10px;" @click="chooseClient(client)">
                  {{client.label}}
                </button>
              </slot>
            </div>

            <div class="clientModal-footer">
              <slot name="footer" >
                  <button class="button button-action button-pill button-small"
                        @click="$emit('close')">
                  Confirmer
                </button>

              </slot>
            </div>
          </div>
        </div>
      </div>
      <!--change or verify the attached client -->
      <div class="clientModal-mask" v-else>
        <div class="clientModal-wrapper">
          <div class="clientModal-container">

            <div class="clientModal-header">
              <slot name="header">
                <h3>
                  Les details du client
                  <button style="float:right"
                          class="clientModal-default-button button button-caution button-circle button-tiny"
                          @click="$emit('close')">
                    <i class="fa fa-times"></i>
                  </button>
                </h3>

              </slot>
            </div>

            <div class="clientModal-body">
              <slot name="body">
                <table>
                  <tr>
                    <td>
                      Client
                    </td>
                    <td>
                      {{currentMissionClient.label}}
                    </td>
                  </tr>
                  <tr>
                    <td>
                      Domaine
                    </td>
                    <td>
                      {{currentMissionClient.domain}}
                    </td>
                  </tr>
                  <tr>
                    <td>
                      Description
                    </td>
                    <td>
                      {{currentMissionClient.description}}
                    </td>
                  </tr>
                </table>
              </slot>
            </div>

            <div class="clientModal-footer">
              <slot name="footer" >
                <button class="button button-action button-pill button-small"
                        @click="changeClient()">
                  Changer client
                </button>
                <button class="button button-royal button-pill button-small"
                        @click="$emit('close')" style="float:right">
                  OK
                </button>
              </slot>
            </div>
          </div>
        </div>
      </div>
    </transition>

</template>

<script>
  import axios from 'axios';
  import config from '../../config/config';

  export default {
    data:function(){
      return{
        newClientOrNot:(this.$store.state.currentMission.client.id == undefined),
        currentMissionClient:this.$store.state.currentMission.client,
        clients:''
      }
    },
    mounted:function(){
      axios.get(config.server + '/api/clients').then(response =>{
        this.clients = response.data;
        this.clients.sort((a, b) => a.label.localeCompare(b.label));
      })
      .catch(error => {

      })
    },
    methods:{
      chooseClient:function(client){
        this.$store.state.currentMission.client = client;
        this.newClientOrNot = false;
        this.currentMissionClient = client;
      },
      changeClient:function(){
        this.$store.state.currentMission.client = '';
        this.newClientOrNot = true;
        this.currentMissionClient = '';
      }
    }
  }
</script>

<style>
  .clientModal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, .5);
    display: table;
    transition: opacity .3s ease;
  }

  .clientModal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }

  .clientModal-container {
    width: 800px;
    margin: 0px auto;
    padding: 30px 30px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }

  .clientModal-header h3 {
    margin-top: 0;
    color: #42b983;
  }

  .clientModal-body {
    margin: 20px 0;
  }

  .clientModal-default-button{
    position:relative;
    top:-40px;
    right:-40px;
  }


  /*
   * The following styles are auto-applied to elements with
   * transition="modal" when their visibility is toggled
   * by Vue.js.
   *
   * You can easily play with the modal transition by editing
   * these styles.
   */

  .clientModal-enter {
    opacity: 0;
  }

  .clientModal-leave-active {
    opacity: 0;
  }

  .clientModal-enter .modal-container,
  .clientModal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }

  table {
    border-collapse: collapse;
    width: 100%;
  }

  th, td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
  }

  tr:hover {background-color:#f5f5f5;}

</style>

