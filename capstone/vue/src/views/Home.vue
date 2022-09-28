<template>
  <div class="home">
    <h1>Home</h1>
    <div id="welcome">
      Welcome!
    </div>
    <div class= "user" v-for="user in $store.state" v-bind:key="user.id">
      <p>{{user.username}}</p>
    </div>
    <div v-on:click="createGame(game)">
  <router-link :to="{name: 'game-view'}" class="create-game"  >
    Create Game
  </router-link>
  </div>
  <br>
  <router-link :to="{name: 'viewAllGames'}" class="current-games">
    View all Current Games
  </router-link>


  </div>

</template>

<script>
import GameService from "../services/GameService"


export default {
  name: "home",
  data() {
    return {
      user: {
        username:"",
      },
      game: {
      username: "",
      startDate: "2022-10-27",
      endDate: "2022-10-27",
      gameName: "",
      gameResult: ""
      }
    }
  }, 
  methods:{
    createGame(){
      this.game.username = this.$store.state.user.username
      GameService.create(this.game);
    }
  }

};
</script>

<style>

.home{
  display: flex;
  flex-direction:column;
  justify-content: center;
}
body, html{
  background: rgb(0,175,239,255);
  margin: 0px;
  padding: 0px;
  width: 100%;
  min-height: 100%;
}
#welcome{
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  margin-left: 10px;
}
.current-games{
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  margin-left: 10px;
}

.create-game{
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  margin-left: 10px;
}

.user{
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  margin-left: 10px;
  font-size: large;
}



</style>
