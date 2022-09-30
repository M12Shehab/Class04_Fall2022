package com.example.t3s2021;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class InsertCourseDialog extends DialogFragment {

    protected EditText titleEditText;
    protected EditText codeEditText;
    protected Button cancelButton;
    protected Button saveButton;


    public InsertCourseDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_course_dialog, container, false);

        titleEditText = view.findViewById(R.id.titleEditText);
        codeEditText = view.findViewById(R.id.codeEditText);
        cancelButton = view.findViewById(R.id.cancelButton);
        saveButton = view.findViewById(R.id.saveButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String code = codeEditText.getText().toString();

                if(!(title.equals("") || code.equals(""))) {
                    //Save in database
                    DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
                    dbHelper.insertCourse(new Course(-1,title,code));
                    ((MainActivity)getActivity()).loadTextView();
                    dismiss();
                    Toast.makeText(getActivity(), "Data saved successfully", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(), "Data must be entered in both fields", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}