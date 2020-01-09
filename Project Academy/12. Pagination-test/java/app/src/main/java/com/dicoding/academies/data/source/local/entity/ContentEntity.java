package com.dicoding.academies.data.source.local.entity;

import androidx.room.ColumnInfo;

public class ContentEntity {
    @ColumnInfo(name = "content")
    private String mContent;

    public ContentEntity(String content) {
        this.mContent = content;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }
}
