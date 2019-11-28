package com.cpsc411.hwassignment3.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Student extends PersistentObject {
    protected String mFirstName;
    protected String mLastName;
    protected String mCWID;
    protected ArrayList<CourseEnrollment> mCourses;

    public Student(String firstName, String lastName, String CWID) {
        mFirstName = firstName;
        mLastName = lastName;
        mCWID = CWID;
    }

    public Student() {}

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public ArrayList<CourseEnrollment> getCourses() {
        return mCourses;
    }

    public void setCourses(ArrayList<CourseEnrollment> courses) {
        mCourses = courses;
    }

    public String getCWID() {
        return mCWID;
    }

    public void setCWID(String CWID) {
        mCWID = CWID;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("FirstName", mFirstName);
        vals.put("LastName", mLastName);
        vals.put("CWID", mCWID);
        db.insert("Student", null, vals);
        for(int i=0; i < mCourses.size(); i++){
            mCourses.get(i).insert(db);
        }
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor cursor) {
        mFirstName = cursor.getString(cursor.getColumnIndex("FirstName"));
        mLastName = cursor.getString(cursor.getColumnIndex("LastName"));
        mCWID = cursor.getString(cursor.getColumnIndex("CWID"));

        mCourses = new ArrayList<CourseEnrollment>();
        Cursor c = db.query("CourseEnrollment", null, "Student=?", new String[]{mCWID}, null, null, null);
        if(c.getCount() > 0){
            while(c.moveToNext()){
                CourseEnrollment cObj = new CourseEnrollment();
                cObj.initFrom(db, c);
                mCourses.add(cObj);
            }
        }
    }
}
