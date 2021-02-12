//import AddressBookApp.JPA.AddressBook;
//import AddressBookApp.JPA.BuddyInfo;
//import org.junit.After;
//import org.junit.Before;
//
//public class AddressBookTest {
//    AddressBook ab1;
//    AddressBook ab2;
//
//    @Before
//    public void setUp() throws Exception {
//        ab1 = new AddressBook();
//
//        BuddyInfo b1 = new BuddyInfo("Buddy 1", "12345");
//        BuddyInfo b2 = new BuddyInfo("Buddy 2", "67890");
//
//        ab1.setBuddies(b1);
//        ab1.setBuddies(b2);
//
//        ab2 = new AddressBook();
//    }
//
//    @After
//    public void takeDown() throws Exception {
//        ab1 = null;
//        ab2 = null;
//    }
//}