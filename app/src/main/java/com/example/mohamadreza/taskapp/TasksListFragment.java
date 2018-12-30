package com.example.mohamadreza.taskapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamadreza.taskapp.models.Task;
import com.example.mohamadreza.taskapp.models.TaskLab;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TasksListFragment extends Fragment {

    private static final String ARG_TAB_POSITION = "tab.position";

    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;

    public static TasksListFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(ARG_TAB_POSITION, position);
        TasksListFragment fragment = new TasksListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TasksListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_show, container, false);

        mRecyclerView = view.findViewById(R.id.tasks_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        List<Task> tasks = TaskLab.getInstance().getTasks();

        int tabPosition = getArguments().getInt(ARG_TAB_POSITION);
        if (tabPosition == 1) {
            List<Task> mTasks = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.isDone())
                    mTasks.add(task);
            }
            tasks = mTasks;
        }
        if (mTaskAdapter == null) {
            mTaskAdapter = new TaskAdapter(tasks);
            mRecyclerView.setAdapter(mTaskAdapter);
        } else {
            mTaskAdapter.setTasks(tasks);
            mTaskAdapter.notifyDataSetChanged();
        }
    }

    private class TaskHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mWordTextView;

        private Task mTask;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.list_item_task_title);
            mDateTextView = itemView.findViewById(R.id.list_item_task_date);
            mWordTextView = itemView.findViewById(R.id.text_view_first_word);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getActivity(), mTask.getTitle() + " clicked!", Toast.LENGTH_LONG).show();
                    Intent intent = DescriptionActivity.newIntent(getActivity(), mTask.getId());
                    startActivity(intent);
                }
            });
        }

        public void bind(Task task) {
            mTask = task;

            mTitleTextView.setText(task.getTitle());
            mDateTextView.setText(task.getDate().toString());
            char s = task.getTitle().charAt(0);
            String str = s + "";
            mWordTextView.setText(str);
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

        private List<Task> mTasks;

        public TaskAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_task, parent, false);
            TaskHolder taskHolder = new TaskHolder(view);
            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = mTasks.get(position);
            holder.bind(task);

        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }
}


