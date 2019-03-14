// Copyright 2019 Pungle

package com.pungle.sdk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PLCardResponseData {

    @SerializedName("data")
    @Expose
    public PLCardResponseInfo data;


}
