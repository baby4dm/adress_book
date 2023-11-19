package com.example.adress_book;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleStringProperty pip = new SimpleStringProperty("");
    private SimpleStringProperty phone = new SimpleStringProperty("");

    public Person(String pip, String Phone) {
        this.pip = new SimpleStringProperty(pip);
        this.phone = new SimpleStringProperty(Phone);
    }

    public String getPip() {
        return pip.get();
    }

    public SimpleStringProperty pipProperty() {
        return pip;
    }

    public void setPip(String pip) {
        this.pip.set(pip);
    }

    public String getPhone() {
        return phone.get();
    }
    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    @Override
    public String toString() {
        return "Person{" +
                "PIP=" + pip +
                ", Phone=" + phone +
                '}';
    }
}
