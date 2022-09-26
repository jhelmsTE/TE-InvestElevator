<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style>
.home {
  position: relative;
  top: 10rem;
  background-color: rgba(88, 23, 90, 0.9);
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>