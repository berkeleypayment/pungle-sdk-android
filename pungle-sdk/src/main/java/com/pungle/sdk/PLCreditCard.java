// Copyright 2019 Pungle

package com.pungle.sdk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PLCreditCard {

    @SerializedName("card")
    @Expose
    private PLCardInfo cardInfo;

    public PLCreditCard(PLCardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }
}
