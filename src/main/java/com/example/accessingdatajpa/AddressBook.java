package com.example.accessingdatajpa;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue
    @Column(name="addressBook_id")
    private Integer id;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressBook")
    private Collection<BuddyInfo> addressBook = new HashSet<>();

    public AddressBook(){}

    public AddressBook(Integer id) {
        this.id = id;
    }

    public Collection<BuddyInfo> getAddressBook() {
        return this.addressBook;
    }

    public boolean addBuddy(BuddyInfo buddy) {
        return this.addressBook.add(buddy);
    }

    public boolean resetBuddies(Collection<BuddyInfo> buddies) {
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
