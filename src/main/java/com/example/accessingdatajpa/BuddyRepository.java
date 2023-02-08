package com.example.accessingdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyRepository extends CrudRepository<BuddyInfo, Integer> {

    List<BuddyInfo> findByPhoneNumber(String phoneNumber);
    List<BuddyInfo> findByName(String name);

    List<BuddyInfo> findAllByAddressBookId(Integer Id);
    BuddyInfo findById(int id);

    Integer deleteAllByAddressBook_Id(Integer id);
}