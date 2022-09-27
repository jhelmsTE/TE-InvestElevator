<template>
  <div class>
    <h1>Welcome to the Game Board!</h1>
    <h3>Here are the available games</h3>
    <div class = "all-cards">
    <router-link :to="{name: 'game-details', params: {id: game.id}}" class="card" v-for="game in games" v-bind:key="game.id">
      <h4>Test Game Title {{ game.title }}</h4>
    </router-link>
    </div>
  </div>
</template>

<script>
//Need to import service to get all games
export default {
  data() {
    return {
      games: ["game", "game", "game", "game", "game", "game", "game", "game", "game", "game"],
      game: {
        title: "",
      },
    };
  },
  //Need to insert a created() method to get all games
};
</script>

<style>
h1,
h3 {
  display: flex;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  text-shadow: 2px 2px 4px honeydew;
  padding-right: 12px;
  justify-content: center;
}
a:link {
  text-decoration: none;
  justify-content: center;
  align-items: center;
}
.all-cards{
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
  text-shadow: 2px 2px 4px honeydew;
  padding-right: 12px;
  background-color: white;
}
</style>