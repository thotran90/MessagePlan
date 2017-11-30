package thotran.android.messageplan.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import thotran.android.messageplan.entities.History;

/**
 * Created by thotran on 12/1/17.
 */
@Dao
public interface HistoryDao {
    @Query("SELECT * FROM histories")
    List<History> getAll();

    @Insert
    void insertAll(History... histories);
}
