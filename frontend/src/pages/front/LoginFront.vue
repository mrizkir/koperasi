<template>
  <v-btn    
    class="text-none mb-4"
    color="indigo-darken-3"
    size="x-large"
    variant="flat"
    block
    @click="doLogin"
  >
    Verify and continue
  </v-btn>
</template>

<script lang="ts">
  import axios from 'axios'
  export default {
    methods: {
      async doLogin() {        
        axios.post("https://koperasi.yacanet.com/api/v1/auth/login", {
          username: 'admi', 
          password: '12345678', 
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
        .then(function(res){
          console.log(res)
        })
        .catch(function(error) {
          if(error.response) {
            console.log(error.response.status);
          }
        });
      },    
    },
  }
</script>