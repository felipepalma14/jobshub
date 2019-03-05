    package palma.felipe.com.br.jobshub.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import palma.felipe.com.br.jobshub.listener.GitHubServiceAPI;
import palma.felipe.com.br.jobshub.model.Issue;

public class IssuePresenter implements IssueContract.UserActionListener {

    private GitHubServiceAPI mAPI;
    private IssueContract.View mIssueView;

    public IssuePresenter(GitHubServiceAPI mAPI, IssueContract.View mIssueView) {
        this.mAPI = mAPI;
        this.mIssueView = mIssueView;
    }

    @Override
    public void loadIssues() {
        mIssueView.showProgressBar();
        mAPI.getListIssues(new GitHubServiceAPI.GitHubServiceCallback<List<Issue>>(){

            @Override
            public void onLoaded(List<Issue> issues) {
                mIssueView.hideProgressBar();
                mIssueView.showIssues(issues);
            }
        });

    }

    @Override
    public void loadIssueDetails(@NonNull Issue issue) {
        mIssueView.showIssueDetails(issue);

    }
}
