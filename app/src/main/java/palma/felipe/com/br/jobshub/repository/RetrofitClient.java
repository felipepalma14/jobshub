package palma.felipe.com.br.jobshub.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import palma.felipe.com.br.jobshub.Config;
import palma.felipe.com.br.jobshub.util.OkHttpUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    private static Gson gson = new GsonBuilder()
            .create();

    public static Retrofit getClient() {
        if (retrofit==null) {
            OkHttpClient client = OkHttpUtils.createHttpClient();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
