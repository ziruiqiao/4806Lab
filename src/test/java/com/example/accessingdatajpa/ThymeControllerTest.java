package com.example.accessingdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
        System.out.println("all = " + all);
        System.out.println(all.get(0).getId());
        String forObject = this.restTemplate.getForObject("http://localhost:" + port + "/thyme?id=9", String.class);
        System.out.println("forObject = " + forObject);
        assertThat(forObject).contains("Chloe").contains("OBrian");
    }

    @Test
    void addAddressBook() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("addressBook[0].name", "Tony");
        map.add("addressBook[0].phoneNumber", "12345");
        map.add("addressBook[1].name", "Mike");
        map.add("addressBook[1].phoneNumber", "54321");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> entity = this.restTemplate.postForEntity("http://localhost:" + port + "/add", request, String.class);

        //System.out.println("entity = " + entity.toString());
        assertThat(entity.toString()).contains("Tony").contains("Mike").contains("12345").contains("54321");
    }
}