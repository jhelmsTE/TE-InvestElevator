<template>
  <div id="login" class="text-center">
    <form class="form-signin signInBorder" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }" class="need-account">Need an account?</router-link>
      <button type="submit">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style>
.text-center{
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: rgb(26, 17, 89);
  
}
.sr-only{
display: flex;
justify-content: center;
margin-top: 4px;
font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
font-weight: bold;
text-shadow: 2px 2px 4px black;
padding-top: 10px;
}

h1{
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  text-shadow: 2px 3px 4px honeydew;
}
.need-account{
  justify-content: center;
  display: flex;
  color: white;
  margin: 2px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

button{
  color:black;
  margin: 5px;
}
.alert.alert-success{
font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
font-weight: bold;
margin-bottom: 10px;
text-shadow: 2px 3px 4px honeydew;
}

.signInBorder{
  border: 2px solid white;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 30px;
}

.need-account{
  padding-top: 10px;
  padding-bottom: 20px;
}

</style>