package AddressBookApp;

import AddressBookApp.JPA.AddressBook;
import AddressBookApp.JPA.AddressBookRepository;
import AddressBookApp.JPA.BuddyInfo;
import AddressBookApp.JPA.BuddyInfoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AddressBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(AddressBookRepository ardRepository,
                                  BuddyInfoRepository buddyRepository) {
        return (args) -> {
            // initialize a few buddies
            BuddyInfo b1, b2, b3, b4, b5;
            b1 = new BuddyInfo("Jack", "+1 (613) 456-12-54");
            b2 = new BuddyInfo("Chloe", "+1 (613) 674-23-82");
            b3 = new BuddyInfo("Kim", "+1 (613) 351-97-34");
            b4 = new BuddyInfo("David", "+1 (613) 949-91-82", "Address of 4");
            b5 = new BuddyInfo("Michelle", "+1 (613) 210-02-81");

            // initialize an address book 1
            AddressBook a1 = new AddressBook();
            a1.setBuddies(b1);
            a1.setBuddies(b2);
            a1.setBuddies(b3);

            // initialize an address book 2
            AddressBook a2 = new AddressBook();
            a2.setBuddies(b4);
            a2.setBuddies(b5);

            // save initialized address books
            ardRepository.save(a1);
            ardRepository.save(a2);

            buddyRepository.save(b1);
            buddyRepository.save(b2);
            buddyRepository.save(b3);
            buddyRepository.save(b4);
            buddyRepository.save(b5);
        };
    }
}
