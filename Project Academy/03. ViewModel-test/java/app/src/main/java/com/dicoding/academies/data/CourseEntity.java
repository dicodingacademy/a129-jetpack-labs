package com.dicoding.academies.data;

public class CourseEntity {
    private String courseId;
    private String title;
    private String description;
    private String deadline;
    private boolean bookmarked = false;
    private String imagePath;

    public CourseEntity(String courseId, String title, String description, String deadline, Boolean bookmarked, String imagePath) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        if (bookmarked != null) {
            this.bookmarked = bookmarked;
        }
        this.imagePath = imagePath;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String mCourseId) {
        this.courseId = mCourseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        this.title = mTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        this.description = mDescription;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String mDeadline) {
        this.deadline = mDeadline;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean mBookmarked) {
        this.bookmarked = mBookmarked;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String mImagePath) {
        this.imagePath = mImagePath;
    }
}
