package com.debtapp.javafx.objects;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Arizel on 25.12.2016.
 */
public abstract class Person extends SimpleStringProperty {
    protected SimpleStringProperty name;
    protected SimpleStringProperty  phone;
    protected SimpleStringProperty address;
    protected SimpleStringProperty vkId;
    protected SimpleStringProperty ip;

    public Person(String name, String phone, String address, String vkId, String ip) {
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.vkId = new SimpleStringProperty(vkId);
        this.ip = new SimpleStringProperty(ip);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setVkId(String vkId) {
        this.vkId.set(vkId);
    }

    public void setIp(String ip) {
        this.ip.set(ip);
    }

    public String getName() {
        return name.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getVkId() {
        return vkId.get();
    }

    public String getIp() {
        return ip.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public SimpleStringProperty vkIdProperty() {
        return vkId;
    }

    public SimpleStringProperty ipProperty() {
        return ip;
    }

}
