package thotran.android.messageplan.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thotran.android.messageplan.activity.R;

/**
 * Created by thotran on 12/1/17.
 */

public class HistoryFragment extends Fragment {

    public HistoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);


        return rootView;
    }
}
