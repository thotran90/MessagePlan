package thotran.android.messageplan.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import thotran.android.messageplan.activity.R;
import thotran.android.messageplan.adapter.WeekAdapter;
import thotran.android.messageplan.database.AppDatabase;
import thotran.android.messageplan.entities.Message;
import thotran.android.messageplan.entities.Template;
import thotran.android.messageplan.utils.Helper;

public class NewMessageFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TITLE = "Title";
    private static final String ARG_BODY = "Body";
    private static final String ARG_SENDTO = "SendTo";

    private static final String SCHEDULE_TIME_DIALOG = "schedule";
    private static final String MONTHLY_DATE_DIALOG = "monthly";
    private static final String ENDDATE_DATE_DIALOG = "enddate";
    // Initial parameter
    private String mTitle;
    private String mBody;
    private String mSendTo;
    // UI Component
    EditText txtTitle, txtSendTo, txtBody, txtSchedule, txtEndDate, txtDateMonthly;
    ImageButton btnShowDialogSchedule, btnShowDialogEndDate, btnShowDialogMonthly;
    Button btnSubmit;
    CheckBox chkIsRepeat, chkIsHasEndDate;
    RadioButton radDaily, radWeekly, radMonthly, radYearly;
    RadioGroup radGroupRepeat;
    GridView areaWeekly;
    LinearLayout areaMonthly, areaEndDate, areaRepeatable, inputEndDate;
    WeekAdapter weekAdapter;
    // Variable
    String[] mDay;
    String mSelectedDay;
    public NewMessageFragment() {
        // Required empty public constructor
    }

    public static NewMessageFragment newInstance(String title, String body, String sendTo) {
        NewMessageFragment fragment = new NewMessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_BODY, body);
        args.putString(ARG_SENDTO, sendTo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mBody = getArguments().getString(ARG_BODY);
            mSendTo = getArguments().getString(ARG_SENDTO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newmessage, container, false);
        // Update titles
        getActivity().setTitle("Add new message");
        initComponent(rootView);
        return rootView;
    }

    private void initComponent(View rootView) {
        txtTitle = (EditText)rootView.findViewById(R.id.txtTitle);
        if(!mTitle.isEmpty())
            txtTitle.setText(mTitle);
        txtSendTo = (EditText)rootView.findViewById(R.id.txtSendTo);
        if(!mSendTo.isEmpty())
            txtSendTo.setText(mSendTo);
        txtBody = (EditText)rootView.findViewById(R.id.txtBody);
        if(!mBody.isEmpty())
            txtBody.setText(mBody);
        initWeeklyPicker(rootView);

        txtSchedule = (EditText)rootView.findViewById(R.id.txtScheduleTime);
        txtEndDate = (EditText)rootView.findViewById(R.id.txtEndDate);
        txtDateMonthly = (EditText)rootView.findViewById(R.id.txtRepeatDateMonthly);

        chkIsRepeat = (CheckBox)rootView.findViewById(R.id.chkIsRepeat);
        chkIsRepeat.setOnCheckedChangeListener((buttonView, isChecked) -> onCheckedChangeIsRepeat(buttonView,isChecked));
        chkIsHasEndDate = (CheckBox)rootView.findViewById(R.id.chkHasEndDate);
        chkIsHasEndDate.setOnCheckedChangeListener((buttonView, isChecked) -> onCheckedChangeHasEndDate(isChecked));

        radDaily = (RadioButton)rootView.findViewById(R.id.radio_daily);
        radMonthly = (RadioButton)rootView.findViewById(R.id.radio_monthly);
        radWeekly = (RadioButton)rootView.findViewById(R.id.radio_weekly);
        radYearly = (RadioButton)rootView.findViewById(R.id.radio_yearly);

        radGroupRepeat = (RadioGroup)rootView.findViewById(R.id.radio_group_repeat);
        radGroupRepeat.setOnCheckedChangeListener((group, checkedId) -> onCheckedChangeRepeatType(group,checkedId));

        areaMonthly = (LinearLayout)rootView.findViewById(R.id.areaMonthly);
        areaEndDate = (LinearLayout)rootView.findViewById(R.id.areaEndDate);
        areaRepeatable = (LinearLayout)rootView.findViewById(R.id.areaRepeatable);
        inputEndDate = (LinearLayout)rootView.findViewById(R.id.inputEndDate);
        //Button
        btnShowDialogSchedule = (ImageButton) rootView.findViewById(R.id.btnShowTimePicker);
        btnShowDialogSchedule.setOnClickListener(v -> showTimePickerSchedule());
        btnShowDialogEndDate = (ImageButton)rootView.findViewById(R.id.btnShowTimePickerEndDate);
        btnShowDialogEndDate.setOnClickListener(v -> showTimePickerEndDate());
        btnShowDialogMonthly = (ImageButton)rootView.findViewById(R.id.btnShowTimePickerMonthly);
        btnShowDialogMonthly.setOnClickListener(v -> showTimePickerDateMonthly());
        btnSubmit = (Button)rootView.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> doSubmit());
    }

    private void initWeeklyPicker(View rootView) {
        areaWeekly = (GridView) rootView.findViewById(R.id.grdWeekly);
        mDay = getActivity().getResources().getStringArray(R.array.day_array);
        weekAdapter = new WeekAdapter(getActivity().getBaseContext(),mDay);
        areaWeekly.setAdapter(weekAdapter);
    }

    private void showTimePickerSchedule(){
        DialogFragment newFragment  = new TimePickerFragment();
        newFragment.show(getActivity().getFragmentManager(),SCHEDULE_TIME_DIALOG);
    }

    private void showTimePickerDateMonthly(){
        DialogFragment newFragment  = new MonthlyDatePickerFragment();
        newFragment.show(getActivity().getFragmentManager(),MONTHLY_DATE_DIALOG);
    }

    private void showTimePickerEndDate(){
        DialogFragment newFragment  = new EndDateDatePickerFragment();
        newFragment.show(getActivity().getFragmentManager(),ENDDATE_DATE_DIALOG);
    }

    private void doSubmit(){
        boolean isValid = validateMessage();
        if(isValid){
            Message message = new Message();
            message.setTitle(txtTitle.getText().toString());
            message.setBody(txtBody.getText().toString());
            message.setTo(txtSendTo.getText().toString());
            message.setSendingTime(txtSchedule.getText().toString());
            boolean isRepeat = chkIsRepeat.isChecked();
            message.setIsRepeat(String.valueOf(isRepeat));
            if(isRepeat){
                boolean isHasEndDate = chkIsHasEndDate.isChecked();
                message.setIsHasEndDate(String.valueOf(isHasEndDate));
                if(isHasEndDate){
                    message.setEndDate(txtEndDate.getText().toString());
                }
                int repeatType = radGroupRepeat.getCheckedRadioButtonId();
                switch (repeatType){
                    case R.id.radio_daily:
                        message.setIsRepeatDaily(String.valueOf(true));
                        break;
                    case R.id.radio_weekly:
                        message.setIsRepeatWeekly(String.valueOf(true));
                        message.setRepeatWeeklyValue(weekAdapter.selectedDay);
                        break;
                    case R.id.radio_monthly:
                        message.setIsRepeatMonthly(String.valueOf(true));
                        message.setRepeatMonthlyDate(txtDateMonthly.getText().toString());
                        break;
                    case R.id.radio_yearly:
                        message.setIsRepeatYearly(String.valueOf(true));
                        message.setRepeatYearlyDate(txtDateMonthly.getText().toString());
                        break;
                }
            }
            AppDatabase.getAppDatabase(getActivity().getBaseContext()).messageDao().insertAll(message);
            Toast.makeText(getActivity().getBaseContext(),"Saved",Toast.LENGTH_SHORT).show();
            redirectToMessageList();
        }
    }

    private void redirectToMessageList() {
        Fragment message = new MessageFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, message).commit();
    }

    private boolean validateMessage(){
        String title = txtTitle.getText().toString();
        if(title.isEmpty()){
            Toast.makeText(getActivity().getBaseContext(),"Input message title before submit.",Toast.LENGTH_SHORT).show();
            return false;
        }
        String content = txtBody.getText().toString();
        if(content.isEmpty()){
            Toast.makeText(getActivity().getBaseContext(),"Can not send empty message.",Toast.LENGTH_SHORT).show();
            return false;
        }
        String sendTo = txtSendTo.getText().toString();
        if(sendTo.isEmpty()){
            Toast.makeText(getActivity().getBaseContext(),"Input receiver number.",Toast.LENGTH_SHORT).show();
            return  false;
        }
        boolean isRepeat = chkIsRepeat.isChecked();
        if(isRepeat){
            boolean isHasEndDate = chkIsHasEndDate.isChecked();
            if(isHasEndDate){
                String endDate = txtEndDate.getText().toString();
                if(endDate.isEmpty()){
                    Toast.makeText(getActivity().getBaseContext(),"Input end date before submit.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                int selectedPlan = radGroupRepeat.getCheckedRadioButtonId();
                switch (selectedPlan){
                    case R.id.radio_daily:
                        break;
                    case R.id.radio_monthly:
                        String monthlyDate = txtDateMonthly.getText().toString();
                        if(monthlyDate.isEmpty()){
                            Toast.makeText(getActivity().getBaseContext(),"Selected repeat date.",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        break;
                    case R.id.radio_yearly:
                        String yearlyDate = txtDateMonthly.getText().toString();
                        if(yearlyDate.isEmpty()){
                            Toast.makeText(getActivity().getBaseContext(),"Select repeat date.",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        break;
                    case R.id.radio_weekly:
                        mSelectedDay = weekAdapter.selectedDay;
                        if(mSelectedDay.isEmpty()){
                            Toast.makeText(getActivity().getBaseContext(),"Select daty want to send message",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        break;
                }
            }
        }
        return true;
    }

    private void onCheckedChangeIsRepeat(CompoundButton button, boolean isChecked){
        if(isChecked){
            areaRepeatable.setVisibility(View.VISIBLE);
            areaEndDate.setVisibility(View.VISIBLE);
        }else{
            areaEndDate.setVisibility(View.GONE);
            areaRepeatable.setVisibility(View.GONE);
            resetRepeatArea();
        }
    }

    private void onCheckedChangeHasEndDate(boolean isChecked){
        if(isChecked){
            inputEndDate.setVisibility(View.VISIBLE);
        }else{
            inputEndDate.setVisibility(View.GONE);
        }
    }

    private void resetRepeatArea(){
        areaMonthly.setVisibility(View.GONE);
        areaWeekly.setVisibility(View.GONE);
    }

    private void onCheckedChangeRepeatType(RadioGroup group, int checkedId){
        switch (checkedId){
            case R.id.radio_daily:
                resetRepeatArea();
                break;
            case R.id.radio_monthly:
                resetRepeatArea();
                areaMonthly.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_weekly:
                resetRepeatArea();
                areaWeekly.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_yearly:
                resetRepeatArea();
                areaMonthly.setVisibility(View.VISIBLE);
                break;
            default:
                resetRepeatArea();
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @SuppressLint("ValidFragment")
    public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        public TimePickerFragment() {
            // Required empty public constructor
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker

            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            txtSchedule.setText(Helper.stringValueOfHour(hourOfDay,minute));
        }
    }

    @SuppressLint("ValidFragment")
    public class MonthlyDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        public MonthlyDatePickerFragment() {
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),this,yy,mm,dd);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            txtDateMonthly.setText(Helper.stringValueOfDate(year,month,dayOfMonth));
        }
    }

    @SuppressLint("ValidFragment")
    public class EndDateDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        public EndDateDatePickerFragment() {
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),this,yy,mm,dd);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            txtEndDate.setText(Helper.stringValueOfDate(year,month,dayOfMonth));
        }
    }

}
