import axios from "axios";
import BindingClass from "../util/bindingClass";

/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class CurrencyClient extends BindingClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getIdentity', 'updateAllCurrency', 'getAllCurrency', 'getCurrency', 'getTransaction', 'createTransaction', 'updateTransaction', 'getAllTransaction', 'getCustomer', 'createCustomer', 'updateCustomer'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;

        axios.defaults.baseURL = INVOKE_URL;
        this.client = axios;
        this.clientLoaded(this.client);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The username and phonetool url for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            // TODO auth?
            //const response = await this.client.get(`identity`);
            const data = {'username': 'Nashville Software'};
            //return response.data;
            return data;
        } catch(error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Gets the currency for the given currencyType Abbrv.
     * @param currencyType given a currencyType
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The currency's metadata.
     */
    async getCurrency(currencyType, errorCallback) {
        try {
            const response = await this.client.get(`currencies/${currencyType}`);
            return response.data.currencyModel;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getAllCurrency(errorCallback) {
        try {
            const response = await this.client.get(`currencies/`);
            return response.data.currencyModelSet;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async createTransaction(customerName, startCurrency, endCurrency, startAmount, errorCallback) {
        try {
            const response = await this.client.post(`transactions`, {
                customerName: customerName,
                startCurrency: startCurrency,
                endCurrency: endCurrency,
                startAmount: startAmount,
        });
        return response.data.transactionModel;
        } catch (error) {
        this.handleError(error, errorCallback)
        }
    }

   //call to api needs work
    async updateAllCurrency(errorCallback) {
        try {
            const response = await this.client.put(`currency`, {
                currentRate: currentRate,
                ranking: ranking,
            });
            return response.data.currency;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Gets the transaction for the given transaction ID.
     * @param transactionId given a unique transactionID
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The transaction's metadata.
     *
     **/
    async getTransaction(transactionId, errorCallback) {
        try {
            const response = await this.client.get(`transactions/${transactionId}`);
            return response.data.transaction;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getAllTransaction(errorCallback) {
            try {
                const response = await this.client.get(`transactions/`);
                return response.data.transactionModelSet;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }
    /**
         * update the transactions is shown for viewing purposes
         * @param errorCallback (Optional) A function to execute if the call fails.
         * @returns The list of songs on a playlist.
         */
    async updateTransaction(errorCallback) {
        try {
            const response = await this.client.put(`transaction/${transactionId}`, {
                isShown: isShown,
            });
            return response.data.transaction;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
    /**
     * Get the customer to show their information
     * @param customerId Unique identifier for a customer
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of songs on a playlist.
     */
    async getCustomer(customerId, errorCallback) {
        try {
            const response = await this.client.get(`customer/${customerId}`);
            return response.data.customer;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async createCustomer(name, dob, balance, errorCallback) {
        try {
            const response = await this.client.post(`customer`, {
                name: name,
                dob: dob,
                balance: balance,
            });
            return response.data.customer;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
     /**
     * update the customer balance
     * @param balance current balance will be updated
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns customer data
     */
    async updateCustomer(errorCallback) {
        try {
            const response = await this.client.put(`customer/${customerId}`, {
                balance: balance,
            });
            return response.data.customer;
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
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message)
        }
        if (errorCallback) {
            errorCallback(error);
        }
    }
}
