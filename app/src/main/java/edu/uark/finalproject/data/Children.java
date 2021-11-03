package edu.uark.finalproject.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Children {

    public static final String CHILD_ID = "id";
    public static final String CHILD_NAME = "name";
    public static final String CHILD_AGE = "age";
    public static final String CHILD_GRADE = "grade";

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = CHILD_NAME)
    private String name;

    @ColumnInfo(name = CHILD_AGE)
    private Integer age;

    @ColumnInfo(name = CHILD_GRADE)
    private Integer grade;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
