package com.example.accessingdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
class ThymeControllerTest {
    @Value(value="${local.server.port}")
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AddressBookRepository r2;


    @BeforeEach
    void setUp() {
        r2.deleteAll();
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(new BuddyInfo("Jack", "Bauer"));
        addressBook.addBuddy(new BuddyInfo("Chloe", "OBrian"));
        addressBook.addBuddy(new BuddyInfo("Kim", "Bauer"));
        addressBook.addBuddy(new BuddyInfo("David", "Palmer"));
        addressBook.addBuddy(new BuddyInfo("Michelle", "Dessler"));

        r2.save(addressBook);
    }

    @Test
    void getAddressBookTest(){
        setUp();
        List<AddressBook> all = r2.findAllBy();
        System.out.println("thymeleaf-----------------------------------------------------------------------------------------------------------");
        //System.out.println("all = " + all);
        //System.out.println(all.get(0).getId());
        String forObject = this.restTemplate.getForObject("http://localhost:" + port + "/thyme?id=7", String.class);
        System.out.println("forObject = " + forObject);
        assertThat(forObject).contains("Chloe").contains("OBrian");
    }

}