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
import thotran.android.messageplan.adapter.TemplateAdapter;
import thotran.android.messageplan.database.AppDatabase;
import thotran.android.messageplan.entities.Template;
import thotran.android.messageplan.utils.Helper;

/**
 * Created by thotran on 12/1/17.
 */

public class TemplateFragment extends Fragment {

    List<Template> Templates;
    TemplateAdapter mAdapter;

    Button btnNewTemlate;
    SwipeMenuListView mListView;
    public TemplateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_template, container, false);
        getActivity().setTitle("Templates");

        initComponent(rootView);
        return rootView;
    }

    private void initComponent(View v){
        btnNewTemlate = (Button)v.findViewById(R.id.btnNewTemplate);
        btnNewTemlate.setOnClickListener(view -> RedirectNewTemplate());

        mListView = (SwipeMenuListView) v.findViewById(R.id.listTemplate);
        mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        Templates = AppDatabase.getAppDatabase(getActivity().getBaseContext()).templateDao().getAll();
        mAdapter = new TemplateAdapter(getActivity(),Templates);
        mListView.setAdapter(mAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem planItem = new SwipeMenuItem(getContext());
                planItem.setBackground(new ColorDrawable(Color.rgb(0x30, 0xB1,
                        0xF5)));
                planItem.setWidth(Helper.dp2px(90,getContext()));
                planItem.setIcon(R.drawable.ic_action_share);
                menu.addMenuItem(planItem);

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
                        Toast.makeText(getActivity().getBaseContext(),"Like button press", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        removeTemplate(position);
                        mAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity().getBaseContext(),"Removed template", Toast.LENGTH_SHORT).show();
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

    private void RedirectNewTemplate(){
        Fragment newTemplate = NewTemplateFragment.newInstance();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, newTemplate).commit();

        getActivity().setTitle("Add new template");
    };

    private void removeTemplate(int position){
        Template template = Templates.get(position);
        AppDatabase.getAppDatabase(getActivity().getBaseContext()).templateDao().delete(template);
        Templates.remove(position);
    }
}
