import ReelDealClient from '../api/reeldealclient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
class Index extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayMovies'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.displayMovies);
        this.header = new Header(this.dataStore);
    }

     async clientLoaded() {
        document.getElementById('l-container').innerText = "(Loading Movies...)";
        const moviesList = await this.client.getAllMovies();
        this.dataStore.set('moviesList', moviesList);
     }

    async mount() {
        this.header.addHeaderToPage();
        this.client = new ReelDealClient();
        await this.clientLoaded();
    }

     async displayMovies() {
     console.log("in the method");
        const moviesList = this.dataStore.get('moviesList');
        console.log("before movies" + moviesList);
        if (!moviesList) {
            return;
        }

        let movieUrl = '';
        for (let i = 0; i < 4; i++) {
        console.log("movie list: " + moviesList[0].posterUrl);
        movieUrl+= '<div class="movie-card"><div class="movie-card__cover" style="background-image: url('+ moviesList[i].posterUrl +');"></div></div>'
        }

        document.getElementById('l-container').innerHTML = movieUrl;

     }


}
 const main = async () => {
        const index = new Index();
        await index.mount();
 };

     window.addEventListener('DOMContentLoaded', main);