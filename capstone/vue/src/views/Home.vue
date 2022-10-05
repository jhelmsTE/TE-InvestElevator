<template>
  <div class="home">
    <h1>Home</h1>
    <div id="welcome">Welcome to the InvestElevator!</div>
    <div class="homeItems">
      <div class="user">
        <p>Current User: {{ this.$store.state.user.username }}</p>
      </div>
      <div class="routerItems">
        <router-link :to="{ name: 'game-view' }" class="create-game">
          Create Game
        </router-link>
        <br />
        <router-link :to="{ name: 'viewAllGames' }" class="current-games">
          View Your Games
        </router-link>
        <br />
        <router-link :to="{ name: 'joinGame' }" class="join-game">
          Join a Game
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import GameService from "../services/GameService";

export default {
  name: "home",
  data() {
    return {
      user: {
        username: "",
      },
    };
  },
  methods: {
    createGame() {
      this.game.username = this.$store.state.user.username;
      GameService.create(this.game);
    },
  },
};
</script>

<style>
.home {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
body{
  background-image: url(../images/stock-market-game-header-img.jpeg);
  color: white;
  width: 100%;
  min-height: 100%;
}
.homeItems {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
#welcome {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  display: flex;
  justify-content: center;
}
.current-games {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
}
.create-game {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
}
.join-game {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
}
.user {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  font-size: large;
  padding-bottom: 40px;
}
a {
  color: white;
}
.routerItems{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 2px solid limegreen;
  padding: 20px;
  background: rgba(26, 17, 89, .9)
}
</style>
