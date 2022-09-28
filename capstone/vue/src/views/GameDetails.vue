<template>
  <div>
    <router-link class="backToGames" :to="{name: 'viewAllGames'}">Back to View All Games</router-link>
    <h1>Game Name: {{ game.gameName }}</h1>
    <h2 class="organizer">Organizer: {{ game.username }}</h2>
    <div class="flex-container">
      <div class="flex-child balanceAndStocks">
        <p>My Current Balance:</p>
        <p class="stocksOwned">Stocks Owned</p>
        <router-link class="stockButton" :to="{name: 'buyStocks'}">Buy Stocks</router-link>
        <router-link class="stockButton" :to="{name: 'sellStocks'}">Sell Stocks</router-link>
      </div>
      <div class="flex-child participantsAndBalances">
        <p>Game Participants and Current Balances</p>
      </div>
    </div>
  </div>
</template>

<script>
import gameService from "../services/GameService";

export default {
  data() {
    return {
      game: {
        username: "",
        startDate: "",
        endDate: "",
        gameName: "",
        gameResult: "",
      },
    };
  },
  created() {
    gameService.getGameDetails(this.$route.params.gameID).then((response) => {
      this.game = response;
    });
  },
};
</script>

<style>
.flex-container {
  display: flex;
}
.flex-child {
  flex: 1;
  border: 2px solid black;
}
.flex-child:first-child {
  margin-right: 20px;
}
.balanceAndStocks{
  padding-left: 10px;
  font-weight: bold;
}
.participantsAndBalances{
  padding-left: 10px;
  text-decoration: underline;
  font-weight: bold;
}
.stocksOwned{
  text-decoration: underline;
}
.backToGames{
  font-weight: bold;
}
.stockButton {
  background-color:#44c767;
	border-radius:28px;
	border:1px solid #18ab29;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:15px;
	padding:16px 31px;
	text-decoration:none;
	text-shadow:0px 1px 0px #2f6627;
  margin-right: 10px;
}
.stockButton:hover {
	background-color:#5cbf2a;
}
.stockButton:active {
	position:relative;
	top:1px;
}
</style>