package thotran.android.messageplan.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import thotran.android.messageplan.activity.R;
import thotran.android.messageplan.adapter.MessageAdapter;
import thotran.android.messageplan.database.AppDatabase;
import thotran.android.messageplan.entities.Message;

/**
 * Created by thotran on 11/30/17.
 */

public class MessageFragment extends Fragment {

    ListView mListView;
    MessageAdapter messageAdapter;
    List<Message> mData;

    public MessageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);

        //Init component
        mListView = (ListView)rootView.findViewById(R.id.listMessages);
        mData = AppDatabase.getAppDatabase(getActivity().getBaseContext()).messageDao().getAll();
        messageAdapter = new MessageAdapter(getActivity(),R.layout.item_message,mData);
        mListView.setAdapter(messageAdapter);
        return rootView;
    }
}
