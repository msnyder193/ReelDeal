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
export default class ReelDealService extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'getAllWorkoutPlans', 'getWorkoutPlan', 'getAllExercises', 'getExercise', 'createExercise', 'updateExercise'];
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

    /**
     * Get all the workout plans on the list.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of workout plans.
     */

     async getAllWorkoutPlans(errorCallback) {
            try {
                const response = await this.axiosClient.get(`workoutplan`);
                return response.data.workoutPlanList;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
      }


    /**
       * Gets the workout plan info for the given ID.
       * @param workoutPlanId Unique identifier for an workout
       * @param errorCallback (Optional) A function to execute if the call fails.
       * @returns The workout plan's data.
       */
      async getWorkoutPlan(workoutPlanId, errorCallback) {
          try {
              const response = await this.axiosClient.get(`workoutplan/${workoutPlanId}`);
              return response.data.workoutPlan;
          } catch (error) {
              this.handleError(error, errorCallback)
          }
      }


    /**
     * Get all the exercises on the list.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of exercises.
     */

     async getAllExercises(errorCallback) {
            try {
                const response = await this.axiosClient.get(`exercises`);
                return response.data.exerciseList;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
      }

      /**
         * Gets the exercise info for the given ID.
         * @param exerciseId Unique identifier for an workout
         * @param errorCallback (Optional) A function to execute if the call fails.
         * @returns The exercise's data.
         */
        async getExercise(exerciseId, errorCallback) {
            try {
                const response = await this.axiosClient.get(`exercises/${exerciseId}`);
                return response.data.exercise;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

    /**
     * Create a new exercise.
     * @param payload object with exercise data
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The exercise that has been created.
     */
    async createExercise(payload, errorCallback) {
        try {
            const response = await this.axiosClient.post(`exercises`, payload);
            return response.data.exercise;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }


    async updateExercise(exercise, errorCallback) {
         try {
             const response = await this.axiosClient.put(`exercises/${exercise.exerciseId}`, exercise);
             return response.data.exerciseModel;
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