package com.example.prayaas.Model;

public class bookclass {

    private String course_name;
    private int course_rating;
    private int course_image;
    private String grade;

    // Constructor
    public bookclass(String course_name, int course_rating, int course_image, String grade) {
        this.course_name = course_name;
        this.course_rating = course_rating;
        this.course_image = course_image;
        this.grade = grade;
    }

    public bookclass(String course_name, String grade) {
        this.course_name = course_name;
        this.grade = grade;
    }

    public bookclass() {
    }

    // Getter and Setter
    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getCourse_rating() {
        return course_rating;
    }

    public void setCourse_rating(int course_rating) {
        this.course_rating = course_rating;
    }

    public int getCourse_image() {
        return course_image;
    }

    public void setCourse_image(int course_image) {
        this.course_image = course_image;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}