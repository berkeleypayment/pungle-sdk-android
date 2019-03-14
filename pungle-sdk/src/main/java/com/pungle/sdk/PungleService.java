// Copyright 2019 Pungle

package com.pungle.sdk;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PungleService {
    @POST("tokens")
    Call<PLCardResponseData> fetchCardToken(@Header("Authorization") String authorization, @Body PLCreditCard creditCard);
}
