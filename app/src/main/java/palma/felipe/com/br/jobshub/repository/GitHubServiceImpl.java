package palma.felipe.com.br.jobshub.repository;

import android.util.Log;

import java.util.List;

import palma.felipe.com.br.jobshub.listener.GitHubServiceAPI;
import palma.felipe.com.br.jobshub.model.Issue;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubServiceImpl implements GitHubServiceAPI {
    IGithubAPIService mServiceAPI;

    public GitHubServiceImpl() {
        this.mServiceAPI = RetrofitClient.getClient().create(IGithubAPIService.class);
    }

    @Override
    public void getListIssues(final GitHubServiceCallback<List<Issue>> callback) {
        Call<List<Issue>> call = this.mServiceAPI.getAllIssues();

        call.enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                if(response.code()==200){
                    List<Issue> listIssuesResult = response.body(); // return the response to a list object
                    callback.onLoaded(listIssuesResult);
                }

            }

            @Override
            public void onFailure(Call<List<Issue>> call, Throwable t) {
                Log.d("TESTE",t.getMessage());
            }
        });


    }

    @Override
    public void getIssue(String id, GitHubServiceCallback<Issue> callback) {

    }
}
