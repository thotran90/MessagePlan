package thotran.android.messageplan.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by thotran on 12/1/17.
 */
@Entity(tableName="templates")
public class Template {
    @PrimaryKey
    private int Id;
    @ColumnInfo(name = "title")
    private String Title;
    @ColumnInfo(name="body")
    private String Body;
    @ColumnInfo(name="sendto")
    private String SendTo;

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

    public String getSendTo() {
        return SendTo;
    }

    public void setSendTo(String sendTo) {
        SendTo = sendTo;
    }
}
