package thotran.android.messageplan.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by thotran on 12/1/17.
 */
@Entity(tableName="histories")
public class History {
    @PrimaryKey
    private int Id;
    @ColumnInfo(name="title")
    private String Title;
    @ColumnInfo(name="body")
    private String Body;
    @ColumnInfo(name = "sentto")
    private String SentTo;
    @ColumnInfo(name="senttime")
    private String SentTime;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getSentTo() {
        return SentTo;
    }

    public void setSentTo(String sentTo) {
        SentTo = sentTo;
    }

    public String getSentTime() {
        return SentTime;
    }

    public void setSentTime(String sentTime) {
        SentTime = sentTime;
    }
}
