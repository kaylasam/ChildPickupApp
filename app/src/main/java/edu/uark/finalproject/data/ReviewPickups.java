package edu.uark.finalproject.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ReviewPickups {
    public static final String PICKUP_ID = "id";
    public static final String PICKUP_DATE = "date";
    public static final String PICKUP_TIME = "time";
    // TODO consider adding which vehicle/parent pickup the child up that day

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = PICKUP_DATE)
    private String date;

    @ColumnInfo(name = PICKUP_TIME)
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
