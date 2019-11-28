package com.cpsc411.hwassignment3.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudentDB {
    protected ArrayList<Student> mStudents;

    protected SQLiteDatabase mSQLiteDatabase;

    public StudentDB(Context context) {
        File dbFile = context.getDatabasePath("student.db");
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);

        mSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Student (FirstName Text, LastName Text, CWID Text)");
        mSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CourseEnrollment (CourseID Text, Grade Text, Student Text)");

        //Reset data
        //createStudentDB();
    }

    public ArrayList<Student> getStudents() {
        return mStudents;
    }

    public void setStudents(ArrayList<Student> students) {
        mStudents = students;
    }

    public void addStudent(Student student){
        student.insert(mSQLiteDatabase);
    }

    public ArrayList<Student> retrieveStudentObjects(){
        mStudents = new ArrayList<Student>();
        Cursor c = mSQLiteDatabase.query("Student", null, null, null, null, null, null);
        if (c.getCount() > 0){
            while(c.moveToNext()){
                Student sObj = new Student();
                sObj.initFrom(mSQLiteDatabase, c);
                mStudents.add(sObj);
            }
        }
        return mStudents;
    }

    //Reset and initialize DB data
    protected void createStudentDB(){
        mSQLiteDatabase.delete("Student", null, null);
        mSQLiteDatabase.delete("CourseEnrollment", null, null);
        mSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Student (FirstName Text, LastName Text, CWID Text)");
        mSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CourseEnrollment (CourseID Text, Grade Text, Student Text)");

        Student s = new Student("John", "Do", "000000000");
        ArrayList<CourseEnrollment> courses = new ArrayList<CourseEnrollment>();
        courses.add(new CourseEnrollment("0", "A", s.getCWID()));
        courses.add(new CourseEnrollment("1", "B", s.getCWID()));
        s.setCourses(courses);
        s.insert(mSQLiteDatabase);

        s = new Student("Juan", "Martinez", "000000001");
        courses = new ArrayList<CourseEnrollment>();
        courses.add(new CourseEnrollment("0", "B", s.getCWID()));
        courses.add(new CourseEnrollment("1", "B", s.getCWID()));
        s.setCourses(courses);
        s.insert(mSQLiteDatabase);
    }
}
