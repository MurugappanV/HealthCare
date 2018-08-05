package com.fine_fettle.models;

import java.io.Serializable;

/**
 * Created by karthik on 4/8/18.
 */

public class TipsModel  implements Serializable{
    public String mTitle;
    public String mDescription;
    public String mImageUrl;
    public String mContent;

    public TipsModel() {
    }

    public TipsModel(String mTitle, String mDescription, String mImageUrl, String mContent) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mImageUrl = mImageUrl;
        this.mContent = mContent;
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

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }
}
