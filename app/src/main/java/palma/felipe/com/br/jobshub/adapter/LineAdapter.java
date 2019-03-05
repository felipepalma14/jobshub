package palma.felipe.com.br.jobshub.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import palma.felipe.com.br.jobshub.R;
import palma.felipe.com.br.jobshub.model.Issue;

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.LineHolder> {

    private List<Issue> mListIssues;
    private ItemListener mItemListener;


    public LineAdapter(List<Issue> mListIssues, ItemListener itemListener) {
        setListIssues(mListIssues);
        this.mItemListener = itemListener;

    }


    @NonNull
    @Override
    public LineHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View issueView = inflater.inflate(R.layout.main_line_view, viewGroup, false);
        return new LineHolder(issueView, mItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LineHolder lineHolder, int i) {
        lineHolder.txtIssueTitle.setText(mListIssues.get(i).getTitle());
        lineHolder.txtIssueJobLocal.setText(mListIssues.get(i).getState());
        lineHolder.txtIssueJobOpenedAt.setText(mListIssues.get(i).getCreatedAt());
        Glide
            .with(lineHolder.imgProfile.getContext())
            .load(mListIssues.get(i).getUser().getAvatarUrl())
            .into(lineHolder.imgProfile);
    }

    @Override
    public int getItemCount() {
        return mListIssues != null ? mListIssues.size() : 0;
    }

    private void setListIssues(List<Issue> issues) {
        this.mListIssues = issues;
    }

    public void replaceData(List<Issue> issues) {
        setListIssues(issues);
        notifyDataSetChanged();
    }

    public Issue getItem(int position) {
        return mListIssues.get(position);

    }

    public class LineHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtIssueTitle, txtIssueJobLocal, txtIssueJobOpenedAt;
        public ImageView imgProfile;
        private ItemListener mItemListener;


        public LineHolder(View itemView, ItemListener listener) {
            super(itemView);
            this.mItemListener = listener;
            txtIssueTitle = (TextView) itemView.findViewById(R.id.txt_job_title);
            txtIssueJobLocal = (TextView) itemView.findViewById(R.id.txt_job_local);
            txtIssueJobOpenedAt = (TextView) itemView.findViewById(R.id.txt_issue_opened_at);
            imgProfile = (ImageView) itemView.findViewById(R.id.img_github_profile);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Issue mIssue = getItem(position);
            mItemListener.onIssueClick(mIssue);

        }
    }
    public interface ItemListener {

        void onIssueClick(Issue clickedIssue);
    }
}

