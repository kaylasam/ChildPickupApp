package edu.uark.finalproject.ChildProfileActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.uark.finalproject.R;

public class ChildProfileViewFragment extends Fragment implements ChildProfileContract.View{

    private ChildProfileContract.Presenter mPresenter;
    private RecyclerView rvChildProfile;

    public ChildProfileViewFragment() {
        // Required empty public constructor
    }

    public static ChildProfileViewFragment newInstance() {
        ChildProfileViewFragment fragment = new ChildProfileViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume(){
        super.onResume();
        rvChildProfile.setAdapter(new ChildProfileViewAdapter(mPresenter.getChildren()));
        rvChildProfile.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_child_profile_view, container, false);
        Button button = root.findViewById(R.id.childBackButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mPresenter.sendClicked();
                Log.d("ChildProfileViewFragment: ", "back button clicked");
            }
        });
        rvChildProfile = root.findViewById(R.id.rvChildren);
        return root;
    }

    @Override
    public void setPresenter(ChildProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void notifyChildrenLoaded() {
        ((ChildProfileViewAdapter)rvChildProfile.getAdapter()).setLocalDataSet(mPresenter.getChildren());
        rvChildProfile.getAdapter().notifyDataSetChanged();
    }
}
