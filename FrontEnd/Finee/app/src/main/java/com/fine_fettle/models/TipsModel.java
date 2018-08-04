package com.fine_fettle.models;

/**
 * Created by karthik on 4/8/18.
 */

public class TipsModel {
    public String mTitle;
    public String mDescription;
    public int mImageUrl;

    public TipsModel() {
    }

    public TipsModel(String mTitle, String mDescription, int image) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        mImageUrl = image;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(int mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
