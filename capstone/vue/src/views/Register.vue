<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="username-line">
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
      </div>
      <div class="password-line">
        <label for="password" class="sr-only">Password</label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
      </div>
      <div class="confirm-line">
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
      </div>
      <div class="create-line">
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      </div>
      <div class="account-line">
      <router-link :to="{ name: 'login' }" class="have-account"
        >Have an account?</router-link
      >
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
#register {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: rgb(26, 17, 89);
}
.form-register{
  border: 2px solid white;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 30px;
}
.username-line{
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}
.password-line{
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}
.confirm-line{
  display: flex;
  justify-content: right;
  margin-bottom: 20px;
}
.create-line{
  display: flex;
  justify-content: center;
  margin-bottom: 50px;
}
.account-line{
  display: flex;
  justify-content: center;
}
.sr-only {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
  text-shadow: 2px 2px 4px black;
  padding-right: 12px;
}
h1 {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  text-shadow: 2px 3px 4px honeydew;
  text-align: center;
}
.have-account {
  color: white;
  margin: 2px;
}
button {
  color: black;
  margin: 5px;
}
</style>
