import CurrencyClient from '../api/currencyClient';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class ViewTransaction extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'showEndAmount', 'loadAllTransactions'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addRateToPage);

    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        this.loadAllTransactions();
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        this.client = new CurrencyClient();
        this.clientLoaded();
    }

    async showEndAmount() {
        const transaction = this.datastore.get('transaction');

        if(transaction == null) {
            return;
        }
        const transactionRecieved = await this.client.getTransaction(transaction);
        let endAmountHtml = ' ';
        endAmountHtml += '<p>Amount Converted: ' + transactionRecieved.endAmount + '</p>';
        document.getElementById('amountConvertedBox').innerHTML = endAmountHtml;
    }

     async loadAllTransactions() {
            const transactions = await this.client.getAllTransaction();

            let html =  `<tr>
                         <th>TransactionId</th>
                         <th>Customer Name</th>
                         <th>Start Currency</th>
                         <th>End Currency</th>
                         <th>Start Amount</th>
                         <th>End Amount</th>
                         </tr>`;
            let transaction;

            for (transaction of transactions) {
                html+= '<tr>' +
                    '<td>' + transaction.transactionId + '</td>' +
                    '<td>' + transaction.customerName + '</td>' +
                    '<td>' + transaction.startCurrency + '</td>' +
                    '<td>' + transaction.endCurrency + '</td>' +
                    '<td>' + transaction.startAmount + '</td>' +
                    '<td>' + transaction.endAmount + '</td>'
                    '</tr>';
            }

            document.getElementById('transactions-table').innerHTML = html;
        }
    }

const main = async () => {
    const viewTransaction = new ViewTransaction();
    viewTransaction.mount();
};

window.addEventListener('DOMContentLoaded', main);
