package com.example.mohamadreza.taskapp.models;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Task {
    private UUID mId;
    private String mTitle;
    private String mDescription;
    private Date mDate;
    private boolean mDone;

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean done) {
        mDone = done;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Task( ) {
        mId = UUID.randomUUID();
        mDate = new Date();
    }
}
