package com.sdktests.pungle.punglesdkwithtests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.pungle.sdk.ErrorType;
import com.pungle.sdk.PLCardInfo;
import com.pungle.sdk.PLCardResponseInfo;
import com.pungle.sdk.PLRequestCallback;
import com.pungle.sdk.Pungle;
import com.pungle.sdk.PungleError;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * This App and code is just used to test the SDK module.
         */
        PLCardInfo creditCard = new PLCardInfo("John Smith", "2222405343248877",
                "visa", "034", "900 Metro Center Blv", "",
                "ON", "94404","CAN",
                "Toronto", "11","2019");

        Pungle.configure("YOUR_API_KEY", Pungle.STAGING);
        Pungle pungle = Pungle.getInstance();
        pungle.logs = true;
        pungle.fetchCreditCardToken(creditCard, new PLRequestCallback() {
            @Override
            public void onSuccess(PLCardResponseInfo cardResponse) {
                Log.v("TOKEN", cardResponse.token);
            }

            @Override
            public void onFailure(ArrayList<PungleError> errors) {

                for (int i = 0; i < errors.size(); i++){
                    PungleError err = errors.get(i);

                    // Validation Error
                    if (err.type == ErrorType.validation){

                        Log.e("MESSAGES ", err.message);
                        Log.e("VALIDATION ERROR ", String.valueOf(err.validationError));
                        return;

                    }

                    // HTTP Error
                    if (err.type == ErrorType.http){

                        Log.e("MESSAGES ", err.error.message);
                        Log.e("CODE ", err.error.code);
                        Log.e("HTTP CODE ", String.valueOf(err.code));
                        return;

                    }

                    // EXCEPTION Error
                    Log.e("EXCEPTION ", err.message);
                }
            }
        });

    }

}
