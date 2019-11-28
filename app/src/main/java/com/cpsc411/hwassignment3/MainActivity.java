package com.cpsc411.hwassignment3;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cpsc411.hwassignment3.adapter.SummaryLVAdapter;
import com.cpsc411.hwassignment3.model.CourseEnrollment;
import com.cpsc411.hwassignment3.model.Student;
import com.cpsc411.hwassignment3.model.StudentDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected ListView mSummaryView;
    protected SummaryLVAdapter ad;
    protected StudentDB mStudentDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStudentDB = new StudentDB(this);
        mStudentDB.retrieveStudentObjects();

        setContentView(R.layout.summary_listview);
        mSummaryView = findViewById(R.id.summary_list_view_id);
        ad = new SummaryLVAdapter(mStudentDB);
        mSummaryView.setAdapter(ad);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.summary_screen_menu, menu);
        menu.findItem(R.id.action_add).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, StudentAddActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStudentDB.retrieveStudentObjects();
        ad.notifyDataSetChanged();
    }
}
