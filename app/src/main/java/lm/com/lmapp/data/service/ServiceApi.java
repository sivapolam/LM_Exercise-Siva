package lm.com.lmapp.data.service;

import lm.com.lmapp.data.model.ProductDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by pnaganjane001 on 9/11/17.
 */

public interface ServiceApi {

    @GET
    Call<ProductDetails> getProductDetails(@Url String url);
}
