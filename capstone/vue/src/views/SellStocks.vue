<template>
  <div>
    <router-link class="return" :to="{ name: 'game-details' }">Back to Game Details</router-link>
    <h1>Sell Stocks Here!</h1>
    <div class="sellStocks">
      <p>Select stock you wish to sell: {{ stocks.ticker }}</p>
      <select v-model="stocks.ticker">
        <option value="" disabled selected hidden>Select a company</option>
        <option value="AAPL">Apple</option>
        <option value="MSFT">Microsoft</option>
        <option value="GOOG">Alphabet (Google)</option>
        <option value="AMZN">Amazon</option>
        <option value="TSLA">Tesla</option>
        <option value="BRK-B">Berkshire Hathaway</option>
        <option value="UNH">UnitedHealth</option>
        <option value="JNJ">Johnson & Johnson</option>
        <option value="V">Visa</option>
        <option value="XOM">Exxon Mobil</option>
        <option value="META">Meta Platforms (Facebook)</option>
        <option value="WMT">Walmart</option>
        <option value="LLY">Eli Lilly</option>
        <option value="JPM">JPMorgan & Chase</option>
        <option value="PG">Procter & Gamble</option>
        <option value="NVDA">NVIDIA</option>
        <option value="HD">Home Depot</option>
        <option value="CVX">Chevron</option>
        <option value="MA">Mastercard</option>
        <option value="ABBV">AbbVie</option>
        <option value="PFE">Pfizer</option>
        <option value="BAC">Bank of America</option>
        <option value="KO">Coca-Cola</option>
        <option value="PEP">Pepsico</option>
        <option value="MRK">Merck</option>
        <option value="COST">Costco</option>
        <option value="TMO">Thermo Fisher Scientific</option>
        <option value="DHR">Danaher</option>
        <option value="AVGO">Broadcom</option>
        <option value="DIS">Walt Disney</option>
      </select>
      <!-- Save for later, just in case
        <select class="search dropdown" v-model="selectedStock">
        <option v-for="stock in stocks" v-bind:key="stock.id">
          {{ stock.name }}
        </option>
      </select> -->
      <div class="stockNumber">
        <p>
        <label for="quantity">How many shares would you like to sell? (Whole numbers only; no fractional shares allowed)</label>
        </p>
        <input type="number" id="quantity" name="quantity" min="1" max="999999999" placeholder="Insert number" 
          v-model="stocks.sharesSold"/>
      </div>
    </div>
    <div class="buttonContainer">
      <div>
    <button class="button" v-on:click="sellStocks()"  
          >Confirm</button>      
          </div>
      <div>
        <router-link class="button" :to="{ name: 'game-details' }">Cancel Transaction</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import gameService from "../services/GameService"

export default {
  data() {
    return {
      number: "",
      selectedStock: "",
      selectedCompany: "",
      stocks: {
        username: "",
        gameId: "",
        ticker: "",
        stockPrice: "",
        sharesPurchased: 0,
        sharesPerTicker: "",
        companyName: "",
        sharesSold: ""
      },
      search: "",
    };
  },
  methods:{
    sellStocks(){
      this.stocks.gameId = this.$store.state.gameDetailId
      console.log(this.stocks.gameId)
      this.stocks.username = this.$store.state.user.username
      console.log(this.stocks.username)
      gameService.createTransaction(this.stocks)
      console.log(this.stocks.gameId)
      this.$router.push({name: 'game-details', params:{id: this.$store.state.gameDetailId}})
    }
  }
};
</script>

<style>
.sellStocks {
  display: flex;
  flex-direction: column;
  padding-left: 20%;
  width: 25%;
}
.buttonContainer{
  display: flex;
  padding-left: 20%;
}
.button {
  background-color: #44c767;
  border-radius: 28px;
  border: 1px solid #18ab29;
  padding: 16px 31px;
  display: inline-block;
  cursor: pointer;
  color: #ffffff;
  font-family: Arial;
  font-size: 15px;
  text-decoration: none;
  text-shadow: 0px 1px 0px #2f6627;
  margin-right: 10px;
  margin-top: 30px;
}
.button:hover {
  background-color: #5cbf2a;
}
.button:active {
  position: relative;
  top: 1px;
}
.return {
  text-decoration: underline;
  font-weight: bold;
  margin-left: 10px;
}
p {
  font-weight: bold;
  margin-top: 30px;
}
</style>