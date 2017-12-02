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
import thotran.android.messageplan.entities.Message;

/**
 * Created by thotran on 11/29/17.
 */

public class MessageAdapter extends ArrayAdapter {
    public MessageAdapter(Context context, int resource, List<Message> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_message, null);
        }

        Message p = (Message) getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.txtTitle);
            TextView tt2 = (TextView) v.findViewById(R.id.txtReceiver);
            TextView tt3 = (TextView) v.findViewById(R.id.txtContent);

            if (tt1 != null) {
                tt1.setText(p.getTitle());
            }

            if (tt2 != null) {
                tt2.setText(p.getTo());
            }

            if (tt3 != null) {
                tt3.setText(p.getBody());
            }
        }

        return v;
    }
}
