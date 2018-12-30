package com.example.mohamadreza.taskapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.mohamadreza.taskapp.models.Task;
import com.example.mohamadreza.taskapp.models.TaskLab;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

public class DescriptionActivity extends AppCompatActivity {

    private static final String EXTRA_TASK_ID_DEC = "com.example.mohamadreza.taskapp.task_id";

    private Task mTask;
    private TextView mTitle;
    private TextView mDescription;
    private TextView mDate;
    private TextView mTime;

    private Button mEdit;
    private Button mDelete;
    private Button mDone;


    public static Intent newIntent(Context context, UUID taskId) {
        Intent intent = new Intent(context, DescriptionActivity.class);
        intent.putExtra(EXTRA_TASK_ID_DEC, taskId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_description);

        UUID taskId = (UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID_DEC);
        mTask = TaskLab.getInstance().getTask(taskId);

        mTitle=findViewById(R.id.title_text_view);
        mDescription=findViewById(R.id.description_text_view);
        mDate=findViewById(R.id.date_text_view);
        mTime=findViewById(R.id.time_text_view);

        mTitle.setText(mTask.getTitle());
        mDescription.setText(mTask.getDescription());
        mDate.setText(mTask.getDate().toString());

        String pattern = "HH:mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("da", "DK"));
        String time = simpleDateFormat.format(mTask.getDate());

        mTime.setText(time);



    }
}
