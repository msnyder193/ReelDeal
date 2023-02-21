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
        const moviesList = await this.client.getAllMovies();
        this.dataStore.set('moviesList', moviesList);
     }

    async mount() {
        this.header.addHeaderToPage();
        this.client = new ReelDealClient();
        await this.clientLoaded();
    }

     async displayMovies() {
     let add = document.getElementById('add-reel');
     add.addEventListener('click', () => {
     window.location.href = 'createMovie.html'
     });
        const moviesList = this.dataStore.get('moviesList');
        if (!moviesList) {
            return;
        }

        let container = document.getElementById('movie-container');
        for (let i = 0; i < 4; i++) {
        console.log("movie list: " + moviesList[0].posterUrl);
        let movieUrl= '<img src=' + moviesList[i].posterUrl + ' alt="Movie 1"><h2>'+ moviesList[i].title +'</h2>'
        let button = document.createElement("div");
        button.innerHTML = movieUrl;
        button.classList.add("card");
        button.addEventListener("click", () => {
        window.location.href = 'viewMovie.html?movieId=' + moviesList[i].id;
         });
        container.append(button);
        }

     }


}
 const main = async () => {
        const index = new Index();
        await index.mount();
 };

     window.addEventListener('DOMContentLoaded', main);