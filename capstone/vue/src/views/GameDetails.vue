<template>
  <div>
    <router-link class="backToGames" :to="{ name: 'viewAllGames' }"
      >Back to View All Games</router-link
    >
    <div class="joinGame">
      To join this game, click the "Join Game" button here:
      <div v-for="user in users" v-bind:key="user.id">
        <button
          v-on:click="addUser(user.id, user.username)"
          v-show="user.username === $store.state.user.username"
        >
          Join Game
        </button>
      </div>
    </div>
    <h1>Game Name: {{ game.gameName }}</h1>
    <div class="gameIntro">
      <h2 class="organizer">Organizer: {{ game.username }}</h2>
      <h4>Game Start: {{ game.startDate }}</h4>
      <h4>Game End: {{ game.endDate }}</h4>
    </div>
    <div class="flex-container">
      <div class="flex-child balanceAndStocks">
        <div v-for="gameResult in currentGameResults" v-bind:key="gameResult.id">
          <p v-show="gameResult.userName === $store.state.user.username">
            My Current Balance: {{gameResult.cashToTrade}}
          </p>
        </div>
        <p class="stocksOwned">Stocks Owned</p>
        <router-link class="stockButton" :to="{ name: 'buyStocks' }"
          >Buy Stocks</router-link
        >
        <router-link class="stockButton" :to="{ name: 'sellStocks' }"
          >Sell Stocks</router-link
        >
      </div>
      <div class="flex-child">
        <p>Game Participants and Current Balances</p>
        <div class="participants">
          <p v-for="gameResult in currentGameResults" v-bind:key="gameResult.id">
            {{ gameResult.userName }} {{ gameResult.cashToTrade }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import gameService from "../services/GameService";
import authService from "../services/AuthService";

export default {
  data() {
    return {
      selectedGameResultUsers: [],
      users: [],
      gameResults: [],
      user: {
        id: "",
        username: "",
      },
      game: {
        username: "",
        startDate: "",
        endDate: "",
        gameName: "",
        gameResult: "",
      },
      gameResult: {
        userId: "",
        userName: "",
        gameName: "",
      },
    };
  },
  created() {
    authService.getUsers().then((response) => {
      this.users = response.data;
    });
    gameService.getGameDetails(this.$route.params.id).then((response) => {
      this.game = response.data,
      this.$store.commit("SET_GAME_DETAIL_ID", this.$route.params.id)

    });
    gameService
      .getGameResultsDetails(this.$route.params.id)
      .then((response) => {
        this.gameResults = response.data;
      });
  },
  methods: {
    addUser(id, username){
      this.gameResult.userId = id;
      this.gameResult.userName = username;
      this.gameResult.gameName = this.game.gameName;
      this.selectedGameResultUsers.push(this.gameResult)
      // this.gameResults = { userId: "", gameName: "", userName: ""}
      gameService.createGameResult(this.selectedGameResultUsers).then(gameService.getGameResultsDetails(this.$route.params.id)
      .then((response) => {
        this.gameResults = response.data;
      }));
      }
  },
  computed: {
    currentGameResults() {
      return this.gameResults;
    }
  }
};
</script>

<style>
.gameIntro {
  display: flex;
  flex-direction: column;
  padding-left: 15%;
  padding-bottom: 20px;
  width: 20%;
}
.flex-container {
  display: flex;
  width: 80%;
  padding-left: 10%;
  padding-right: 10%;
}
.flex-child {
  padding-left: 10px;
  padding-bottom: 10px;
  flex: 1;
  border: 2px solid white;
}
.flex-child:first-child {
  margin-right: 20px;
}
.balanceAndStocks {
  padding-left: 10px;
  font-weight: bold;
}
.participants {
  display: flex;
  flex-direction: column;
  font-weight: bold;
}
.stocksOwned {
  text-decoration: underline;
}
.backToGames {
  font-weight: bold;
  margin-left: 10px;
}
.stockButton {
  background-color: #44c767;
  border-radius: 28px;
  border: 1px solid #18ab29;
  display: inline-block;
  cursor: pointer;
  color: #ffffff;
  font-family: Arial;
  font-size: 15px;
  padding: 15px 31px;
  text-decoration: none;
  text-shadow: 0px 1px 0px #2f6627;
  margin-top: 60px;
  margin-right: 10px;
}
.stockButton:hover {
  background-color: #5cbf2a;
}
.stockButton:active {
  position: relative;
  top: 1px;
}
.joinGame {
  display: flex;
  justify-content: center;
  align-items: center;
  padding-top: 30px;
  padding-left: 10px;
}
</style>