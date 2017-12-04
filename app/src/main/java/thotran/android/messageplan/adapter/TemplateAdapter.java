package thotran.android.messageplan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import thotran.android.messageplan.activity.R;
import thotran.android.messageplan.entities.Template;

/**
 * Created by thotran on 12/2/17.
 */

public class TemplateAdapter extends BaseAdapter {
    private Context context; //context
    List<Template> Templates;

    public TemplateAdapter(Context context, List<Template> templates) {
        this.context = context;
        Templates = templates;
    }

    @Override
    public int getCount() {
        return Templates.size();
    }

    @Override
    public Template getItem(int i) {
        return Templates.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Templates.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_template,viewGroup,false);
        }
        Template currentTemplate = (Template)getItem(i);
        TextView txtSendTo, txtTitle, txtBody;
        txtSendTo = (TextView)view.findViewById(R.id.txtSendTo);
        txtSendTo.setText(currentTemplate.getSendTo());
        txtTitle = (TextView)view.findViewById(R.id.txtTitle);
        txtTitle.setText(currentTemplate.getTitle());
        txtBody = (TextView)view.findViewById(R.id.txtBody);
        txtBody.setText(currentTemplate.getBody());
        return view;
    }
}
