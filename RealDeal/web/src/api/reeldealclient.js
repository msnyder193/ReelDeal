import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the ReelDealService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class ReelDealClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'createMovie',
         'createReview', 'getAllMovies', 'getAllMovieReviews', 'getMovie', 'getReview', 'updateMovie', 'deleteReview', 'updateReview'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

     async getAllMovies(errorCallback) {
            try {
                const response = await this.axiosClient.get(`movies`);
                return response.data.moviesModelList;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
      }


      async getAllMovieReviews(movieId, errorCallback) {
          try {
              const response = await this.axiosClient.get(`reviews/allmovies/${movieId}`);
              return response.data.reviewsModelList;
          } catch (error) {
              this.handleError(error, errorCallback)
          }
      }


     async getMovie(id, errorCallback) {
            try {
                const response = await this.axiosClient.get(`movies/${id}`);
                return response.data.singleMovie;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
      }

        async getReview(id, errorCallback) {
            try {
                const response = await this.axiosClient.get(`reviews/${id}`);
                return response.data.reviews;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

    async createMovie(title, description, releaseDate, posterUrl,
    genres, cast, director, errorCallback) {
        try {
            const response = await this.axiosClient.post(`movies`, {
                title: title,
                description: description,
                releaseDate: releaseDate,
                posterUrl: posterUrl,
                genres: genres,
                cast: cast,
                director: director
            });
            console.log("movie response", response.data);
            return response.data.moviesModel;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async deleteReview(id, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete reviews.")
            const response = await this.axiosClient.delete(`reviews/${id}`,
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.reviews;
        } catch (error) {
//            window.alert("You must be the owner to delete this review. Redirecting ..");
//            window.location.href = `/viewMovie.html`;
            this.handleError(error, errorCallback)
        }
    }

    async createReview(text,rating, movieId, errorCallback) {
         try {
             const token = await this.getTokenOrThrow("cant do if no log in");
             const response = await this.axiosClient.post(`reviews`, {
                text: text,
                rating: rating,
                movieId: movieId
             }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
             });
             return response.data.reviews;
         } catch (error) {
             this.handleError(error, errorCallback)
         }
     }

    async updateMovie(movies, errorCallback) {
            try {
                const response = await this.client.put(`movies/${movies.id}`, movies);
                return response.data.moviesModel;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }


     async updateReview(id, text, rating, errorCallback) {
             try {
                 const response = await this.axiosClient.put(`reviews`, {
                    id: id,
                    text: text,
                    rating: rating
                 });
                 return response.data.reviews;
             } catch (error) {
                 this.handleError(error, errorCallback)
             }
         }
    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }

}