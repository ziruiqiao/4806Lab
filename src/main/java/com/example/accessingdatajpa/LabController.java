package com.example.accessingdatajpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LabController {

    @Autowired
    private LabService labService;

    @GetMapping("/get/{id}")
    public AddressBook getAddressBook(@PathVariable("id") Integer id) {
        AddressBook abById = labService.getAbById(id);
        return abById;
    }

    @PostMapping("/post")
    public Boolean postAddressBook(@RequestBody AddressBook addressBook) {
        return labService.postAddressBook(addressBook);
    }

    @DeleteMapping("/del/{id}")
    public Boolean delAddressBook(@PathVariable("id") Integer id) {
        return labService.delAddressBook(id);
    }
}
