package com.gmail.fomichov.m.model;

public class Customer {
    private long id;
    private String name;
    private int capitalization;
    private String founder;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(int capitalization) {
        this.capitalization = capitalization;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capitalization=" + capitalization +
                ", founder='" + founder + '\'' +
                '}';
    }
}
