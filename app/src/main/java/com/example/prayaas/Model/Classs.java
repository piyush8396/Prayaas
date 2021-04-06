package com.example.prayaas.Model;

public class Classs {
    String Name;
    int no;

    public Classs(String name,int no) {
        Name = name;
        this.no=no;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
