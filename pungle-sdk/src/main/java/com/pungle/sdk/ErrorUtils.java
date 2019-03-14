// Copyright 2019 Pungle

package com.pungle.sdk;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

    public static PungleError parseError(Response<?> response) {
        Converter<ResponseBody, PungleError> converter =
                Pungle.getInstance().retrofit()
                        .responseBodyConverter(PungleError.class, new Annotation[0]);

        PungleError error;

        try {
            error = converter.convert(response.errorBody());
            error.code = response.code();
            error.type = ErrorType.http;
        } catch (IOException e) {
            return new PungleError(ErrorType.exception, null, "Something went wrong while parsing http error", -1);
        }

        return error;
    }
}
