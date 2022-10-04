<template>
  <div class>
    <h1>Welcome to the Game Board!</h1>
    <h3>Here are the games you can join! Click a game to join</h3>
    <div class="searchBar">
      <input type="text" v-model="selectedGame" placeholder="Search games..." />
    </div>
    <div class="all-cards">
      <button v-bind:gameId="game.id"
        v-on:click="goToGameDetails(game.id)"
        class="card"
        v-for="game in filteredGamesForSearchBar" v-bind:key="game.id"
      >
        <h4>{{ game.gameName }}</h4>
      </button>
    </div>
  </div>
</template>

<script>
import gameService from "../services/GameService";

export default {
  data() {
    return {
      selectedGame: "",
      games: [],
      gameResults: [],
      game: {
        id: "",
        name: "",
      },
      user: {
        id: "",
        username: "",
      },
      gameResult: {
        userId: "",
        userName: "",
        gameName: "",
      },
    };
  },
  created() {
    gameService.getAllGames().then((response) => {
      this.games = response.data;
    });
    gameService.getGameResultsByNotCurrentUser()
    .then((response) => {
      this.gameResults = response.data
    });
  },
  computed: {
    filteredGamesForSearchBar() {
      return this.gameResults.filter((game) => {
        return (
          game.gameName
            .toLowerCase()
            .indexOf(this.selectedGame.toLowerCase()) != -1
        );
      });
    },
  },
  methods: {
    goToGameDetails(id){
      console.log(id)
      this.$router.push({ name: 'game-details', params: { id: id } })
    }
  }
};
</script>

<style>
h1,
h3 {
  display: flex;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  text-shadow: 2px 2px 4px black;
  padding-right: 12px;
  justify-content: center;
}
a:link {
  justify-content: center;
  align-items: center;
}
.all-cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}
.card {
  border: 2px solid black;
  border-radius: 10px;
  width: 250px;
  height: 200px;
  margin: 40px;
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  text-shadow: 2px 2px 4px black;
  text-decoration: none;
  padding-right: 12px;
  background-color: #44c767;
  color: white;
}
.searchBar {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>