package thotran.android.messageplan.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import thotran.android.messageplan.database.AppDatabase;
import thotran.android.messageplan.entities.Message;

/**
 * Created by thotran on 11/29/17.
 */

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static void populateAsync(@NonNull final AppDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

//    private static Message addMessage(final AppDatabase db, Message msg) {
//        db.messageDao().insertAll(msg);
//        return msg;
//    }

    private static void populateWithTestData(AppDatabase db) {
        Message msg = new Message();
        msg.setId(1);
        msg.setTitle("Huy Vip 2K FShare");
        msg.setBody("Huy FSN");
        msg.setTo("1055");
//        addMessage(db, msg);

        List<Message> userList = db.messageDao().getAll();
        Log.d(DatabaseInitializer.TAG, "Rows Count: " + userList.size());
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}
