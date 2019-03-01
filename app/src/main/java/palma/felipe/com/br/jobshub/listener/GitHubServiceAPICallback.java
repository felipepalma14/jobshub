package palma.felipe.com.br.jobshub.listener;

import java.util.List;

import palma.felipe.com.br.jobshub.model.Issue;

public interface GitHubServiceAPICallback {
    void onLoadIssuesComplete(List<Issue> issues);

}
