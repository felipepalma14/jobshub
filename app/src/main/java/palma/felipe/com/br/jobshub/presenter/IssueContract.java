package palma.felipe.com.br.jobshub.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import palma.felipe.com.br.jobshub.model.Issue;

public class IssueContract {


    public interface View {
        void showIssues(List<Issue> issues);
        void showIssueDetails(Issue issue);
        void onError(String error);
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserActionListener{
        void loadIssues();
        void loadIssueDetails(@NonNull Issue issue);
    }

}
