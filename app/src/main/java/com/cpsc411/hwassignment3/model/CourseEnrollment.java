package com.cpsc411.hwassignment3.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CourseEnrollment extends PersistentObject {
    protected String mCourseID;
    protected String mGrade;
    protected String mStudent;

    public CourseEnrollment(String courseID, String grade, String student) {
        mCourseID = courseID;
        mGrade = grade;
        mStudent = student;
    }

    public CourseEnrollment() {}

    public String getCourseID() {
        return mCourseID;
    }

    public void setCourseID(String courseID) {
        mCourseID = courseID;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    public String getStudent() {
        return mStudent;
    }

    public void setStudent(String student) {
        mStudent = student;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("CourseID", mCourseID);
        vals.put("Grade", mGrade);
        vals.put("Student", mStudent);
        db.insert("CourseEnrollment", null, vals);
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor cursor) {
        mCourseID = cursor.getString(cursor.getColumnIndex("CourseID"));
        mGrade = cursor.getString(cursor.getColumnIndex("Grade"));
        mStudent = cursor.getString(cursor.getColumnIndex("Student"));
    }
}
