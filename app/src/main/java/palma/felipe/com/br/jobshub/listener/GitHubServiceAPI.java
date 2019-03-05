package palma.felipe.com.br.jobshub.listener;

import java.util.List;

import palma.felipe.com.br.jobshub.model.Issue;

public interface GitHubServiceAPI {

    interface GitHubServiceCallback<T> {
        void onLoaded(T issues);
    }

    void getListIssues(GitHubServiceCallback<List<Issue>> callback);
    void getIssue(String id, GitHubServiceCallback<Issue> callback);

}
