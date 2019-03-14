// Copyright 2019 Pungle

package com.pungle.sdk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PLCardResponseInfo {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("risk_level")
    @Expose
    public String riskLevel;
    @SerializedName("push_funds_enabled")
    @Expose
    public Boolean pushFundsEnabled;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("last_four_digits")
    @Expose
    public String lastFourDigits;
    @SerializedName("issuer_country_code")
    @Expose
    public String issuerCountryCode;
    @SerializedName("funding_type")
    @Expose
    public String fundingType;
    @SerializedName("fast_funds_enabled")
    @Expose
    public Boolean fastFundsEnabled;
    @SerializedName("expiry_year")
    @Expose
    public String expiryYear;
    @SerializedName("expiry_month")
    @Expose
    public String expiryMonth;
    @SerializedName("cvv_check_status")
    @Expose
    public String cvvCheckStatus;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("card_holder_type")
    @Expose
    public String cardHolderType;
    @SerializedName("brand")
    @Expose
    public String brand;
    @SerializedName("avs_check_status")
    @Expose
    public String avsCheckStatus;
    @SerializedName("address_state")
    @Expose
    public String addressState;
    @SerializedName("address_postal_code")
    @Expose
    public String addressPostalCode;
    @SerializedName("address_line2")
    @Expose
    public String addressLine2;
    @SerializedName("address_line1")
    @Expose
    public String addressLine1;
    @SerializedName("address_country")
    @Expose
    public String addressCountry;
    @SerializedName("address_city")
    @Expose
    public String addressCity;

}
