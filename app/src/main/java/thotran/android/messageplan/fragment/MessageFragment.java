package thotran.android.messageplan.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.List;

import thotran.android.messageplan.activity.R;
import thotran.android.messageplan.adapter.MessageAdapter;
import thotran.android.messageplan.database.AppDatabase;
import thotran.android.messageplan.entities.Message;
import thotran.android.messageplan.entities.Template;
import thotran.android.messageplan.utils.Helper;

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

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem removeItem = new SwipeMenuItem(getContext());
                removeItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                removeItem.setWidth(Helper.dp2px(90,getContext()));
                removeItem.setIcon(R.drawable.ic_action_discard);


                menu.addMenuItem(removeItem);


            }
        };
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {

            @Override

            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                switch (index) {
                    case 0:
                        Message message = messageAdapter.getItem(position);
                        AppDatabase.getAppDatabase(getActivity().getBaseContext()).messageDao().delete(message);
                        mData.remove(position);
                        messageAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity().getBaseContext(),"Removed message",Toast.LENGTH_SHORT).show();
                        break;

                }

                return true;

            }

        });

        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {

            @Override
            public void onMenuOpen(int position) {

            }

            @Override
            public void onMenuClose(int position) {

            }

        });

        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });
    }

    private void redirectNewMessage(){
        Fragment newMessage = NewMessageFragment.newInstance("","","");

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, newMessage).commit();

        getActivity().setTitle("Add new message");
    }
}
