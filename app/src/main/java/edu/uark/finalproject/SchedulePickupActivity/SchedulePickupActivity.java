package edu.uark.finalproject.SchedulePickupActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TimePicker;


import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import edu.uark.finalproject.R;

public class SchedulePickupActivity extends AppCompatActivity {

    private CalendarView mCalendarView;
    int tpHour, tpMinute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_pickup);

        mCalendarView = (CalendarView) findViewById(R.id.schedulePickupCalendar);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 + "/" + i;
                Log.d("SchedulePickupActivity: ", date);
                
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
                    }
                }, 12, 0, false
                );
                // display previous selected time
                timePickerDialog.updateTime(tpHour, tpMinute);
                // show dialog
                timePickerDialog.show();
            }
        });

    }
}
