package com.example.accessingdatajpa;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue
    @Column(name="addressBook_id")
    private Integer id;

    //@JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private List<BuddyInfo> addressBook = new ArrayList<>();

    public AddressBook(){}

    public AddressBook(Integer id) {
        this.id = id;
    }

    public List<BuddyInfo> getAddressBook() {
        return this.addressBook;
    }

    public boolean addBuddy(BuddyInfo buddy) {
        return this.addressBook.add(buddy);
    }

    public boolean resetBuddies(List<BuddyInfo> buddies) {
        this.addressBook = buddies;
        return this.addressBook != null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toStr() {
        return "AddressBook{" +
                "id=" + id +
                ", buddies=" + addressBook +
                '}';
    }
}
