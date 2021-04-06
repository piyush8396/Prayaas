package com.example.prayaas.Model;

public class UserAdmin {
    String  profilepic=null,username=null,userId=null,mail=null,password=null,phoneNumber=null,branch=null,center=null,curYear=null;
    boolean isAdmin=false;

    public UserAdmin(String profilepic, String username, String userId, String mail, String password, String phoneNumber, String branch, String center, String curYear, boolean isAdmin) {
        this.profilepic = profilepic;
        this.username = username;
        this.userId = userId;
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.branch = branch;
        this.center = center;
        this.curYear = curYear;
        this.isAdmin = isAdmin;
    }

    public UserAdmin(String username, String userId, String mail, String password, String phoneNumber, boolean isAdmin) {
        this.username = username;
        this.userId = userId;
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
    }

    public UserAdmin() {
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getCurYear() {
        return curYear;
    }

    public void setCurYear(String curYear) {
        this.curYear = curYear;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
