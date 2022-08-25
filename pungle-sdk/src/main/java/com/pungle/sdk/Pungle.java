// Copyright 2019 Pungle

package com.pungle.sdk;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pungle {

    private static Pungle instance;
    private String publicApiToken;
    public boolean logs = false;
    public static String STAGING = "https://api.staging.pungle.co/api/v2/";
    public static String PRODUCTION = "https://api.pungle.io/api/v2/";

    public Pungle(){}

    private String BASE_URL = "";

    public static void configure(String apiKey, String environmentUrl) {

        if (instance == null){
            instance = new Pungle();
        }

        instance.publicApiToken = apiKey;
        instance.BASE_URL = environmentUrl;
    }

    public static synchronized Pungle getInstance(){
        if(instance == null){
            instance = new Pungle();
        }

        return instance;
    }

    public Retrofit retrofit(){

        if (logs) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            return new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void fetchCreditCardToken(PLCardInfo cardInfo, final PLRequestCallback callback) {

        if (instance.publicApiToken == null){
            ArrayList<PungleError> errorsArr = new ArrayList<PungleError>();
                    PungleError apiError = new PungleError(ErrorType.validation, ValidationError.noApiKey, "API key required", PungleValidation.VALIDATION_ERR_CODE);
                    errorsArr.add(apiError);
            callback.onFailure(errorsArr);
        }

        // Validate Card
        ArrayList<PungleError> errors= cardInfo.validate();
        if (errors.size() > 0){
            callback.onFailure(errors);
            return;
        }

        // Retrofit init
        Retrofit retrofit = retrofit();

        PungleService service = retrofit.create(PungleService.class);
        PLCreditCard cardModel = new PLCreditCard(cardInfo);

        String authorization = "Bearer " + publicApiToken;
        Call<PLCardResponseData> call = service.fetchCardToken(authorization, cardModel);

        call.enqueue(new Callback<PLCardResponseData>() {
            @Override
            public void onResponse(Call<PLCardResponseData> call, retrofit2.Response<PLCardResponseData> response) {

                if (response.isSuccessful()){
                    PLCardResponseInfo cardInfo = response.body().data;
                    callback.onSuccess(cardInfo);
                    return;
                }

                // HTTP error parse
                PungleError error = ErrorUtils.parseError(response);
                ArrayList<PungleError> httpErrors = new ArrayList<PungleError>();
                httpErrors.add(error);
                callback.onFailure(httpErrors);

            }

            @Override
            public void onFailure(Call<PLCardResponseData> call, Throwable t) {
                PungleError error = new PungleError(ErrorType.exception, null, t.getMessage(), -1);
                ArrayList<PungleError> exceptionErr = new ArrayList<PungleError>();
                exceptionErr.add(error);
                callback.onFailure(exceptionErr);

            }
        });

    }

}
