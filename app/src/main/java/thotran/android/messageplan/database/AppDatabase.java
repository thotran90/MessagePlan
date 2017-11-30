package thotran.android.messageplan.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import thotran.android.messageplan.dao.HistoryDao;
import thotran.android.messageplan.dao.MessageDao;
import thotran.android.messageplan.dao.TemplateDao;
import thotran.android.messageplan.entities.History;
import thotran.android.messageplan.entities.Message;
import thotran.android.messageplan.entities.Template;

/**
 * Created by thotran on 11/29/17.
 */

@Database(entities = {Message.class, Template.class, History.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract MessageDao messageDao();

    public abstract TemplateDao templateDao();

    public abstract HistoryDao historyDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "message-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}

