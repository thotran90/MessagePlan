package thotran.android.messageplan.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import thotran.android.messageplan.adapter.MessageAdapter;
import thotran.android.messageplan.database.AppDatabase;
import thotran.android.messageplan.entities.Message;
import thotran.android.messageplan.utils.DatabaseInitializer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    ListView mListView;
    List<Message> Messages;
    MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    @SuppressLint("WrongViewCast")
    private void initComponent(){
        mListView = (ListView)findViewById(R.id.ivListMessage);
        Messages = AppDatabase.getAppDatabase(getBaseContext()).messageDao().getAll();
        messageAdapter = new MessageAdapter(getBaseContext(),R.layout.item_message,Messages);
        mListView.setAdapter(messageAdapter);
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
