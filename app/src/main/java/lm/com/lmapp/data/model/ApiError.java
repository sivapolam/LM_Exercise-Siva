package lm.com.lmapp.data.model;

/**
 * Holds API error information
 */
public class ApiError {
    private int statusCode;
    private String message;

    public ApiError() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
