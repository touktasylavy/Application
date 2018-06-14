package la.nuol.fen.application.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;


import la.nuol.fen.application.manager.Http.ApiManager;
import la.nuol.fen.application.utils.ContactUrls;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpManager {

    private static HttpManager instance;

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    private Context mContext;
    private ApiManager apiManager;

    private HttpManager() {
        mContext = Contextor.getInstance().getContext();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(getClient())
                .baseUrl(ContactUrls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiManager = retrofit.create(ApiManager.class);
    }

    private OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        return client;
    }

    public ApiManager getApiManager() {
        return apiManager;
    }

}
