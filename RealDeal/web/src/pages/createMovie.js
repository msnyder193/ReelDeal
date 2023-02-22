import ReelDealClient from '../api/reeldealclient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
class CreateMovie extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'submitForm' ], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

     async clientLoaded() {

     }

    async mount() {
        this.header.addHeaderToPage();
        this.client = new ReelDealClient();
        document.getElementById('submit').addEventListener('click', this.submitForm);
        await this.clientLoaded();

    }

    async submitForm() {
    const title = document.getElementById('movieTitle').value;
    const description = document.getElementById('movieDescription').value;
    const releaseDate = document.getElementById('releaseDate').value;
    const posterUrl = document.getElementById('posterUrl').value;
    const cast = document.getElementById('cast').value.split(',');
    const director = document.getElementById('director').value;
    const genres = this.getCheckBoxValue();
    const text = document.getElementById('comments').value;
    const rating = this.getRating();
    const submit = document.getElementById('submit');

    if (!title || !description || !releaseDate || !posterUrl || !cast || !director || !genres || !text || !rating) {
        alert("please fill in all required fields");
        return;
    }

    console.log(title, description, releaseDate, posterUrl, director, cast, genres);
    console.log(text, rating, submit);


    const singleMovie = await this.client.createMovie(title, description, releaseDate, posterUrl, genres, cast, director);
    this.dataStore.set('singleMovie', singleMovie);
    const movie = this.dataStore.get('singleMovie')
    if(movie) {
        await this.client.createReview(text, rating, movie.id);
        const submit = document.getElementById('submit');
        submit.disabled = true;
        submit.textContent = 'Saving...';
        submit.style.background = 'grey';
    }
    }

    getCheckBoxValue() {
      var l1 = document.getElementById("action");
      var l2 = document.getElementById("comedy");
      var l3 = document.getElementById("drama");
      var l4 = document.getElementById("horror");
      var l5 = document.getElementById("mystery");
      var l6 = document.getElementById("romance");
      var l7 = document.getElementById("thriller");
      var l8 = document.getElementById("scifi");
      var l9 = document.getElementById("western");
      var l10 = document.getElementById("history");
      var l11 = document.getElementById("crime");

      var res= [];
      if (l1.checked){
        var pl1 = document.getElementById("action").id;
        res.push(pl1);
      }
       if (l2.checked){
        var pl2 = document.getElementById("comedy").id;
        res.push(pl2);
      }
      if (l3.checked){
        var pl3 = document.getElementById("drama").id;
        res.push(pl3);
      }
      if (l4.checked){
        var pl4 = document.getElementById("horror").id;
        res.push(pl4);
      }
      if (l5.checked){
        var pl5 = document.getElementById("mystery").id;
        res.push(pl5);
      }
      if (l6.checked){
        var pl6 = document.getElementById("romance").id;
        res.push(pl6);
      } if (l7.checked){
        var pl7 = document.getElementById("thriller").id;
        res.push(pl7);
      }
      if (l8.checked){
        var pl8 = document.getElementById("scifi").id;
        res.push(pl8);
      }
      if (l9.checked){
        var pl9 = document.getElementById("western").id;
        res.push(pl9);
      }
      if (l10.checked){
        var pl10 = document.getElementById("history").id;
        res.push(pl10);
      }
      if (l11.checked){
        var pl11 = document.getElementById("crime").id;
        res.push(pl11);
      }
      if (res.length == 0){
      return null;
      }
      return res;
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
        const createMovie = new CreateMovie();
        await createMovie.mount();
 };

     window.addEventListener('DOMContentLoaded', main);