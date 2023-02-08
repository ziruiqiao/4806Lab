package com.example.accessingdatajpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepository extends CrudRepository<AddressBook, Integer> {
    AddressBook findById(int id);
}
