package com.example.t3s2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    protected FloatingActionButton insertCourseButton;
    protected TextView mCourseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCourseListView = findViewById(R.id.CourseListView);
        insertCourseButton = findViewById(R.id.InsertCourseButton);

        loadTextView();

        insertCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DatabaseHelper dbHelper = new DatabaseHelper(getBaseContext());
                //dbHelper.insertCourse(new Course(-1, "Team project", "ELEC390"));

                InsertCourseDialog dialog = new InsertCourseDialog();
                dialog.show(getSupportFragmentManager(), "Insert Course");

                Log.d(TAG, "floating button onClick()");

                loadTextView();

            }
        });
    }

    public void loadTextView() {
        DatabaseHelper dbHelper = new DatabaseHelper((getBaseContext()));
        List<Course> courses = dbHelper.getAllCourses();

        String courseListString = "";

        for (int i = 0; i < courses.size(); i++) {
            courseListString = courseListString + courses.get(i).getTitle() + "\n"
                    + courses.get(i).getCode() + "\n";
        }
        mCourseListView.setText(courseListString);
    }
}