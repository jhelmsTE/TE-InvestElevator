<template>
<div>
    <p>Welcome</p>
    <p>invite players</p>
    <div v-for="user in users" v-bind:key="user.id" input type="checkbox">
        <p>{{user.username}}</p>  
    </div>

    <p>Start Date</p>
     <input type="date" v-model="date">
    <p>End date</p>
    <input type="date" v-model="date">
       <br>
    <button type="submit">Submit</button>
    
</div>
</template>

<script>
import AuthService from '../services/AuthService.js'
export default {
  data() {
    return {
      users: "",
    };
  },
  created() {
    AuthService.getUsers().then(response => {
      this.users = response.data;
    });
  }
};

</script>

<style>

</style>