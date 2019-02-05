# Pungle Android SDK

## Installation

#### Import the AAR into your project:

1. Go to File > New > New Module
2. Select `Import .JAR/.AAR Package` and click next.
3. Enter the path to .aar file and click finish.
4. Go to File > Project Structure (Ctrl+Shift+Alt+S).
5. Under `Modules` in left menu, select `app`
6. Go to `Dependencies` tab.
7. Click the green `+` in the upper right corner.
8. Select `Module Dependency`
9. Select the new module from the list.

#### Add dependencies
Add these dependencies in your App's Gradle config file (you might already be using some of these, or all of them, so only include the ones you need):

```
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
    implementation 'com.google.code.gson:gson:2.8.5'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.9.1'
    implementation 'com.squareup.okhttp3:okhttp:3.12.1'
```

Sync / Build and test everything is working.

#### Config and use SDK
You will need your API key to initialize the SDK. The API key only needs to be provided once, so you can set this on `Main.java` if you like.

Here's an example on how to use:

```java

    @Override
    protected void onResume() {
        super.onResume();

        PLCardInfo creditCard = new PLCardInfo("John Smith", "2222405343248877",
                "master", "034", "900 Metro Center Blv", "",
                "ON", "94404","CAN", "Toronto", "11","2019");

        // Environments available are Pungle.STAGING and Pungle.PRODUCTION
        Pungle.configure("YOUR_API_KEY", Pungle.STAGING);
        Pungle pungle = Pungle.getInstance();

        // Logs are disabled by default, so if you remove this line there'll be no http logs.
        pungle.logs = true;
        pungle.fetchCreditCardToken(creditCard, new PLRequestCallback() {
            new PLRequestCallback() {

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
                            Log.e("VALIDATION ERROR ", String.valueOf(err.error));
                            return;

                        }

                        // HTTP Error
                        if (err.type == ErrorType.http){

                            Log.e("MESSAGES ", err.error.message);
                            // This code is sent by the server, this is a string.
                            Log.e("CODE ", err.error.code);
                            // In this case this is an http code.
                            Log.e("HTTP CODE ", String.valueOf(err.code));
                            return;

                        }

                        // EXCEPTION Error
                        Log.e("EXCEPTION ", err.message);
                    }
                }
        });

    }

```

## Error handling

Below you can see the PungleError class and its properties or fields.

```java
public class PungleError {

    public ValidationError validationError;
    public ErrorType type;
    public int code;
    public String message;

    // This will be populated if there's an error message being sent by the backend.
    public PLError error;
    ...
```

The `type` prop is an `ErrorType` enum.

```java
enum ErrorType {
    validation,
    http,
    exception
}
```

#### Local Validation

Validation gets done before the card is sent to the server for processing. Validation errors done locally return an error code of `2010`. They will also return a `ValidationError` enum which you can use to present an error message of your choice, or you can use the `message` String to display an error. The `validationError` prop can and will be null if the error is not a local validation error.

```java
enum ValidationError {
    name,
    cardNumber,
    cvvNumber,
    expiryYear,
    expiryMonth,
    state,
    postalCode,
    country,
    city,
    addressLine1,
    addressLine2,
    noApiKey
}
```

#### HTTP errors

In the case of an `http` type error, the `code` will be an http error and for more details you should check the `error` field of the `PungleError` being returned. Please look at the example above.

#### Exception error

The `PungleError` `type` property will be of an `exception` kind when the errors are things like no connectivity, unreachable servers, or other more severe errors.


