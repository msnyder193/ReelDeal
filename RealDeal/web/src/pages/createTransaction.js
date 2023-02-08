import CurrencyClient from '../api/currencyClient';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class CreateTransaction extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'convert'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('convertButton').addEventListener('click', this.convert);
        this.client = new CurrencyClient();
        this.clientLoaded();
    }

    async convert() {
        document.getElementById('convertButton').innerText = 'Converting...';
        const customerName = document.getElementById('customerName').value;
        const startCurrency = document.getElementById('start-currency-dropdown').value;
        const endCurrency = document.getElementById('end-currency-dropdown').value;
        const startAmount = document.getElementById('startAmount').value;
        const transaction = await this.client.createTransaction(customerName, startCurrency, endCurrency, startAmount);
        this.dataStore.set('transaction', transaction);
        document.getElementById('convertButton').innerText = 'Convert';
        document.getElementById('amountConvertedBox').innerText = transaction.endAmount;
    }
}

const main = async () => {
    const createTransaction = new CreateTransaction();
    createTransaction.mount();
};

window.addEventListener('DOMContentLoaded', main);
