package thotran.android.messageplan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import thotran.android.messageplan.activity.R;

/**
 * Created by thotran on 12/7/17.
 */

public class WeekAdapter extends BaseAdapter {

    Context context;
    String[] mDays;
    public static String selectedDay = "";
    public static String selectedDayString = "";

    public WeekAdapter(Context context, String[] mDays) {
        this.context = context;
        this.mDays = mDays;
    }

    @Override
    public int getCount() {
        return mDays.length;
    }

    @Override
    public Object getItem(int position) {
        return mDays[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= convertView;
        if(v==null){
            v = LayoutInflater.from(context).inflate(R.layout.item_day,parent,false);
        }

        CheckBox chkDay;
        chkDay = (CheckBox)v.findViewById(R.id.chkDay);
        //chkDay.setId(position);
        chkDay.setText(getItem(position).toString());
        chkDay.setOnCheckedChangeListener((buttonView, isChecked) -> onItemCheckedChange(buttonView,isChecked,position));
        return v;
    }

    private void onItemCheckedChange(CompoundButton v, boolean isChecked, int position){
        if(isChecked){
            selectedDay += String.valueOf(position) + ";";
            selectedDayString +=getItem(position).toString() + ";";
        }else{
            selectedDay.replace(String.valueOf(position)+";","");
            selectedDayString.replace(getItem(position).toString() +";","");
        }
    }
}
