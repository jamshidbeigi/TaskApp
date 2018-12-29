package com.example.mohamadreza.taskapp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskLab {

    private static TaskLab instance;

    private List<Task> mTasks;

    private TaskLab() {
        mTasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Task crime = new Task();
            crime.setTitle("Crime#" + i);
            crime.setDone(i % 2 == 0);
            mTasks.add(crime);
        }
    }

    public static TaskLab getInstance() {
        if (instance == null)
            instance = new TaskLab();

        return instance;
    }

    public List<Task> getCrimes() {
        return mTasks;
    }

    public Task getTask(UUID id) {
        for (Task task : mTasks) {
            if (task.getId().equals(id))
                return task;
        }
        return null;
    }

    public int getIndexOfTask(UUID id) {
        List<Task> tasks = getCrimes();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id))
                return i;
        }
        return -1;
    }

    public void addTask(Task task) {
        mTasks.add(task);
    }

}
