package edu.uark.finalproject.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Parents {

    public static final String PARENT_ID = "id";
    public static final String PARENT_NAME = "name";
    public static final String PARENT_PHONE = "phone";
    public static final String PARENT_EMAIL = "email";

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = PARENT_NAME)
    private String name;

    @ColumnInfo(name = PARENT_PHONE)
    private String phone;

    @ColumnInfo(name = PARENT_EMAIL)
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
