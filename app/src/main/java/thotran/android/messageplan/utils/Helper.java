package thotran.android.messageplan.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by thotran on 12/4/17.
 */

public class Helper {
    public static int dp2px(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,context.getResources().getDisplayMetrics());
    }

    public static String stringValueOfHour(int hours, int mins){
        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";


        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        return aTime;
    }

    public static String stringValueOfDate(int yy, int mm,int dd){
        String year = String.valueOf(yy);
        int mMonth = mm +1;
        String month = "";
        if(mMonth < 10){
            month = "0" + mMonth;
        }else{
            month = String.valueOf(mMonth);
        }

        String day = "";
        if(dd < 10){
            day = "0"+dd;
        }else{
            day = String.valueOf(dd);
        }

        return day +"/" + month + "/"+year;
    }
}
