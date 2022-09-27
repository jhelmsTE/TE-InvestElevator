<template>
<div>
    <h1>Welcome</h1>
    <h2 id='invite'>Invite Players</h2>
   
    <tr>
    <td v-for="user in users" v-bind:key="user.id" input type="checkbox">
        <p>{{user.username}}</p>  
    </td>
    </tr>
    
    <h3 class="start-date">Start Date</h3>
     <input type="date" id='start-date'>

    <h3 class="end-date">End date</h3>
    <input type="date" id='end-date'>

       <br>
    <button type="submit" class="submit">Create Game</button>
    
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
#invite{
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    text-align: left;
    margin-left: 10px;
}

.start-date{
justify-content: left;
margin-left: 10px;
}
#start-date{
justify-content: left;
margin-left: 10px;
}

.end-date{
justify-content: left;
margin-left: 10px;
}
#end-date{
margin-left: 10px;
}
.submit{
 margin-top: 20px;
 margin-left: 15px;
}


</style>