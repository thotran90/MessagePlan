package thotran.android.messageplan.fragment;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import thotran.android.messageplan.activity.R;
import thotran.android.messageplan.database.AppDatabase;
import thotran.android.messageplan.entities.Template;

public class NewTemplateFragment extends Fragment {

    EditText txtTitle, txtSendTo, txtBody;
    Button btnSubmit;

    public NewTemplateFragment() {
        // Required empty public constructor
    }

    public static NewTemplateFragment newInstance() {
        NewTemplateFragment fragment = new NewTemplateFragment();

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
        View rootView = inflater.inflate(R.layout.fragment_new_template, container, false);

        initComponent(rootView);
        return rootView;
    }

    private void initComponent(View v){
        txtTitle = (EditText) v.findViewById(R.id.txtTitle);
        txtSendTo = (EditText)v.findViewById(R.id.txtSendTo);
        txtBody = (EditText)v.findViewById(R.id.txtBody);
        btnSubmit = (Button)v.findViewById(R.id.btnSubmitTemplate);
        btnSubmit.setOnClickListener(view -> doSubmitTemplate());
    }

    private void doSubmitTemplate(){
        Template template = new Template();
        template.setTitle(txtTitle.getText().toString());
        template.setBody(txtBody.getText().toString());
        template.setSendTo(txtSendTo.getText().toString());
        boolean isValid = validateTemplate(template);
        if(isValid){
            AppDatabase.getAppDatabase(getActivity().getBaseContext()).templateDao().insertAll(template);
            redirectToTemplate();
            Toast.makeText(getActivity(),"Saved template",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getActivity(),"Fill all of required fields before submit.",Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateTemplate(Template template){
        if(template.getTitle().isEmpty() || template.getBody().isEmpty())
            return false;
        return true;
    }

    private void redirectToTemplate(){
        Fragment template = new TemplateFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, template).commit();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
