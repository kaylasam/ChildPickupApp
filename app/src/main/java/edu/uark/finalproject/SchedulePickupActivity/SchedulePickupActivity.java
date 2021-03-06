package edu.uark.finalproject.SchedulePickupActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import edu.uark.finalproject.R;

public class SchedulePickupActivity extends AppCompatActivity {

    private CalendarView mCalendarView;
    private Calendar mCalendarStartPickupTime, mCalendarEndPickupTime, scheduledTime;
    int tpHour, tpMinute;
    Boolean isInRange;
    EditText etPickupTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_pickup);

        etPickupTime = findViewById(R.id.editTextTime);
        etPickupTime.setFocusable(false);
        etPickupTime.setClickable(true);

        setOpeningAndClosingTimes();
        mCalendarView = (CalendarView) findViewById(R.id.schedulePickupCalendar);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 + "/" + i;
                Log.d("SchedulePickupActivity: ", date);
            }
        });

        // on click of scheduled time, display time picker
        etPickupTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTimePicker();
            }
        });
    }

    private boolean checkTimeInRange() {
        boolean inRange;

        if(scheduledTime.after(mCalendarStartPickupTime) && scheduledTime.before(mCalendarEndPickupTime))
            inRange = true;
        else
            inRange = false;
        return inRange;
    }

    private void displayScheduledPickupToast(boolean inRange, int hour, int minute){
        //if out of range, display toast
        if(!inRange) {
            Log.d("SchedulePickupActivity: ", "not in range");
            Toast toast = Toast.makeText(getApplication(),
                    "Time selected not in range! Please select a time between 3:00 PM and 4:00 PM.",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 300);
            toast.show();
        }
        else {
            if (minute == 0) {
                Log.d("SchedulePickupActivity: ", "in range");
                Toast toast = Toast.makeText(getApplication(),
                        "Scheduled pickup time updated to " + (hour - 12) + ":00" + " PM",
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 300);
                toast.show();
            }
            else {
                Toast toast = Toast.makeText(getApplication(),
                        "Scheduled pickup time updated to " + (hour - 12) + ":" + minute + " PM",
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 300);
                toast.show();
            }
        }
    }

    private void displayTimePicker(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                SchedulePickupActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                // initialize hour and minute variable
                tpHour = hour;
                tpMinute = minute;
                // initialize calendar
                scheduledTime = Calendar.getInstance();
                // set hour and minute
                scheduledTime.set(Calendar.HOUR_OF_DAY, hour);
                scheduledTime.set(Calendar.MINUTE, minute);
                Log.d("SchedulePickupActivity: ", "time selected: " + hour + ":" + minute);

                isInRange = checkTimeInRange();
                Log.d("SchedulePickupActivity: ", isInRange.toString());
                displayScheduledPickupToast(isInRange, tpHour, tpMinute);
            }
        }, 12, 0, false
        );


        // display previous selected time
        //timePickerDialog.updateTime(tpHour, tpMinute);

        // show dialog
        timePickerDialog.show();
    }

    private void setOpeningAndClosingTimes() {
        // set start pickup time
        mCalendarStartPickupTime = Calendar.getInstance();
        mCalendarStartPickupTime.set(Calendar.HOUR, 3);
        mCalendarStartPickupTime.set(Calendar.MINUTE, 00);
        mCalendarStartPickupTime.set(Calendar.AM_PM, Calendar.PM);

        // set end pickup time
        mCalendarEndPickupTime = Calendar.getInstance();
        mCalendarEndPickupTime.set(Calendar.HOUR, 4);
        mCalendarEndPickupTime.set(Calendar.MINUTE, 00);
        mCalendarEndPickupTime.set(Calendar.AM_PM, Calendar.PM);
    }


}
