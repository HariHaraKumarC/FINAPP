package hari.app.finapp.utils;

/**
 * Created by HariHaraKumar on 6/12/2017.
 */
public class FinappResponseHandler {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FinappResponseHandler(String message) {
        this.message = message;
    }
}
