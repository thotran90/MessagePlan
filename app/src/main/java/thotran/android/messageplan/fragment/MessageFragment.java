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

import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.List;

import thotran.android.messageplan.activity.R;
import thotran.android.messageplan.adapter.MessageAdapter;
import thotran.android.messageplan.database.AppDatabase;
import thotran.android.messageplan.entities.Message;

/**
 * Created by thotran on 11/30/17.
 */

public class MessageFragment extends Fragment {

    SwipeMenuListView mListView;
    MessageAdapter messageAdapter;
    List<Message> mData;
    Button btnNewMessage;

    public MessageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);

        //Init component
        initComponent(rootView);
        return rootView;
    }

    private void initComponent(View v){
        btnNewMessage = (Button)v.findViewById(R.id.btnnewMessage);
        btnNewMessage.setOnClickListener(view -> redirectNewMessage());

        mListView = (SwipeMenuListView)v.findViewById(R.id.listMessage);
        mData = AppDatabase.getAppDatabase(getActivity().getBaseContext()).messageDao().getAll();
        messageAdapter = new MessageAdapter(getActivity(),mData);
        mListView.setAdapter(messageAdapter);
    }

    private void redirectNewMessage(){
        Fragment newMessage = NewMessageFragment.newInstance("","","");

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, newMessage).commit();

        getActivity().setTitle("Add new message");
    }
}
