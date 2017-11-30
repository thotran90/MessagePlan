package thotran.android.messageplan.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import thotran.android.messageplan.entities.Template;

/**
 * Created by thotran on 12/1/17.
 */
@Dao
public interface TemplateDao {
    @Query("SELECT * FROM templates")
    List<Template> getAll();

    @Query("SELECT * FROM templates where title LIKE  :title")
    Template findByName(String title);

    @Query("SELECT COUNT(*) from templates")
    int countUsers();

    @Insert
    void insertAll(Template... templates);

    @Delete
    void delete(Template template);
}
