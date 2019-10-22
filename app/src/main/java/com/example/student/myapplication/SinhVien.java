package com.example.student.myapplication;

public class SinhVien {
    private int Id;
    private String Name;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public SinhVien(int id, String name) {
        Id = id;
        Name = name;
    }

    @Override
    public String toString() {
        return "Id: " + getId() + " , Name: " + getName();
    }
}
