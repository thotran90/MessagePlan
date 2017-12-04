package thotran.android.messageplan.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thotran.android.messageplan.activity.R;

public class PlanningFragment extends Fragment {


    public PlanningFragment() {
        // Required empty public constructor
    }

    public static PlanningFragment newInstance() {
        PlanningFragment fragment = new PlanningFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planning, container, false);
    }
}
