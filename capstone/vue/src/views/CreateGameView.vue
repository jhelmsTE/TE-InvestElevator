<template>
<form v-on:submit.prevent>
  <div>
    <h1>Welcome</h1>
    <h2 id="invite">Invite Players</h2>
    <div class="checkBoxForm" v-for="user in users" v-bind:key="user.id" >
      <input class="checkboxes" type="checkbox" v-on:click='addUser(user.id, user.username)'>
      <label for="user.id">{{ user.username }}</label>
    </div>

    <h3 class="game-name" >Game name</h3>
    <input type="text" v-model="game.gameName"/>

    <h3 class="start-date">Start Date</h3>
    <input type="date" id="start-date" v-model="game.startDate"/>

    <h3 class="end-date">End date</h3>
    <input type="date" id="end-date" v-model="game.endDate"/>

    <br/>
   <button v-on:click="createGame(game)">Create Game</button> 
   <button v-on:click="createGameResult()">Add Players</button> 

</div>

</form>
</template>
<script>
import AuthService from "../services/AuthService";
import GameService from "../services/GameService";
export default {
  data() {
    return {
      selectedGameResultUsers : [],
      users: [],
      user: {
        name: "",
      },
      game: {
      username: "",
      startDate: "",
      endDate: "",
      gameName: "",
      gameResult: ""
      },
      gameResults : {
        userId: "",
        gameName: "",
        userName: ""
      }
    };
  },
   created() {
    AuthService.getUsers().then((response) => {
      this.users = response.data;
    });
   },
  methods:{
  createGameResult(){
    GameService.createGameResult(this.selectedGameResultUsers);
    this.selectedGameResultUsers = [];
    this.game = {};
    this.$router.push({name: "home"})
  },
 
  createGame(){
      this.game.username = this.$store.state.user.username
      GameService.create(this.game);
      
    },
    addUser(id, username){
      console.log(id)
      console.log(username)
      this.gameResults.userId = id;
      this.gameResults.userName = username;
      this.gameResults.gameName = this.game.gameName;
      this.selectedGameResultUsers.push(this.gameResults)
      this.gameResults = { userId: "",
        gameName: "",
        userName: ""}
      console.log(this.selectedGameResultUsers.length)
      }
     }
}
</script>
<style>
#invite {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  text-shadow: 2px 2px 4px honeydew;
  text-align: left;
  margin-left: 10px;
}
.checkBoxForm {
  padding-left: 10px;
  padding-bottom: 20px;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  text-shadow: 2px 2px 4px honeydew;
}
.checkboxes {
  margin-right: 10px;
}
.start-date {
  justify-content: left;
  margin-left: 10px;
}
#start-date {
  justify-content: left;
  margin-left: 10px;
}
.end-date {
  justify-content: left;
  margin-left: 10px;
}
#end-date {
  margin-left: 10px;
}
.submit {
  margin-top: 20px;
  margin-left: 15px;
}
.game-name{
  justify-content: left;
  margin-left: 10px;
}
</style>






