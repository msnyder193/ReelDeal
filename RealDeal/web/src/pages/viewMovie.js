import ReelDealClient from '../api/reeldealclient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
class ViewMovie extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayMovie', 'displayReviews'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.displayMovie);
        this.dataStore.addChangeListener(this.displayReviews);
        this.header = new Header(this.dataStore);
    }

     async clientLoaded() {
         let url = new URL(window.location.href);
         let id = url.searchParams.get("movieId");
         this.dataStore.set('id', id);
         const singleMovie = await this.client.getMovie(id);
         this.dataStore.set('singleMovie', singleMovie);
         const movieReviews = await this.client.getAllMovieReviews(id);
         this.dataStore.set('movieReviews', movieReviews);
     }

    async mount() {
        this.header.addHeaderToPage();
        this.client = new ReelDealClient();
        await this.clientLoaded();
    }

    async displayMovie() {
         let movie = this.dataStore.get('singleMovie');

         if(!movie) {
            return;
         }

         let container = document.getElementById("movie-container");
         let posterContainer = document.getElementById("poster");
         let detailsContainer = document.getElementById("details");
         let genreContainer = document.getElementById("genre");
         let castContainer = document.getElementById("cast");
         let titleContainer = document.getElementById("title");
         let directorContainer = document.getElementById("director");
         let poster = '<img src='+movie.posterUrl+' alt="Movie Poster"></div>';
         let title = movie.title
         let director = movie.director;
         let genre = '';
         let cast = '';
         if(movie.genres) {
         for (let movieGenre of movie.genres) {
         genreContainer.textContent += movieGenre + '\n';
         }
         }
         if(movie.cast) {
         for (let castMember of movie.cast) {
         castContainer.textContent += castMember + '\n';
         }
         }
         posterContainer.innerHTML += poster;
         titleContainer.textContent += title;
         directorContainer.textContent += director;
    }

    async displayReviews() {
                <h2>Review by John Doe</h3>
                <p>This movie was amazing! I highly recommend it to anyone who loves action films. The cast was fantastic and the special effects were top-notch. 9/10 would watch again.</p>
    }
}
 const main = async () => {
        const viewMovie = new ViewMovie();
        await viewMovie.mount();
 };

 window.addEventListener('DOMContentLoaded', main);