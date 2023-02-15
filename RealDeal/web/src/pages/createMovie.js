import ReelDealClient from '../api/reeldealclient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
class Index extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'submitForm'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.displayMovies);
        this.header = new Header(this.dataStore);
    }

     async clientLoaded() {

     }

    async mount() {
        this.header.addHeaderToPage();
        this.client = new ReelDealClient();
        await this.clientLoaded();
    }

    async submitForm() {
    const title = document.getElementById('movieTitle');
    const description = document.getElementById('movieDescription');
    const releaseDate = document.getElementById('ReleaseDate');
    const posterUrl = document.getElementById('posterUrl');
    const cast = document.getElementById('cast');
    const director = document.getElementById('director');
    const genres = '';
    const text = document.getElementById('comments');
    const rating = 0;
    const submit = document.getElementById('submit');

    submit.addEventListener('click', (event) => {
        event.preventDefault();
        title.value;
        description.value;
        releaseDate.value;
        posterUrl.value;
        cast.value;
        director.value;
        genres.getCheckedBoxValue();
        text.value;
        rating.getRating();
        this.client.createReview(text,rating);
        this.client.createMovie(title,description,releaseDate,posterUrl,genres,cast,director);
    })
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
      var l10 = document.getElementById("hisory");
      var l11 = document.getElementById("crime");

      var res=" ";
      if (l1.checked == true){
        var pl1 = document.getElementById("action").value;
        res = pl1 + ",";
      }
      else if (l2.checked == true){
        var pl2 = document.getElementById("comedy").value;
        res = res + pl2 + ",";
      }
      else if (l3.checked == true){
      document.write(res);
        var pl3 = document.getElementById("drama").value;
        res = res + pl3 + ",";
      }
      else if (l4.checked == true){
        var pl4 = document.getElementById("horror").value;
        res = res + pl4 + ",";
      }
      else if (l5.checked == true){
        var pl5 = document.getElementById("mystery").value;
        res = res + pl5 + ",";
      }
      else if (l6.checked == true){
        var pl6 = document.getElementById("romance").value;
        res = res + pl6;
      } else if (l7.checked == true){
        var pl7 = document.getElementById("thriller").value;
        res = pl7 + ",";
      }
      else if (l8.checked == true){
        var pl8 = document.getElementById("scifi").value;
        res = res + pl8 + ",";
      }
      else if (l9.checked == true){
      document.write(res);
        var pl9 = document.getElementById("western").value;
        res = res + pl9 + ",";
      }
      else if (l10.checked == true){
        var pl10 = document.getElementById("history").value;
        res = res + pl10 + ",";
      }
      else if (l11.checked == true){
        var pl11 = document.getElementById("crime").value;
        res = res + pl11 + ",";
      }
      else {
      return null;
      }
      return res;
        }

        getRating() {
            var rating = document.getElementByName("rating");
            var res = 0;
            if (!rating.checked) {
            return;
            }
            for (var rate of rating.checked) {
                if(parseInt(rate.value) > res) {
                    res = parseInt(rate.value);
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