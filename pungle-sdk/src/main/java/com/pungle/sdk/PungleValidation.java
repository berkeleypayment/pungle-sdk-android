// Copyright 2019 Pungle

package com.pungle.sdk;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PungleValidation {

    public static final int VALIDATION_ERR_CODE = 2010;

    public boolean yearIsValid(String year){

        if ( !year.matches("^\\d{4}$") ){
            return false;
        };

        int cardYear = Integer.parseInt(year);

        Date date = new Date(); // your date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int currentYr = cal.get(Calendar.YEAR);

        if (cardYear < currentYr) { return false; }

        return true;
    }

    public boolean monthIsValid(String month) {
        return month.matches("^\\d{2}$");
    }

    public boolean cvvIsValid(String cvv) {
        return cvv.matches("^\\d{3,4}$");
    }

    public boolean addressIsValid(String address) {
        return address.matches("^[a-zA-Z0-9 ,.'\\-]{1,35}$");
    }

    public boolean cityIsValid( String city ) {
        return city.matches("^[a-zA-Z,.'\\- ]{1,25}$");
    }

    public boolean countryIsValid( String country) {
        return country.matches( "^[A-Z]{3}$");
    }

    public boolean stateIsValid(String state ) {
        return state.matches( "^[A-Z]{2}$");
    }

    public boolean postalCodeIsValid(String postalCode) {
        return postalCode.matches( "^[a-zA-Z0-9]{5,9}$");
    }

    public String cleanPostalCode(String postalCode) {
        return postalCode.replaceAll("[\\s-]+", "");
    }

    public boolean luhnCheckIsValid(String number){

        int sum = 0;
        boolean alternate = false;
        for (int i = number.length() - 1; i >= 0; i--)
        {
            int n = Integer.parseInt(number.substring(i, i + 1));
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);

    }
}
