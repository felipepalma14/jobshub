package palma.felipe.com.br.jobshub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import palma.felipe.com.br.jobshub.adapter.LineAdapter;
import palma.felipe.com.br.jobshub.model.Issue;
import palma.felipe.com.br.jobshub.presenter.IssueContract;
import palma.felipe.com.br.jobshub.presenter.IssuePresenter;
import palma.felipe.com.br.jobshub.repository.GitHubServiceImpl;

public class MainActivity extends AppCompatActivity implements IssueContract.View {

    //https://developer.github.com/v3/issues/#get-a-single-issue
    private final String TAG = this.getClass().getSimpleName();

    private IssueContract.UserActionListener mUserActionListener;

    RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private LineAdapter mAdapter;

    LineAdapter.ItemListener mItemListener = new LineAdapter.ItemListener() {

        @Override
        public void onIssueClick(Issue clickedIssue) {
            mUserActionListener.loadIssueDetails(clickedIssue);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar)findViewById(R.id.progresbar);
        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        mUserActionListener = new IssuePresenter(new GitHubServiceImpl(),this);
        setupRecycler();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserActionListener.loadIssues();
    }

    @Override
    public void showIssues(List<Issue> issues) {
        Log.d(TAG,issues.toString());
        mAdapter.replaceData(issues);

    }

    @Override
    public void showIssueDetails(Issue issue) {
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("ISSUE", issue);
        Toast.makeText(this,"Clicou: " + issue.getNumber(), Toast.LENGTH_LONG).show();
        Intent mIntent = new Intent(this, DetailJobsActivity.class);
        mIntent.putExtras(mBundle);

        startActivity(mIntent);
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void setupRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new LineAdapter(new ArrayList<Issue>(0),mItemListener);
        mRecyclerView.setAdapter(mAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
