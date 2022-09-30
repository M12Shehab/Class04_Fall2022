package com.example.t3s2021;

public class Course {

    private long mId;
    private String mTitle;
    private String mCode;

    public Course(long id, String title, String code){
        this.mId = id;
        this.mTitle = title;
        this.mCode = code;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String mCode) {
        this.mCode = mCode;
    }
}
