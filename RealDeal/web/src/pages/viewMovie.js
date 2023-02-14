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
         let movieId = url.searchParams.get("movieId");
         this.dataStore.set('id', id);
         this.dataStore.set('movieId', movieId);
         const singleMovie = await this.client.getMovie(id);
         this.dataStore.set('singleMovie', singleMovie);
         const reviewsModelList = await this.client.getAllMovieReviews(movieId);
         this.dataStore.set('reviewsModelList', reviewsModelList);
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
         genre += movieGenre + '\n';
         }
         }
         if(movie.cast) {
         for (let castMember of movie.cast) {
         cast += castMember + '\n';
         }
         }
         console.log(cast);
         console.log(genre);
         posterContainer.innerHTML = poster;
         titleContainer.textContent = title;
         directorContainer.textContent = director;
         genreContainer.innerText = 'Genre: '+genre;
         castContainer.textContent = 'Cast:'+cast;
    }

    async displayReviews() {

    let movieReviews = this.dataStore.get('reviewsModelList');
    if(!movieReviews) {
    return;
    }
    console.log(movieReviews);
    let reviewContainer = document.getElementById('review-container');
    let reviewText = '';
    for (let singleReview of movieReviews) {
        reviewText += '<h3>Review By: '+singleReview.username+'</h3><h2>'+singleReview.rating+'</h2><p>'+singleReview.text+'</p>'
    }
    reviewContainer.innerHTML = reviewText;
    }
}
 const main = async () => {
        const viewMovie = new ViewMovie();
        await viewMovie.mount();
 };

 window.addEventListener('DOMContentLoaded', main);