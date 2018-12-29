package com.example.mohamadreza.taskapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.UUID;

public class DesciptionActivity extends AppCompatActivity {

    private static final String EXTRA_TASK_ID = "com.example.mohamadreza.taskapp.task_id";

    public static Intent newIntent(Context context, UUID crimeId) {
        Intent intent = new Intent(context, DesciptionActivity.class);
        intent.putExtra(EXTRA_TASK_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_description);
    }
}
