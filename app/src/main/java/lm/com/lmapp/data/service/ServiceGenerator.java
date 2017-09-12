package lm.com.lmapp.data.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import lm.com.lmapp.ApiResponseCallback;
import lm.com.lmapp.data.model.ApiError;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by rkaringat001 on 12/08/17.
 */

public class ServiceGenerator {

    private static final String BASE_URL = "https://a2b7cf8676394fda75de-6e0550a16cd96615f7274fd70fa77109.r93.cf3.rackcdn.com/";

    private static ApiResponseCallback sApiResponseCallback;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            })
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    okhttp3.Response response = chain.proceed(request);

                    //errr
                    if (!response.isSuccessful()) {
                        handleErrorScenario(retrofit, response.body());
                        return response;
                    }

                    return response;
                }
            })
            .build();



    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson));

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(ApiResponseCallback apiResponseCallback, Class<S> serviceClass) {
        sApiResponseCallback = apiResponseCallback;
        return retrofit.create(serviceClass);
    }

    /**
     * Handles Error scenarios
     * @param retrofit - Retrofit reference
     * @param responseBody - ResponseBody reference
     */
    private static void handleErrorScenario(Retrofit retrofit, ResponseBody responseBody) {
        Converter<ResponseBody, ApiError> errorBodyConverter = retrofit
                .responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError error = null;
        try {
            error = errorBodyConverter.convert(responseBody);
        } catch (IOException e) {
            sApiResponseCallback.onFailure("Problem in Fetching Data! \nPlease Try again...!");
        }

        if (error != null) {
            sApiResponseCallback.onFailure(error.getMessage());
        }
    }
}
