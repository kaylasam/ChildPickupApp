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

    private boolean checkTimeInRange(int hour, int minute) {
        boolean inRange;

        // if parent has set time less than 3:00PM or greater than 4:00PM
        if (hour > 16 && minute > 0 || hour < 15)
            inRange = false;
        else
            inRange = true;

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
                Calendar calendar = Calendar.getInstance();
                // set hour and minute
                calendar.set(0, 0, 0, tpHour, tpMinute);
                Log.d("SchedulePickupActivity: ", "time selected: " + hour + ":" + minute);

                isInRange = checkTimeInRange(tpHour, tpMinute);
                displayScheduledPickupToast(isInRange, tpHour, tpMinute);
            }
        }, 12, 0, false
        );


        // display previous selected time
        //timePickerDialog.updateTime(tpHour, tpMinute);

        // show dialog
        timePickerDialog.show();
    }


}
