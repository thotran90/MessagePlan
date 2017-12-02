package thotran.android.messageplan.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import thotran.android.messageplan.activity.R;
import thotran.android.messageplan.adapter.TemplateAdapter;
import thotran.android.messageplan.entities.Template;

/**
 * Created by thotran on 12/1/17.
 */

public class TemplateFragment extends Fragment {

    ListView mListTemplate;
    List<Template> Templates;
    TemplateAdapter mAdapter;

    Button btnNewTemlate;
    public TemplateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_template, container, false);

        btnNewTemlate = (Button)rootView.findViewById(R.id.btnNewTemplate);
        btnNewTemlate.setOnClickListener(view -> RedirectNewTemplate());

        return rootView;
    }

    private void RedirectNewTemplate(){
        Fragment newTemplate = NewTemplateFragment.newInstance("text","test");
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, newTemplate).commit();

        getActivity().setTitle("Add new template");
    };
}
