package thotran.android.messageplan.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import thotran.android.messageplan.entities.Message;

/**
 * Created by thotran on 11/29/17.
 */
@Dao
public interface MessageDao {
    @Query("SELECT * FROM messages")
    List<Message> getAll();

    @Query("SELECT * FROM messages where title LIKE  :title")
    Message findByName(String title);

    @Query("SELECT COUNT(*) from messages")
    int countUsers();

    @Insert
    void insertAll(Message... messages);

    @Delete
    void delete(Message message);
}
