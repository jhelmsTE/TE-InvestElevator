import axios from 'axios';

export default {

    getAllGames() {
        return axios.get('/viewAllGames')
    },

    getGameDetails(id) {
        return axios.get(`/viewGame/${id}`)
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
    }

}