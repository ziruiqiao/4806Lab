package com.example.accessingdatajpa;

import com.thoughtworks.qdox.model.expression.Add;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ThymeController {

    @Autowired
    private LabService labService;

    @GetMapping("/thyme")
    public String getAddressBook(@RequestParam(value = "id", defaultValue = "1")
                                     Integer id, Model model) {
        AddressBook abById = labService.getAbById(id);
        model.addAttribute("addressBook", abById);
        return "greeting";
    }

    @PostMapping("/add")
    public String addAddressBook(AddressBook addressBook, Model model){
        AddressBook ad1 = labService.postAddressBook(addressBook);
        return getAddressBook(ad1.getId(), model);
    }
}
