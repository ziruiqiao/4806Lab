package com.example.accessingdatajpa;

import com.thoughtworks.qdox.model.expression.Add;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.Rollback;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
class LabControllerTest {

    @Autowired
    private LabController controller;

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
        addressBook.addBuddy(new BuddyInfo("Chloe", "O'Brian"));
        addressBook.addBuddy(new BuddyInfo("Kim", "Bauer"));
        addressBook.addBuddy(new BuddyInfo("David", "Palmer"));
        addressBook.addBuddy(new BuddyInfo("Michelle", "Dessler"));

        //repository.saveAll(addressBook.getBuddies());
        r2.save(addressBook);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @Order(4)
    public void contextLoads(){
        assertThat(controller).isNotNull();
    }

    @Test
    @Order(1)
    void postAddressBook() {
        AddressBook nuladr =  new AddressBook();
        AddressBook forObject = this.restTemplate.getForObject("http://localhost:" + port + "/get/2", AddressBook.class);
        assertThat(forObject.toStr()).isEqualTo(nuladr.toStr());

        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(new BuddyInfo(7,"Mike", "1234"));
        addressBook.addBuddy(new BuddyInfo(8, "Tony", "2345"));
        addressBook.addBuddy(new BuddyInfo(6, "Go", "3456"));
        AddressBook ret = this.restTemplate.postForObject("http://localhost:" + port + "/post", addressBook, AddressBook.class);
        assertThat(ret).isInstanceOf(AddressBook.class);

        AddressBook forObject2 = this.restTemplate.getForObject("http://localhost:" + port + "/get/" + ret.getId(), AddressBook.class);
        assertThat(forObject2.toStr()).isEqualTo(ret.toStr());
    }

    @Test
    @Order(2)
    void getAddressBook() {
        AddressBook forObject = this.restTemplate.getForObject("http://localhost:" + port + "/get/1", AddressBook.class);
        //System.out.println("forObject = " + forObject.toStr());
        assertThat(forObject).isInstanceOf(AddressBook.class);
    }

    @Test
    @Order(3)
    void delAddressBook() {
        AddressBook forObject = this.restTemplate.getForObject("http://localhost:" + port + "/get/1", AddressBook.class);
        System.out.println("forObject = " + forObject.toStr());
        assertThat(forObject).isInstanceOf(AddressBook.class);

        this.restTemplate.delete("http://localhost:" + port + "/del/1", 1);

        AddressBook nuladr =  new AddressBook();
        AddressBook forObject2 = this.restTemplate.getForObject("http://localhost:" + port + "/get/1", AddressBook.class);
        assertThat(forObject2.toStr()).isEqualTo(nuladr.toStr());
    }
}