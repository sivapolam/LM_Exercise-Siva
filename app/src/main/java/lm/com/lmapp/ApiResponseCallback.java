package lm.com.lmapp;

/**
 * ApiResponseCallback interface
 */

public interface ApiResponseCallback {
    void onSuccess(Object responseBody);
    void onFailure(String errorMessage);
}
