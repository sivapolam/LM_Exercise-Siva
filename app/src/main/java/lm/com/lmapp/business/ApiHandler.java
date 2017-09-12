package lm.com.lmapp.business;

import android.provider.SyncStateContract;

import lm.com.lmapp.ApiResponseCallback;
import lm.com.lmapp.data.model.ProductDetails;
import lm.com.lmapp.data.service.ServiceApi;
import lm.com.lmapp.data.service.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pnaganjane001 on 9/11/17.
 */

public class ApiHandler {

    private final ServiceApi musicServiceApi;

    private ApiResponseCallback apiResponseCallback;

    public ApiHandler(ApiResponseCallback apiResponseCallback) {
        this.apiResponseCallback = apiResponseCallback;
        musicServiceApi = ServiceGenerator.createService(apiResponseCallback, ServiceApi.class);
    }

    /**
     * Make API request to get list of Albums/Tracks based on term
     */
    public void getProductDetails() {
        final String BASE_URL = "https://a2b7cf8676394fda75de-6e0550a16cd96615f7274fd70fa77109.r93.cf3.rackcdn.com/common/json/assignment.json";

        Call<ProductDetails> albumDetailsCall = musicServiceApi.getProductDetails(BASE_URL);
        albumDetailsCall.enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                if (response.isSuccessful()
                        && apiResponseCallback != null) {
                    apiResponseCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {
                apiResponseCallback.onFailure("Problem in Fetching Data! \n Please Try again...!");
            }
        });
    }

}
