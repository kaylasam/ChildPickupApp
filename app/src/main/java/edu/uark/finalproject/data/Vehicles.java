package edu.uark.finalproject.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vehicles {

    public static final String VEHICLE_ID = "id";
    public static final String VEHICLE_MAKE = "make";
    public static final String VEHICLE_MODEL = "model";
    public static final String VEHICLE_COLOR = "color";

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = VEHICLE_MAKE)
    private String make;

    @ColumnInfo(name = VEHICLE_MODEL)
    private String model;

    @ColumnInfo(name = VEHICLE_COLOR)
    private String color;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
