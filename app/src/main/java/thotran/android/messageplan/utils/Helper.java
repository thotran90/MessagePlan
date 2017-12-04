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
}
