package com.example.accessingdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyRepository extends CrudRepository<BuddyInfo, Integer> {

    List<BuddyInfo> findByPhoneNumber(String phoneNumber);
    List<BuddyInfo> findByName(String name);

    BuddyInfo findById(int id);
}