package edu.uark.finalproject.ParentProfileActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.uark.finalproject.ChildProfileActivity.ChildProfileContract;
import edu.uark.finalproject.ChildProfileActivity.ChildProfileViewAdapter;
import edu.uark.finalproject.ChildProfileActivity.ChildProfileViewFragment;
import edu.uark.finalproject.DashboardActivity.DashboardActivity;
import edu.uark.finalproject.R;

public class ParentProfileViewFragment extends Fragment implements ParentProfileContract.View {

    private ParentProfileContract.Presenter mPresenter;
    private RecyclerView rvParentProfile;

    public ParentProfileViewFragment() {
        // Required empty public constructor
    }

    public static ParentProfileViewFragment newInstance() {
        ParentProfileViewFragment fragment = new ParentProfileViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume(){
        super.onResume();
        rvParentProfile.setAdapter(new ParentProfileViewAdapter(mPresenter.getParents()));
        rvParentProfile.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_parent_profile_view, container, false);
        Button button = root.findViewById(R.id.parentBackButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });
        rvParentProfile = root.findViewById(R.id.rvParents);
        return root;
    }

    @Override
    public void setPresenter(ParentProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void notifyParentsLoaded() {
        ((ParentProfileViewAdapter)rvParentProfile.getAdapter()).setLocalDataSet(mPresenter.getParents());
        rvParentProfile.getAdapter().notifyDataSetChanged();
    }
}
