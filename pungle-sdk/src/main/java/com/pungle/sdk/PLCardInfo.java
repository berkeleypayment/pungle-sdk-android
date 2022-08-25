// Copyright 2019 Pungle

package com.pungle.sdk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PLCardInfo {

    @SerializedName("address_line2")
    @Expose
    private String addressLine2;
    @SerializedName("address_state")
    @Expose
    private String addressState;
    @SerializedName("address_line1")
    @Expose
    private String addressLine1;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("address_postal_code")
    @Expose
    private String addressPostalCode;
    @SerializedName("address_city")
    @Expose
    private String addressCity;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("expiry_month")
    @Expose
    private String expiryMonth;
    @SerializedName("address_country")
    @Expose
    private String addressCountry;
    @SerializedName("expiry_year")
    @Expose
    private String expiryYear;
    @SerializedName("cvv")
    @Expose
    private String cvv;

    public PLCardInfo( String name, String number, String brand, String cvv, String addressLine1, String addressLine2,
                       String addressState, String addressPostalCode, String addressCountry, String addressCity,
                       String expiryMonth, String expiryYear) {

        this.addressLine2 = addressLine2;
        this.addressState = addressState;
        this.addressLine1 = addressLine1;
        this.number = number;
        this.brand = brand;

        this.addressPostalCode = new PungleValidation().cleanPostalCode(addressPostalCode);

        this.addressCity = addressCity;
        this.name = name;
        this.expiryMonth = expiryMonth;
        this.addressCountry = addressCountry;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
    }

    public ArrayList<PungleError> validate() {

        ArrayList<PungleError> errors = new ArrayList<PungleError>();

        PungleValidation plValidation = new PungleValidation();

//         if ( !plValidation.yearIsValid(this.expiryYear)  ){
//             PungleError error = new PungleError( ErrorType.validation, ValidationError.expiryYear,
//                     "Expiry year incorrect. Either the year is in the past or the number is not well formatted", PungleValidation.VALIDATION_ERR_CODE);
//             errors.add( error );
//         }

//         if (!plValidation.addressIsValid(this.addressLine1) ) {
//             PungleError error = new PungleError( ErrorType.validation, ValidationError.addressLine1,
//                     "Address line 1 has invalid symbols or it's too long", PungleValidation.VALIDATION_ERR_CODE);
//             errors.add( error );
//         }

//         if (!plValidation.addressIsValid(this.addressLine1) ) {
//             PungleError error = new PungleError( ErrorType.validation, ValidationError.addressLine2,
//                     "Address line 2 has invalid symbols or it's too long", PungleValidation.VALIDATION_ERR_CODE);
//             errors.add( error );
//         }

//         if (!plValidation.cityIsValid(this.addressCity) ) {
//             PungleError error = new PungleError(ErrorType.validation, ValidationError.city,
//                     "City has invalid symbols or it's too long", PungleValidation.VALIDATION_ERR_CODE);
//             errors.add( error );
//         }

//         if (!plValidation.countryIsValid(this.addressCountry) ) {
//             PungleError error = new PungleError( ErrorType.validation, ValidationError.country,
//                      "Country is too long or it has invalid symbols", PungleValidation.VALIDATION_ERR_CODE);
//             errors.add( error );

//         }

//         if (!plValidation.stateIsValid(this.addressState) ) {
//             PungleError error = new PungleError( ErrorType.validation, ValidationError.state,
//                      "State is too long or it has invalid symbols", PungleValidation.VALIDATION_ERR_CODE);
//             errors.add( error );

//         }

//         if (!plValidation.postalCodeIsValid(this.addressPostalCode) ) {
//             PungleError error = new PungleError( ErrorType.validation, ValidationError.postalCode,
//                     "Postal Code is missing characters ot it's too long", PungleValidation.VALIDATION_ERR_CODE);
//             errors.add( error );
//         }

//         if (!plValidation.monthIsValid(this.expiryMonth) ) {
//             PungleError error = new PungleError( ErrorType.validation, ValidationError.expiryMonth,
//                     "Expiry month must be 2 digits long", PungleValidation.VALIDATION_ERR_CODE);
//             errors.add( error );
//         }

//         if (!plValidation.cvvIsValid(this.cvv) ) {
//             PungleError error = new PungleError( ErrorType.validation, ValidationError.cvvNumber,
//                     "CVV code must be 3 to 4 digits long", PungleValidation.VALIDATION_ERR_CODE);
//             errors.add( error );
//         }

        if (!plValidation.luhnCheckIsValid(this.number) ) {
            PungleError error = new PungleError( ErrorType.validation, ValidationError.cardNumber,
                    "Credit Card number is invalid", PungleValidation.VALIDATION_ERR_CODE);
            errors.add( error );
        }

        return errors;

    }

}
