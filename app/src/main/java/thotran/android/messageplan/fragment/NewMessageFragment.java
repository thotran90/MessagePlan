package thotran.android.messageplan.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thotran.android.messageplan.activity.R;

public class NewMessageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TITLE = "Title";
    private static final String ARG_BODY = "Body";
    private static final String ARG_SENDTO = "SendTo";
    // TODO: Rename and change types of parameters
    private String mTitle;
    private String mBody;
    private String mSendTo;

    public NewMessageFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NewMessageFragment newInstance(String title, String body, String sendTo) {
        NewMessageFragment fragment = new NewMessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_BODY, body);
        args.putString(ARG_SENDTO, sendTo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mBody = getArguments().getString(ARG_BODY);
            mSendTo = getArguments().getString(ARG_SENDTO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newmessage, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
