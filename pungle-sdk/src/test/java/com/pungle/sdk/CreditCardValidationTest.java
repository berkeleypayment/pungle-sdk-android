package com.pungle.sdk;

import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CreditCardValidationTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void validation_creditCard() {

        ArrayList<String> ccArr = new ArrayList<String>();
        ccArr.add("2222405343248877");
        ccArr.add("2222990905257051");
        ccArr.add("2223007648726984");
        ccArr.add("2223577120017656");
        ccArr.add("5105105105105100");
        ccArr.add("5111010030175156");
        ccArr.add("5185540810000019");
        ccArr.add("5200828282828210");
        ccArr.add("5204230080000017");
        ccArr.add("5204740009900014");
        ccArr.add("5420923878724339");
        ccArr.add("5455330760000018");
        ccArr.add("5506900490000436");
        ccArr.add("5506900490000444");
        ccArr.add("5506900510000234");
        ccArr.add("5506920809243667");
        ccArr.add("5506922400634930");
        ccArr.add("5506927427317625");
        ccArr.add("5553042241984105");
        ccArr.add("5555553753048194");
        ccArr.add("5555555555554444");
        ccArr.add("4012888888881881");
        ccArr.add("4111111111111111");
        ccArr.add("6011000990139424");
        ccArr.add("6011111111111117");
        ccArr.add("371449635398431");
        ccArr.add("378282246310005");
        ccArr.add("30569309025904");
        ccArr.add("38520000023237");
        ccArr.add("3530111333300000");
        ccArr.add("3566002020360505");

        for (int i = 0; i < ccArr.size(); i++) {
            String num = ccArr.get(i);
            Boolean test = new PungleValidation().luhnCheckIsValid(num);
            assertEquals(true, test);
        }

        // Invalid test
        Boolean test = new PungleValidation().luhnCheckIsValid("35660020203605054");
        assertEquals(false, test);

    }

    @Test
    public void validation_ExpiryYear() {

        String year = "2017";
        Boolean yearIsValid = new PungleValidation().yearIsValid(year);

        // "Year is in the past so it should fail"
        assertFalse(yearIsValid);

        year = "2019";
        Boolean yearIsTrue = new PungleValidation().yearIsValid(year);

        // "Year is valid"
        assertTrue(yearIsTrue);

        // "Year is valid"
        year = "2020";
        yearIsTrue = new PungleValidation().yearIsValid(year);

        assertTrue(yearIsTrue);

    }

    @Test
    public void validation_Address() {

        String addressLine1 = "455 Erskine Road Ave St";
        Boolean validAddr = new PungleValidation().addressIsValid(addressLine1);

        // "Address is valid"
        assertTrue(validAddr);

        addressLine1 = "Suite 900, Blabla";
        validAddr = new PungleValidation().addressIsValid(addressLine1);
        // "Address 2 is valid"
        assertTrue(validAddr);

        addressLine1 = "Suite 900, *&Blabla";
        validAddr = new PungleValidation().addressIsValid(addressLine1);
        // "Address should fail"
        assertFalse(validAddr );

    }

    @Test
    public void validation_City() {

        String city = "Toronto";
        Boolean validCity = new PungleValidation().cityIsValid(city);
        // "Toronto City is valid"
        assertTrue(validCity);

        city = "New York";
        validCity = new PungleValidation().cityIsValid(city);
        // "New York City is valid"
        assertTrue(validCity );

        city = "Very long sentence which is not a City.";
        validCity = new PungleValidation().cityIsValid(city);
        // "City is invalid"
        assertFalse(validCity );

        city = "Invalid ^";
        validCity = new PungleValidation().cityIsValid(city);
        // "City is invalid"
        assertFalse(validCity );

    }

    @Test
    public void validation_Country() {

        String country = "CAN";
        Boolean validCountry = new PungleValidation().countryIsValid(country);
        // "Canada is a valid country"
        assertTrue(validCountry );

        country = "GBR";
        validCountry = new PungleValidation().countryIsValid(country);
        // "Germany code is a valid country"
        assertTrue(validCountry );

        country = "DE";
        validCountry = new PungleValidation().countryIsValid(country);
        // "Country code is only two characters, so incorrect"
        assertFalse(validCountry );

        country = "Not a country code -";
        validCountry = new PungleValidation().countryIsValid(country);
        // "Not a country code, so incorrect"
        assertFalse(validCountry );

    }

    @Test
    public void validation_State() {

        String state = "FL";
        Boolean stateIsValid = new PungleValidation().stateIsValid(state);
        // "FL is a valid state"
        assertTrue(stateIsValid );

        state = "SC";
        stateIsValid = new PungleValidation().stateIsValid(state);
        // "SC is a Valid state"
        assertTrue(stateIsValid );

        state = "SCC";
        stateIsValid = new PungleValidation().stateIsValid(state);
        // "SCC not a Valid state"
        assertFalse(stateIsValid );

        state = "N*";
        stateIsValid = new PungleValidation().stateIsValid(state);
        // "Not a Valid state"
        assertFalse(stateIsValid );

    }

    @Test
    public void validation_PostalCode() {

        String postalCode = "L3T2C0";
        Boolean postalCodeIsValid = new PungleValidation().postalCodeIsValid(postalCode);
        // "L3T2C0 is a valid postal code"
        assertTrue(postalCodeIsValid );

        postalCode = "33980";
        postalCodeIsValid = new PungleValidation().postalCodeIsValid(postalCode);
        // "33980 zip is a valid postal code"
        assertTrue(postalCodeIsValid );

        postalCode = "M5C 5U7";
        postalCodeIsValid = new PungleValidation().postalCodeIsValid(postalCode);
        // "Postal code with space is not a valid"
        assertFalse(postalCodeIsValid );

        postalCode = "1234567890";
        postalCodeIsValid = new PungleValidation().postalCodeIsValid(postalCode);
        // "Not a valid postal code, too long"
        assertFalse(postalCodeIsValid );

        postalCode = "M5C5U75*";
        postalCodeIsValid = new PungleValidation().postalCodeIsValid(postalCode);
        // "Not a valid postal code, invalid symbols"
        assertFalse(postalCodeIsValid );

    }


}