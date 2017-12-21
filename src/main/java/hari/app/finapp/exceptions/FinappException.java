package hari.app.finapp.exceptions;

/**
 * Created by HariHaraKumar on 19/12/2017.
 */
public class FinappException extends Exception {
    String exceptionMessage;

    public FinappException(String message){
        this.exceptionMessage=message;
    }

    @Override
    public String toString() {
        return "FinappException Occured {" +
                "exceptionMessage='" + exceptionMessage + '\'' +
                '}';
    }
}
