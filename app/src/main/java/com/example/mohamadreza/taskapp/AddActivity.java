package com.example.mohamadreza.taskapp;

import android.content.Context;
import android.content.Intent;

import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class AddActivity extends AppCompatActivity {

    private static final String EXTRA_Task_ID = "com.example.mohamadreza.taskapp.task_id";

    public static Intent newIntent(Context context, UUID taskId) {
        Intent intent = new Intent(context, AddActivity.class);
        intent.putExtra(EXTRA_Task_ID, taskId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        UUID currentTaskId = (UUID) getIntent().getSerializableExtra(EXTRA_Task_ID);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.container_fragment) == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.container_fragment, AddFragment.newInstance(currentTaskId))
                    .commit();
        }

    }
}
