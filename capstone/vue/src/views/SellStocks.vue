<template>
  <div>
    <router-link class="return" :to="{ name: 'game-details' }">Back to Game Details</router-link>
    <h1>Sell Stocks Here!</h1>
    <p>Select stock you wish to sell:</p>
    <input type="text" v-model="search" placeholder="Search" />
    <div class="flex-container" v-for="stock in filteredStocks" :key="stock.id">
      <div class="stockName">{{ stock.name }}</div>
    </div>
    <div class="stockNumber">
      <p>How many shares would you like to sell?</p>
      <input v-model.number="number" placeholder="Insert number" />
    </div>
    <div>
      <router-link class="button" :to="{ name: 'game-details' }">Confirm</router-link>
    </div>
    <div>
      <router-link class="button" :to="{ name: 'game-details' }">Cancel Transaction</router-link>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      number: "",
      stocks: [
        { id: 1, name: "Apple" },
        { id: 2, name: "Amazon" },
        { id: 3, name: "Meta" },
        { id: 4, name: "Google" },
      ],
      search: "",
    };
  },
  computed: {
    filteredStocks() {
      return this.stocks.filter((response) => {
        // return true if the product should be visible

        // in this example we just check if the search string
        // is a substring of the product name (case insensitive)
        return (
          response.name.toLowerCase().indexOf(this.search.toLowerCase()) != -1
        );
      });
    },
  },
}
</script>

<style>
.button {
  background-color:#44c767;
	border-radius:28px;
	border:1px solid #18ab29;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:12px;
	padding:16px 31px;
	text-decoration:none;
	text-shadow:0px 1px 0px #2f6627;
  margin-right: 10px;
}
.button:hover {
	background-color:#5cbf2a;
}
.button:active {
	position:relative;
	top:1px;
}
.return{
  text-decoration: underline;
}
</style>