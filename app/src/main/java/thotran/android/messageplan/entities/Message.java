package thotran.android.messageplan.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by thotran on 11/29/17.
 */
@Entity(tableName = "messages")
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    @ColumnInfo(name = "title")
    private String Title;
    @ColumnInfo(name = "body")
    private String Body;
    @ColumnInfo(name = "to_number")
    private String To;
    @ColumnInfo(name = "sending_time")
    private String SendingTime;
    @ColumnInfo(name="is_repeat")
    private String IsRepeat;
    @ColumnInfo(name="is_repeat_daily")
    private String IsRepeatDaily;
    @ColumnInfo(name = "is_repeat_weekly")
    private String IsRepeatWeekly;
    @ColumnInfo(name="repeat_weekly_day")
    private String RepeatWeeklyValue;
    @ColumnInfo(name="is_repeat_monthly")
    private String IsRepeatMonthly;
    @ColumnInfo(name = "repeat_monthly_date")
    private String RepeatMonthlyDate;
    @ColumnInfo(name="is_repeat_yearly")
    private String IsRepeatYearly;
    @ColumnInfo(name = "repeat_yearly_date")
    private String RepeatYearlyDate;
    @ColumnInfo(name = "is_has_end_date")
    private String IsHasEndDate;
    @ColumnInfo(name = "end_date")
    private String EndDate;
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

    public String getIsRepeat() {
        return IsRepeat;
    }

    public void setIsRepeat(String isRepeat) {
        IsRepeat = isRepeat;
    }

    public String getIsRepeatDaily() {
        return IsRepeatDaily;
    }

    public void setIsRepeatDaily(String isRepeatDaily) {
        IsRepeatDaily = isRepeatDaily;
    }

    public String getIsRepeatWeekly() {
        return IsRepeatWeekly;
    }

    public void setIsRepeatWeekly(String isRepeatWeekly) {
        IsRepeatWeekly = isRepeatWeekly;
    }

    public String getRepeatWeeklyValue() {
        return RepeatWeeklyValue;
    }

    public void setRepeatWeeklyValue(String repeatWeeklyValue) {
        RepeatWeeklyValue = repeatWeeklyValue;
    }

    public String getIsRepeatMonthly() {
        return IsRepeatMonthly;
    }

    public void setIsRepeatMonthly(String isRepeatMonthly) {
        IsRepeatMonthly = isRepeatMonthly;
    }

    public String getRepeatMonthlyDate() {
        return RepeatMonthlyDate;
    }

    public void setRepeatMonthlyDate(String repeatMonthlyDate) {
        RepeatMonthlyDate = repeatMonthlyDate;
    }

    public String getIsRepeatYearly() {
        return IsRepeatYearly;
    }

    public void setIsRepeatYearly(String isRepeatYearly) {
        IsRepeatYearly = isRepeatYearly;
    }

    public String getRepeatYearlyDate() {
        return RepeatYearlyDate;
    }

    public void setRepeatYearlyDate(String repeatYearlyDate) {
        RepeatYearlyDate = repeatYearlyDate;
    }

    public String getIsHasEndDate() {
        return IsHasEndDate;
    }

    public void setIsHasEndDate(String isHasEndDate) {
        IsHasEndDate = isHasEndDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}
