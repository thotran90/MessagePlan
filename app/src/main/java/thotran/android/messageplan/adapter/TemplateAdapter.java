package thotran.android.messageplan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import thotran.android.messageplan.activity.R;
import thotran.android.messageplan.entities.Template;

/**
 * Created by thotran on 12/2/17.
 */

public class TemplateAdapter extends ArrayAdapter {
    public TemplateAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_template, null);
        }
        TextView txtTitle, txtSendTo, txtBody;
        txtTitle = (TextView)v.findViewById(R.id.txtTitle);
        txtSendTo = (TextView)v.findViewById(R.id.txtSendTo);
        txtBody = (TextView)v.findViewById(R.id.txtBody);
        Template item = (Template)getItem(position);
        if(item != null){
            txtTitle.setText(item.getTitle());
            txtBody.setText(item.getBody());
            txtSendTo.setText(item.getSendTo());
        }
        return v;
    }
}
