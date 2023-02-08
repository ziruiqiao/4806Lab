package com.example.accessingdatajpa;

import com.example.accessingdatajpa.AddressBook;
import com.example.accessingdatajpa.BuddyInfo;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class AddressBookTest {

    private AddressBook addressBookUnderTest;

    @Before
    public void setUp() {
        addressBookUnderTest = new AddressBook();
    }

    @Test
    public void testAddBuddy() {
        // Setup
        final BuddyInfo buddy = new BuddyInfo(1, "mike", "testNumber");

        // Run the test
        final boolean result = addressBookUnderTest.addBuddy(buddy);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void getBuddies() {
        // Setup
        final BuddyInfo buddy1 = new BuddyInfo(1, "mike", "testNumber");
        final BuddyInfo buddy2 = new BuddyInfo(2, "tony", "testNumber2");
        addressBookUnderTest.addBuddy(buddy1);
        addressBookUnderTest.addBuddy(buddy2);
        Collection<BuddyInfo> buddies = addressBookUnderTest.getAddressBook();
        for (BuddyInfo buddy : buddies) {
            if (buddy.getId() == 1) assertEquals(buddy, buddy1);
            if (buddy.getId() == 2) assertEquals(buddy, buddy2);
        }
    }
}
