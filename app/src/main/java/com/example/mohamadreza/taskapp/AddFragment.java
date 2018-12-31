package com.example.mohamadreza.taskapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.mohamadreza.taskapp.models.Task;
import com.example.mohamadreza.taskapp.models.TaskLab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    private static final String ARG_TASK_ID = "taskId";
    private static final String DIALOG_TAG = "DialogDate";
    public static final String EXTRA_IS_EMPTY = "com.example.mohamadreza.taskapp.isEmpty";

    private static final int REQ_DATE_PICKER = 0;
    private static final int REQ_TIME_PICKER = 1;

    private Task mTask;

    private EditText mTitleField;
    private EditText mDescription;
    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mSolvedCheckBox;
    private Button mDone;

    public static AddFragment newInstance(UUID taskId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID, taskId);
        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID taskId = (UUID) getArguments().getSerializable(ARG_TASK_ID);
        mTask = TaskLab.getInstance().getTask(taskId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_task, container, false);

        mTitleField = view.findViewById(R.id.title_edit_text);
        mDescription = view.findViewById(R.id.description_text_view);
        mDateButton = view.findViewById(R.id.button_date);
        mTimeButton = view.findViewById(R.id.button_time);
        mSolvedCheckBox = view.findViewById(R.id.check_box_is_done);
        mDone = view.findViewById(R.id.button_done_add);

        mTitleField.setText(mTask.getTitle());
        mDescription.setText(mTask.getDescription());

        String simpleDate = getDateString();
        mDateButton.setText(simpleDate);

        String date = getTimeString();
        mTimeButton.setText(date.toString());

        mSolvedCheckBox.setChecked(mTask.isDone());

        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTask.getTitle() != null)
                    getActivity().finish();

                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("you dont create a Task!");
                    builder.setMessage("Write a Title");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });


        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTask.setDone(isChecked);
            }
        });

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mTask.getDate());
                datePickerFragment.setTargetFragment(AddFragment.this,
                        REQ_DATE_PICKER);
                datePickerFragment.show(getFragmentManager(), DIALOG_TAG);
            }
        });

        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment = TimePickerFragment.newInstance(mTask.getDate());
                timePickerFragment.setTargetFragment(AddFragment.this,
                        REQ_TIME_PICKER);
                timePickerFragment.show(getFragmentManager(), DIALOG_TAG);
            }
        });

        return view;
    }

    public String getDateString() {
        String pattern1 = "E,yyyy/MM/dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1, new Locale("en", "US"));
        return simpleDateFormat1.format(mTask.getDate());
    }


    public String getTimeString() {
        String pattern = "HH:mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "US"));
        return simpleDateFormat.format(mTask.getDate());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK)
            return;

        if (requestCode == REQ_DATE_PICKER) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

            mTask.setDate(date);
            String simpleDate = getDateString();
            mDateButton.setText(simpleDate);

        } else if (requestCode == REQ_TIME_PICKER) {
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mTask.setDate(date);

            String simpleDate = getDateString();
            mDateButton.setText(simpleDate);

            String time = getTimeString();

            mTimeButton.setText(time);
        }
    }

}