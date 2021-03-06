package com.example.haohoang.microsofttest.sutudentdata;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import static android.content.ContentValues.TAG;

/**
 * Created by tranh on 3/5/2017.
 */

public class Student {
    private String personid;
    private String name;
    private String userData;
    private String idStudent;

    public String getUrl() {
        return url;
    }

    private String url;

    @Override
    public String toString() {
        return "Student{" +
                "personid='" + personid + '\'' +
                ", name='" + name + '\'' +
                ", idStudent='" + idStudent + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    // Hàm này service trả về
    public Student(String name, String userData) {
        // userData có dạng maxSV+" "URL
        this.name = name;
        this.userData = userData;
        String[] strings = userData.split(" ");
        Log.e(TAG, String.format("Student: %s", userData) );
        this.idStudent=strings[0];
        this.url=strings[1];

        Log.e(TAG, String.format("Student: %s %s",idStudent,url ) );
    }
    public Student(StudentRespon studentRespon){
        this.name = studentRespon.getName();
        this.userData = studentRespon.getUserdata();
        this.personid=studentRespon.getPersonid();
        String[] strings = userData.split(" ");
        Log.e(TAG, String.format("Student: %s", userData) );
            this.idStudent = strings[0];
            this.url=strings[1];
    }

    // Hàm này do mình khởi tạo mới SInh viên
    public Student(String name, String idStudent, String url) {
        this.name = name;
        //bỏ hết dấu cách trong mã SV
        idStudent = idStudent.replaceAll("\\s", "");
        this.idStudent = idStudent;
        // bỏ hết dấu cách trong url
        url = url.replaceAll("\\s", "");
        this.url = url;
        userData = this.idStudent + " " + this.url;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

}
