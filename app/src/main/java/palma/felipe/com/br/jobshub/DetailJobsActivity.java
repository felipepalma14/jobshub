package palma.felipe.com.br.jobshub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import palma.felipe.com.br.jobshub.model.Issue;

public class DetailJobsActivity extends AppCompatActivity {

    private Issue mIssue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jobs);

        TextView txt = (TextView)findViewById(R.id.txt_body);

        mIssue = (Issue) getIntent().getSerializableExtra("ISSUE");
        txt.setText(mIssue.getBody());
        Log.d("ISSUE", mIssue.getBody());
    }
}
