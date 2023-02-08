package com.example.accessingdatajpa;

import com.thoughtworks.qdox.model.expression.Add;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LabService {

    private final AddressBookRepository abr;
    private final BuddyRepository br;

    @Autowired
    public LabService(AddressBookRepository abr, BuddyRepository br) {
        this.abr = abr;
        this.br = br;
    }

    public AddressBook getAbById(Integer id) {
        AddressBook addressBook;
        Optional<AddressBook> byId = abr.findById(id);
        addressBook = byId.get();
        addressBook.setId(id);
//        Iterable<BuddyInfo> all = br.findAllByAddressBookId(id);
//
//        for (BuddyInfo buddy : all) {
//            addressBook.addBuddy(buddy);
//        }
        System.out.println("addressBook = " + addressBook.toStr());
        return addressBook;
    }

    public AddressBook postAddressBook(AddressBook addressBook) {
        Collection<BuddyInfo> buddies = addressBook.getAddressBook();
        List<BuddyInfo> buddyInfos = new ArrayList<>();

        AddressBook save = abr.save(addressBook);
//        Iterable<BuddyInfo> save2 = br.saveAll(buddies);
        System.out.println("addressBook = " + save.toStr());

        return save;
    }

    public Boolean delAddressBook(Integer id) {
        //br.deleteAllByAddressBook_Id(id);
        abr.deleteById(id);
        return true;
    }
}
