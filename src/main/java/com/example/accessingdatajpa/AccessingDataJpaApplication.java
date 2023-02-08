package com.example.accessingdatajpa;

import com.thoughtworks.qdox.model.expression.Add;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class);
	}

	@Bean
	public CommandLineRunner demo(BuddyRepository repository, AddressBookRepository r2) {
		return (args) -> {
			AddressBook addressBook = new AddressBook();
			addressBook.addBuddy(new BuddyInfo("Jack", "Bauer", addressBook));
			addressBook.addBuddy(new BuddyInfo("Chloe", "O'Brian", addressBook));
			addressBook.addBuddy(new BuddyInfo("Kim", "Bauer", addressBook));
			addressBook.addBuddy(new BuddyInfo("David", "Palmer", addressBook));
			addressBook.addBuddy(new BuddyInfo("Michelle", "Dessler", addressBook));

			//repository.saveAll(addressBook.getBuddies());
			r2.save(addressBook);

			log.info("AddressBook found with findAll():");
			log.info("--------------------------------------------");
			r2.findAll().forEach(a -> {
				log.info(a.toString());
				Integer addressBookId = a.getId();
				log.info("addressBookId = " + addressBookId);
				// fetch all buddyinfo
				log.info("BuddyInfos found with findByAddressBook_Id:");
				log.info("-------------------------------");
				for (BuddyInfo buddy : repository.findAllByAddressBookId(addressBookId)) {
					log.info(buddy.toString());
				}
				log.info("");
			});
			log.info("");
		};
	}

}
