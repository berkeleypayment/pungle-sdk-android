// Copyright 2019 Pungle

package com.pungle.sdk;

import java.util.ArrayList;

public interface PLRequestCallback {

    void onSuccess(PLCardResponseInfo cardResponse);

    void onFailure(ArrayList<PungleError> errors);
}
