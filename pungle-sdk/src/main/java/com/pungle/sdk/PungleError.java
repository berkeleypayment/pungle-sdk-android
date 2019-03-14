// Copyright 2019 Pungle

package com.pungle.sdk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PungleError {

    public ValidationError validationError;
    public ErrorType type;
    public int code;
    public String message;

    @SerializedName("error")
    @Expose
    public PLError error;

    public PungleError(ErrorType type, ValidationError error, String message, int code) {
        this.validationError = error;
        this.type = type;
        this.message = message;
        this.code = code;
    }


}