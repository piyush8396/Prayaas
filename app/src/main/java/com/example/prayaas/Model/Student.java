package com.example.prayaas.Model;

import android.net.Uri;

public class Student {
    String name, center, profilepic, phone, address, father_name, mother_name, father_number, teacher;
    int grade, age;

    public Student(String name, String center, String profilepic, String phone, String address, String father_name, String mother_name, String father_number, String teacher, int grade, int age) {
        this.name = name;
        this.center = center;
        this.profilepic = profilepic;
        this.phone = phone;
        this.address = address;
        this.father_name = father_name;
        this.mother_name = mother_name;
        this.father_number = father_number;
        this.teacher = teacher;
        this.grade = grade;
        this.age = age;
    }

    public Student(String name, String center, int grade,String phone) {
        this.name = name;
        this.phone=phone;
        this.center = center;
        this.grade = grade;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getFather_number() {
        return father_number;
    }

    public void setFather_number(String father_number) {
        this.father_number = father_number;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}