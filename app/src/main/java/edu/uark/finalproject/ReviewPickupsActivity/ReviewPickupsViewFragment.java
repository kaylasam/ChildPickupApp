package edu.uark.finalproject.ReviewPickupsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.uark.finalproject.DashboardActivity.DashboardActivity;
import edu.uark.finalproject.R;

public class ReviewPickupsViewFragment extends Fragment implements ReviewPickupsContract.View{

    private ReviewPickupsContract.Presenter mPresenter;
    private RecyclerView rvReviewPickups;

    public ReviewPickupsViewFragment() {
        // Required empty public constructor
    }

    public static ReviewPickupsViewFragment newInstance() {
        ReviewPickupsViewFragment fragment = new ReviewPickupsViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume(){
        super.onResume();
        rvReviewPickups.setAdapter(new ReviewPickupsViewAdapter(mPresenter.getReviewPickups()));
        rvReviewPickups.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_review_pickups_view, container, false);
        Button button = root.findViewById(R.id.reviewPickupsBackButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });
        rvReviewPickups = root.findViewById(R.id.rvReviewPickups);
        return root;
    }

    @Override
    public void setPresenter(ReviewPickupsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void notifyReviewPickupsLoaded() {
        ((ReviewPickupsViewAdapter)rvReviewPickups.getAdapter()).setLocalDataSet(mPresenter.getReviewPickups());
        rvReviewPickups.getAdapter().notifyDataSetChanged();
    }
}
