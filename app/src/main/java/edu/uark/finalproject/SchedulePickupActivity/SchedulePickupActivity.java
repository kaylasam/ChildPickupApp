package edu.uark.finalproject.SchedulePickupActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import edu.uark.finalproject.R;

public class SchedulePickupActivity extends AppCompatActivity {

    private CalendarView mCalendarView;

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

                // todo onClick of a date, take user to page that will allow for pickup time adjustments specific to day selected
            }
        });

    }
}
