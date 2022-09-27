import axios from 'axios';

export default {

    getAllGames() {
        return axios.get('/viewAllGames')
    },

    getGameDetails(gameID) {
        return axios.get(`/games/${gameID}`).then((response) => {
            const games = response.data.games;
            return games.find(game => game.id == gameID);
        })
    }

}