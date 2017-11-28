package thotran.android.messageplan.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by thotran on 11/29/17.
 */
@Entity(tableName = "messages")
public class Message {
    @PrimaryKey
    private int Id;
    @ColumnInfo(name = "title")
    private String Title;
    @ColumnInfo(name = "body")
    private String Body;
    @ColumnInfo(name = "to_number")
    private String To;
    @ColumnInfo(name = "sending_time")
    private String SendingTime;

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

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getSendingTime() {
        return SendingTime;
    }

    public void setSendingTime(String sendingTime) {
        SendingTime = sendingTime;
    }
}
