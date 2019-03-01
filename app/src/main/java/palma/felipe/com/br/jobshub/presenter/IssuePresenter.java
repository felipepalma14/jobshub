package palma.felipe.com.br.jobshub.presenter;

import java.util.List;

import palma.felipe.com.br.jobshub.model.Issue;

public class IssuePresenter {
    private List<Issue> mListIssues;
    private View view;

    public IssuePresenter(View view) {
        this.view = view;
    }


    public interface View {
        void loadIssues();
        void loadIssue();
        void onError(String error);
        void showProgressBar();
        void hideProgressBar();
    }
}
