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
import thotran.android.messageplan.entities.Message;

/**
 * Created by thotran on 11/29/17.
 */

public class MessageAdapter extends BaseAdapter {

    private Context context;
    private List<Message> lMessages;

    public MessageAdapter(Context context, List<Message> lMessages) {
        this.context = context;
        this.lMessages = lMessages;
    }

    @Override
    public int getCount() {
        return lMessages.size();
    }

    @Override
    public Message getItem(int i) {
        return lMessages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lMessages.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_message,viewGroup,false);
        }

        TextView txtSendTo, txtContent, txtSentTime, txtTitle, txtRepeat;

        return view;
    }
}
