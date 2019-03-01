package palma.felipe.com.br.jobshub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import okhttp3.OkHttpClient;
import palma.felipe.com.br.jobshub.model.Issue;
import palma.felipe.com.br.jobshub.repository.IGithubAPIService;
import palma.felipe.com.br.jobshub.util.OkHttpUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up retrofit builder object

        OkHttpClient client = OkHttpUtils.createHttpClient();
        Retrofit.Builder builder = new Retrofit.Builder()

                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client);
        //create retrofit object

        Retrofit retrofit = builder.build();
        //instance of our github user

        IGithubAPIService githubAPIService = retrofit.create(IGithubAPIService.class);

        Call<List<Issue>> call = githubAPIService.getAllIssues();

        call.enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                Log.d("TESTE",response.code()+"");
                List<Issue> list = response.body(); // return the response to a list object
                Log.d("TESTE",list.get(3).toString());

            }

            @Override
            public void onFailure(Call<List<Issue>> call, Throwable t) {
                Log.d("TESTE",t.getMessage());
            }
        });

    }
}
