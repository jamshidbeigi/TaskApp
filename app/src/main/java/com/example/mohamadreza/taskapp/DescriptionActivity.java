package com.example.mohamadreza.taskapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        mTitle = findViewById(R.id.title_text_view);
        mDescription = findViewById(R.id.description_text_view);
        mDate = findViewById(R.id.date_text_view);
        mTime = findViewById(R.id.time_text_view);
        mEdit = findViewById(R.id.button_edit);
        mDelete = findViewById(R.id.button_delete);
        mDone = findViewById(R.id.button_done);


        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddActivity.newIntent(DescriptionActivity.this, mTask.getId());
                startActivity(intent);
            }
        });

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DescriptionActivity.this);

                builder.setTitle("Select your answer.");

                builder.setMessage("Are you sure to delete?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaskLab.getInstance().removeTask(mTask);
                        onBackPressed();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        UUID taskId = (UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID_DEC);
        mTask = TaskLab.getInstance().getTask(taskId);

        mTitle.setText(mTask.getTitle());
        mDescription.setText(mTask.getDescription());
        mDate.setText(mTask.getDate().toString());

        String pattern = "HH:mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("da", "DK"));
        String time = simpleDateFormat.format(mTask.getDate());
        mTime.setText(time);
    }
}
