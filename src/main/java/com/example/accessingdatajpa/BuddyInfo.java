package com.example.accessingdatajpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
public class BuddyInfo {
    private String name;
    private String phoneNumber;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "addressBook_id")
    private AddressBook addressBook;
    @Id
    @GeneratedValue
    private Integer id;

    public BuddyInfo(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public BuddyInfo(String name, String phoneNumber, Integer id) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public BuddyInfo(String name, String phoneNumber, AddressBook addressBook) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressBook = addressBook;
    }

    public BuddyInfo() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "{Id: " + this.id + ", Name: " + this.name + ", Phone Number: " + this.phoneNumber + "}";
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }
}
