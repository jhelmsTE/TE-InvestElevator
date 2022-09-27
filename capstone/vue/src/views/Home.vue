<template>
  <div class="home">
    <h1>Home</h1>
    <div id="welcome">
      Welcome!
    </div>
    <div class= "user" v-for="user in $store.state" v-bind:key="user.id">
      <p> {{user.username}}</p>
    </div>
  <router-link :to="{name: 'game-view'}" >
    Create Game
  </router-link>


  </div>

</template>

<script>
export default {
  name: "home",
  data() {
    return {
      user: {
        username:"",
      }
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



</style>
