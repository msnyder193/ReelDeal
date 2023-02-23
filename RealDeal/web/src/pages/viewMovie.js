import ReelDealClient from '../api/reeldealclient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
class ViewMovie extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayMovie', 'displayReviews','deleteReview', 'populateUpdateForm', 'showMovieReview', 'submitMovieReview', 'getRating', 'addMovieReview'], this);
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
    let bodyText = this.populateAddForm();
    let reviewDiv = document.getElementById('addReview');
    reviewDiv.innerHTML = bodyText;
    for (let singleReview of movieReviews) {
        reviewText += '<h3>Review By: '+singleReview.username+'</h3><h2>'+singleReview.rating+'</h2><p>'+singleReview.text+'</p>';
        const identity = await this.client.getIdentity();
        if (singleReview.username === identity.email) {
            reviewText += '<button class="delete-btn" id="'+singleReview.id+'">Delete</button>';
            reviewText += this.populateUpdateForm(singleReview.id);
        }


       reviewContainer.innerHTML = reviewText;
       const updateBtn = document.querySelectorAll('.update-btn');
       for (let elementN of updateBtn) {
           elementN.addEventListener('click',
           this.showMovieReview);
       }
       const submit = document.querySelectorAll('.submit-btn')
       for (let elementN of submit) {
           elementN.addEventListener('click',
           this.submitMovieReview);
       }
       const deleteBtn = document.querySelectorAll('.delete-btn');
       for (let elementN of deleteBtn) {
            elementN.addEventListener('click',
            this.deleteReview);
        }
    }
       const addReviewButton = document.getElementById('add-review');
       addReviewButton.addEventListener('click', this.showReview);
       const addSubmit = document.getElementById('submitAddButton');
       addSubmit.addEventListener('click', this.addMovieReview);
    }

    async deleteReview(event) {
        confirm('Are you sure you want to delete');
        await this.client.deleteReview(event.target.id);
        const movieId = this.dataStore.get('movieId');
        const reviewsModelList = await this.client.getAllMovieReviews(movieId);
        this.dataStore.set('reviewsModelList', reviewsModelList);
    }

    populateUpdateForm(id) {
        // Create the "Write a review" button
        const button = document.createElement("button");
        button.setAttribute("class", "update-btn")
        button.setAttribute("id", `update-btn${id}`);
        button.textContent = "Update Review";
        button.onclick = this.showMovieReview;

        // Create the movie review popup
        const popup = document.createElement("div");
        popup.setAttribute("id", `popup${id}`);
        popup.style.display = "none";
        popup.style.position = "absolute";
        popup.style.top = "50%";
        popup.style.left = "50%";
        popup.style.transform = "translate(-50%, -50%)";
        popup.style.backgroundColor = "white";
        popup.style.padding = "20px";
        popup.style.borderRadius = "10px";
        popup.style.boxShadow = "0px 0px 10px rgba(0, 0, 0, 0.2)";
        document.body.appendChild(popup);

        // Add the "Write a review" heading to the popup
        const heading = document.createElement("h2");
        heading.textContent = "Update a review";
        popup.appendChild(heading);

        // Add the review text area to the popup
        const reviewText = document.createElement("textarea");
        reviewText.setAttribute("id", `reviewText${id}`);
        reviewText.style.width = "100%";
        reviewText.style.height = "100px";
        popup.appendChild(reviewText);

        // Add the rating options to the popup
        const ratingContainer = document.createElement("div");
        for (let i = 1; i <= 5; i++) {
          const rating = document.createElement("input");
          rating.type = "radio";
          rating.name = "rating";
          rating.value = i;
          rating.id = "rating" + i;
          const label = document.createElement("label");
          label.textContent = i;
          label.htmlFor = "rating" + i;
          ratingContainer.appendChild(rating);
          ratingContainer.appendChild(label);
        }
        popup.appendChild(ratingContainer);

        // Add the submit button to the popup
        const submitButton = document.createElement("button");
        submitButton.setAttribute("id", `submitButton${id}`);
        submitButton.setAttribute("class", "submit-btn");
        submitButton.textContent = "Submit";
        submitButton.onclick = this.submitMovieReview;
        popup.appendChild(submitButton);

        return button.outerHTML + popup.outerHTML;
    }

    populateAddForm() {
        // Create the "Write a review" button
        const button = document.getElementById("add-review")
        button.onclick = this.showReview;

        // Create the movie review popup
        const popup = document.createElement("div");
        popup.setAttribute("id", `popup`);
        popup.style.display = "none";
        popup.style.position = "absolute";
        popup.style.top = "50%";
        popup.style.left = "50%";
        popup.style.transform = "translate(-50%, -50%)";
        popup.style.backgroundColor = "white";
        popup.style.padding = "20px";
        popup.style.borderRadius = "10px";
        popup.style.boxShadow = "0px 0px 10px rgba(0, 0, 0, 0.2)";
        document.body.appendChild(popup);

        // Add the "Write a review" heading to the popup
        const heading = document.createElement("h2");
        heading.textContent = "Add a New Review";
        popup.appendChild(heading);

        // Add the review text area to the popup
        const reviewText = document.createElement("textarea");
        reviewText.setAttribute("id", "reviewText");
        reviewText.style.width = "100%";
        reviewText.style.height = "100px";
        popup.appendChild(reviewText);

        // Add the rating options to the popup
        const ratingContainer = document.createElement("div");
        for (let i = 1; i <= 5; i++) {
          const rating = document.createElement("input");
          rating.type = "radio";
          rating.name = "rating";
          rating.value = i;
          rating.id = "rating" + i;
          const label = document.createElement("label");
          label.textContent = i;
          label.htmlFor = "rating" + i;
          ratingContainer.appendChild(rating);
          ratingContainer.appendChild(label);
        }
        popup.appendChild(ratingContainer);

        // Add the submit button to the popup
        const submitButton = document.createElement("button");
        submitButton.setAttribute("id", "submitAddButton");
        submitButton.setAttribute("class", "submitAdd-btn");
        submitButton.textContent = "Submit";
        submitButton.onclick = this.addMovieReview;
        popup.appendChild(submitButton);

        return button.outerHTML + popup.outerHTML;
    }

        // Show the movie review popup
    async showMovieReview(event) {
    console.log(event.target.id);
      const id = event.target.id.substr(10);
      console.log(id);
      const popup = document.getElementById(`popup${id}`);
      popup.style.display = "block";
    }

    async showReview() {
        const popup = document.getElementById('popup');
        popup.style.display = "block";
    }

        // Submit the movie review
    async submitMovieReview(event) {
      const id = event.target.id.substr(12);
      const popup = document.getElementById(`popup${id}`);
      // Get the review text
      const review = document.getElementById(`reviewText${id}`).value;
      // Get the rating value
      const rating = this.getRating();
      //get id from event parent node
      // Do something with the review and rating (e.g. send it to a server)
      console.log("Review text:", review);
      console.log("Rating:", rating);
      // Close the popup
      await this.client.updateReview(id , review, rating);
      popup.style.display = "none";
      const movieId = this.dataStore.get('movieId');
      const reviewsModelList = await this.client.getAllMovieReviews(movieId);
      this.dataStore.set('reviewsModelList', reviewsModelList);

    }

    async addMovieReview() {
      const movieId = this.dataStore.get('movieId');
      const popup = document.getElementById("popup");
      // Get the review text
      const review = document.getElementById("reviewText").value;
      // Get the rating value
      const rating = this.getRating();
      //get id from event parent node
      // Do something with the review and rating (e.g. send it to a server)
      console.log("Review text:", review);
      console.log("Rating:", rating);
      // Close the popup
      await this.client.createReview(review, rating, movieId);
      popup.style.display = "none";
      const reviewsModelList = await this.client.getAllMovieReviews(movieId);
      this.dataStore.set('reviewsModelList', reviewsModelList);
    }

    getRating() {
        var rating = document.getElementsByName("rating");
        var res = 0;
        if (rating.length == 0) {
        return;
        }
        for (var rate of rating) {
            if (rate.checked) {
                if(parseInt(rate.value) > res) {
                    res = parseFloat(rate.value);
                }
            }
        }
        return res;
    }
}

 const main = async () => {
        const viewMovie = new ViewMovie();
        await viewMovie.mount();
 };

 window.addEventListener('DOMContentLoaded', main);