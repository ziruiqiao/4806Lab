package com.example.accessingdatajpa;

import com.example.accessingdatajpa.BuddyInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuddyInfoTest {

    private BuddyInfo buddyInfoUnderTest;

    @Before
    public void setUp() {
        buddyInfoUnderTest = new BuddyInfo(1, "name", "phoneNumber");
    }

    @Test
    public void testToString() {
        assertEquals("{Id: 1, Name: name, Phone Number: phoneNumber}", buddyInfoUnderTest.toString());
    }

    @Test
    public void testGetterSetters() {
        assertEquals(buddyInfoUnderTest.getName(), "name");
        assertEquals(buddyInfoUnderTest.getPhoneNumber(), "phoneNumber");
        buddyInfoUnderTest.setName("mike");
        buddyInfoUnderTest.setPhoneNumber("mikeNumber");
        assertEquals(buddyInfoUnderTest.getName(), "mike");
        assertEquals(buddyInfoUnderTest.getPhoneNumber(), "mikeNumber");
    }
}
