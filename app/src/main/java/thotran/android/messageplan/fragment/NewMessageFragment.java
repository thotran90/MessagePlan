package thotran.android.messageplan.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import thotran.android.messageplan.activity.R;
import thotran.android.messageplan.adapter.WeekAdapter;
import thotran.android.messageplan.utils.Helper;

public class NewMessageFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TITLE = "Title";
    private static final String ARG_BODY = "Body";
    private static final String ARG_SENDTO = "SendTo";
    // Initial parameter
    private String mTitle;
    private String mBody;
    private String mSendTo;
    // UI Component
    EditText txtTitle, txtSendTo, txtBody, txtSchedule, txtEndDate;
    Button btnSchedule, btnEndDate, btnSubmit;
    CheckBox chkIsRepeat;
    RadioButton radDaily, radWeekly, radMonthly, radYearly;
    GridView areaWeekly, areaMonthly;
    WeekAdapter weekAdapter;
    // Variable
    String[] mDay;

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
        View rootView = inflater.inflate(R.layout.fragment_newmessage, container, false);
        // Update titles
        getActivity().setTitle("Add new message");
        initComponent(rootView);
        return rootView;
    }

    private void initComponent(View rootView) {
        txtTitle = (EditText)rootView.findViewById(R.id.txtTitle);
        if(!mTitle.isEmpty())
            txtTitle.setText(mTitle);
        txtSendTo = (EditText)rootView.findViewById(R.id.txtSendTo);
        if(!mSendTo.isEmpty())
            txtSendTo.setText(mSendTo);
        txtBody = (EditText)rootView.findViewById(R.id.txtBody);
        if(!mBody.isEmpty())
            txtBody.setText(mBody);
        initWeeklyPicker(rootView);
    }

    private void initWeeklyPicker(View rootView) {
        areaWeekly = (GridView) rootView.findViewById(R.id.grdWeekly);
        mDay = getActivity().getResources().getStringArray(R.array.day_array);
        weekAdapter = new WeekAdapter(getActivity().getBaseContext(),mDay);
        areaWeekly.setAdapter(weekAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
