package com.dicoding.academies.data.source.remote.response;


import android.os.Parcel;
import android.os.Parcelable;

public class ModuleResponse implements Parcelable {
    private String moduleId;
    private String courseId;
    private String title;
    private int position;

    public ModuleResponse(String moduleId, String courseId, String title, int position) {
        this.moduleId = moduleId;
        this.courseId = courseId;
        this.title = title;
        this.position = position;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.moduleId);
        dest.writeString(this.courseId);
        dest.writeString(this.title);
        dest.writeInt(this.position);
    }

    private ModuleResponse(Parcel in) {
        this.moduleId = in.readString();
        this.courseId = in.readString();
        this.title = in.readString();
        this.position = in.readInt();
    }

    public static final Creator<ModuleResponse> CREATOR = new Creator<ModuleResponse>() {
        @Override
        public ModuleResponse createFromParcel(Parcel source) {
            return new ModuleResponse(source);
        }

        @Override
        public ModuleResponse[] newArray(int size) {
            return new ModuleResponse[size];
        }
    };
}

