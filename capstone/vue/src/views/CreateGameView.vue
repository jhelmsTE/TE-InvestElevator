<template>
  <form v-on:submit.prevent>
    <h1>Welcome</h1>
    <h3>Please create a game on the left side first. After you create a game, you can add players to that game on the right side</h3>
    <div class="flex-container">
      <div class="flex-child left-side">
        <h2 class="create-game">Create Game</h2>
        <h3 class="game-name">Game name</h3>
        <input type="text" v-model="game.gameName" />
        <h3 class="start-date">Start Date</h3>
        <input type="date" id="start-date" v-model="game.startDate" />
        <h3 class="end-date">End date</h3>
        <input type="date" id="end-date" v-model="game.endDate" />
        <br /><br />
        <button v-on:click="createGame(game)">Create Game</button>
      </div>
      <div class="flex-child right-side">
        <h2 id="invite">Add Players</h2>
        <select name="name" id="select-users" multiple>
          <option
            value="vue"
            v-for="user in users"
            v-bind:key="user.id"
            v-on:click="addUser(user.id, user.username)"
          >
            {{ user.username }}
          </option>
        </select>
        <button v-on:click="createGameResult()">Add Players</button>
      </div>
    </div>
  </form>
</template>

<script>
import AuthService from "../services/AuthService";
import GameService from "../services/GameService";
export default {
  data() {
    return {
      selectedGameResultUsers: [],
      users: [],
      user: {
        name: "",
      },
      game: {
        username: "",
        startDate: "",
        endDate: "",
        gameName: "",
        gameResult: "",
      },
      gameResults: {
        userId: "",
        gameName: "",
        userName: "",
      },
    };
  },
  created() {
    AuthService.getUsers().then((response) => {
      this.users = response.data;
    });
  },
  methods: {
    createGameResult() {
      GameService.createGameResult(this.selectedGameResultUsers);
      this.selectedGameResultUsers = [];
      this.game = {};
      this.$router.push({ name: "home" });
    },

    createGame() {
      this.game.username = this.$store.state.user.username;
      GameService.create(this.game);
    },
    addUser(id, username) {
      this.gameResults.userId = id;
      this.gameResults.userName = username;
      this.gameResults.gameName = this.game.gameName;
      this.selectedGameResultUsers.push(this.gameResults);
      this.gameResults = { userId: "", gameName: "", userName: "" };
    },
  },
};
</script>
<style>
#invite {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  text-shadow: 2px 2px 4px black;
  text-align: left;
  margin-left: 10px;
}
.checkBoxForm {
  padding-left: 10px;
  padding-bottom: 20px;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  text-shadow: 2px 2px 4px black;
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
.game-name {
  justify-content: left;
  margin-left: 10px;
}

.create-game {
  justify-content: left;
  margin-left: 10px;
}

.flex-child.left-side {
  display: flex;
  justify-content: right;
  border: limegreen solid 2px;
  flex-direction: column;
  background: rgba(26, 17, 89, 0.9);
  margin-left: 20%;
  padding-right: 10px
}
.flex-child.right-side {
  display: flex;
  justify-content: right;
  border: limegreen solid 2px;
  flex-direction: column;
  background: rgba(26, 17, 89, 0.9);
  margin-right: 20%;
  padding-right: 10px
}
.flex-container {
  display: flex;
}
</style>






