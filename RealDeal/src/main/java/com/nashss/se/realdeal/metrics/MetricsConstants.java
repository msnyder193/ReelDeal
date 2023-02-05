package com.nashss.se.realdeal.metrics;

/**
 * Constant values for use with metrics.
 */
public class MetricsConstants {
    public static final String GETCURRENCY_CURRENCYNOTFOUND_COUNT =
            "GetCurrency.CurrencyNotFoundException.Count";

    public static final String UPDATECURRENCY_CURRENCYNOTFOUND_COUNT =
            "UpdateCurrency.CurrencyNotFoundException.Count";


    public static final String SERVICE = "Service";
    public static final String SERVICE_NAME = "CurrencyPal";
    public static final String NAMESPACE_NAME = "CurrencyPal";

    public static final String GETCUSTOMER_CUSTOMERNOTFOUND_COUNT =
            "GetCustomer_CustomerNotFoundException_Count";
    public static final String UPDATECUSTOMER_CUSTOMERNOTFOUND_COUNT =
            "UpdateCustomer_CustomerNotFoundException_Count";
    public static final String GETTRANSACTION_TRANSACTIONNOTFOUND_COUNT =
            "GetTransaction_TransactionNotFoundException_Count";
}
