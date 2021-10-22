package edu.uark.finalproject.InboxActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.uark.finalproject.ComposeActivity.ComposeActivity;
import edu.uark.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InboxViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InboxViewFragment extends Fragment implements InboxContract.View {

    private InboxContract.Presenter mPresenter;
    private RecyclerView rvInbox;

    public InboxViewFragment() {
        // Required empty public constructor
    }

    public static InboxViewFragment newInstance() {
        InboxViewFragment fragment = new InboxViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume(){
        super.onResume();
        rvInbox.setAdapter(new InboxViewAdapter(mPresenter.getMessages()));
        rvInbox.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_inbox_view, container, false);
        Button button = root.findViewById(R.id.btnCreateMessage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sendClicked();
            }
        });
        rvInbox = root.findViewById(R.id.rvMessages);
        return root;
    }

    @Override
    public void setPresenter(InboxContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void startComposeActivity() {
        Intent composeIntent = new Intent();
        composeIntent.setClass(getActivity(), ComposeActivity.class);
        composeIntent.putExtra("To","info@csce.uark.edu");
        composeIntent.putExtra("CC","");
        composeIntent.putExtra("Subject","Graduation Date");
        composeIntent.putExtra("Content","");
        startActivity(composeIntent);
    }

    @Override
    public void notifyMessagesLoaded() {
        ((InboxViewAdapter)rvInbox.getAdapter()).setLocalDataSet(mPresenter.getMessages());
        rvInbox.getAdapter().notifyDataSetChanged();
    }
}