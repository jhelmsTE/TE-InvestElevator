import axios from 'axios';

export default {

    getAllGames() {
        return axios.get('/viewAllGames')
    },

    getGameDetails(id) {
        return axios.get(`/viewGame/${id}`)
    },

    getGameResultsDetails(id) {
        return axios.get(`/viewGameResults/${id}`)
    },

    create(game) {
            console.log(game)
            return axios.post('/createGame', game)
    },
    
    createGameResult(gameResult) {
        return axios.post('/createGameResult', gameResult)
    },

    updateGame(id) {
        return axios.put(`/viewGame/${id}`)
    },

    createTransaction(stocks) {
        return axios.post('/stocks/obj', stocks)
    },

    showUserStock(id) {
        return axios.get(`/stocks/${id}`)
    },

    getGameResultsDetailsByUsername() {
        return axios.get('/viewGameResultsByUsername')
    },

    getGameResultsByNotCurrentUser() {
        return axios.get('/viewGameResultsByNotCurrentUser')
    },

    endGame(id, mixedObject) {
        return axios.put(`/endGame/${id}`, mixedObject)
    }
       
}