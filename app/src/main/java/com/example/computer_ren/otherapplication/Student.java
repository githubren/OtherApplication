package com.example.computer_ren.otherapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private String name;
//    private int age;
    private String sex;

    protected Student(Parcel in) {
        name = in.readString();
//        age = in.readInt();
        sex = in.readString();
    }

    public Student(String name,/* int age,*/ String sex) {
        this.name = name;
//        this.age = age;
        this.sex = sex;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
//        dest.writeInt(age);
        dest.writeString(sex);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
//                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
