package palma.felipe.com.br.jobshub.repository;

import java.util.List;

import palma.felipe.com.br.jobshub.model.Issue;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IGithubAPIService {
    @GET("/repos/androiddevbr/vagas/issues")
    Call<List<Issue>> getAllIssues();


    @GET("/repos/androiddevbr/vagas/issues/{id}")
    Call<List<Issue>> getIssueById(@Path("id") String id);


}
