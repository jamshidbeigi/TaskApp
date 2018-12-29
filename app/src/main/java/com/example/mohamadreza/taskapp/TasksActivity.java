package com.example.mohamadreza.taskapp;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mohamadreza.taskapp.models.Task;
import com.example.mohamadreza.taskapp.models.TaskLab;

import java.util.List;
import java.util.UUID;

public class TasksActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TabItem mAll;
    private TabItem mDone;
    private FloatingActionButton mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        mTabLayout = findViewById(R.id.tab_layout);
        mAll = findViewById(R.id.all_tasks_tab);
        mDone = findViewById(R.id.done_tasks_tab);
        mAddButton = findViewById(R.id.floating_add_button);
        mViewPager = findViewById(R.id.task_view_pager);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new BlankFragment();
                }

            @Override
            public int getCount() {
                return mTabLayout.getTabCount();
            }
        });
//        mViewPager.setCurrentItem(0);



    }
}
