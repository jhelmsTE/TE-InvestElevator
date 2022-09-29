<template>
  <div>
    <h1>Welcome</h1>
    <h2 id="invite">Invite Players</h2>
    <div class="checkBoxForm" v-for="user in users" v-bind:key="user.id">
      <input class="checkboxes" type="checkbox">
      <label for="user.id">{{ user.username }}</label>
    </div>
    <h3 class="game-name">Game name</h3>
    <h3 class="start-date">Start Date</h3>
    <input type="date" id="start-date" />

    <h3 class="end-date">End date</h3>
    <input type="date" id="end-date" />

    <br />
    <button type="submit" class="submit">Create Game</button>
  </div>
</template>

<script>
import AuthService from "../services/AuthService";

export default {
  data() {
    return {
      users: [],
      user: {
        name: "",
      },
    };
  },
  created() {
    AuthService.getUsers().then((response) => {
      this.users = response.data;
    });
  },
};
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