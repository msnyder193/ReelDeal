import CurrencyClient from '../api/currencyClient';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class ViewCurrency extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addRateToPage', 'loadAllRates'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addRateToPage);

    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);

        this.loadAllRates();
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        this.client = new CurrencyClient();
        this.clientLoaded();
    }

    async addRateToPage() {
        const currency = this.dataStore.get('currency');

        if(currency == null) {
            return;
        }
        console.log("currency" + currency);
        const currencyRate = await this.client.getCurrency(currency);
        console.log("currencyRate " + JSON.stringify(currencyRate));

        let rateHtml = ' ';

        rateHtml+= '<p>ranking: ' + currencyRate.ranking + '</p>';
        rateHtml+= '<p>name: ' + currencyRate.currencyName + '</p>';
        rateHtml+= '<p>abv: ' + currencyRate.currencyAbrv + '</p>';
        rateHtml+= '<p>rate: ' + currencyRate.currentRate + '</p>';

        document.getElementById('display-rate').innerHTML = rateHtml;

    }

    async loadAllRates() {
        const currencies = await this.client.getAllCurrency();

        let html =  `<tr>
                     <th>Ranking</th>
                     <th>Currency Name</th>
                     <th>Currency Abrv</th>
                     <th>Rates</th>
                     </tr>`;
        let currency;

        for (currency of currencies) {
            html+= '<tr>' +
                '<td>' + currency.ranking + '</td>' +
                '<td>' + currency.currencyName + '</td>' +
                '<td>' + currency.currencyAbrv + '</td>' +
                '<td>' + currency.currentRate + '</td>'
                '</tr>';
        }

        document.getElementById('rates-table').innerHTML = html;
    }
}

const main = async () => {
    const viewCurrency = new ViewCurrency();
    viewCurrency.mount();
};

window.addEventListener('DOMContentLoaded', main);
