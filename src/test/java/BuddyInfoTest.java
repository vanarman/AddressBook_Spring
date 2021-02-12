//import AddressBookApp.JPA.BuddyInfo;
//import org.junit.Before;
//import org.junit.After;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class BuddyInfoTest {
//    BuddyInfo buddy1;
//    BuddyInfo buddy2;
//    BuddyInfo buddy3;
//
//    @Before
//    public void setUp() throws Exception {
//        buddy1 = new BuddyInfo("James", "613 224-23-45");
//        buddy2 = new BuddyInfo("Nicolas", "");
//        buddy3 = new BuddyInfo("Emily", null);
//    }
//
//    @After
//    public void takeDown() throws Exception {
//        buddy1 = null;
//        buddy2 = null;
//        buddy3 = null;
//    }
//
//    @Test
//    public void getName() {
//        assertEquals("James", buddy1.getName());
//        assertNotEquals("James", buddy2.getName());
//        assertNotEquals("James", buddy3.getName());
//
//        assertEquals("Nicolas", buddy2.getName());
//        assertNotEquals("Nicolas", buddy1.getName());
//        assertNotEquals("Nicolas", buddy3.getName());
//
//        assertEquals("Emily", buddy3.getName());
//        assertNotEquals("Emily", buddy1.getName());
//        assertNotEquals("Emily", buddy2.getName());
//    }
//
//    @Test
//    public void setName() {
//        buddy1.setName("Adam");
//        assertEquals("Adam", buddy1.getName());
//        assertNotEquals("James", buddy1.getName());
//        assertNotEquals("Nicolas", buddy1.getName());
//        assertNotEquals("Emily", buddy1.getName());
//
//        buddy2.setName("Carl");
//        assertEquals("Carl", buddy2.getName());
//        assertNotEquals("James", buddy2.getName());
//        assertNotEquals("Nicolas", buddy2.getName());
//        assertNotEquals("Emily", buddy2.getName());
//
//        buddy3.setName("Carol");
//        assertEquals("Carol", buddy3.getName());
//        assertNotEquals("James", buddy3.getName());
//        assertNotEquals("Nicolas", buddy3.getName());
//        assertNotEquals("Emily", buddy3.getName());
//    }
//
//    @Test
//    public void getPhoneNUmber() {
//        assertEquals("613 224-23-45", buddy1.getPhoneNumber());
//        assertNotEquals("", buddy1.getPhoneNumber());
//        assertNotEquals(null, buddy1.getPhoneNumber());
//
//        assertEquals("", buddy2.getPhoneNumber());
//        assertNotEquals("613 224-23-45", buddy2.getPhoneNumber());
//        assertNotEquals(null, buddy2.getPhoneNumber());
//
//        assertEquals(null, buddy3.getPhoneNumber());
//        assertNotEquals("613 224-23-45", buddy3.getPhoneNumber());
//        assertNotEquals("", buddy3.getPhoneNumber());
//    }
//
//    @Test
//    public void setPhoneNUmber() {
//        buddy1.setPhoneNumber("613 662-95-23");
//        assertEquals("613 662-95-23", buddy1.getPhoneNumber());
//        assertNotEquals("613 224-23-45", buddy1.getPhoneNumber());
//
//        buddy2.setPhoneNumber(null);
//        assertEquals(null, buddy2.getPhoneNumber());
//        assertNotEquals("", buddy2.getPhoneNumber());
//
//        buddy3.setPhoneNumber("123 453-23-78");
//        assertEquals("123 453-23-78", buddy3.getPhoneNumber());
//        assertNotEquals(null, buddy3.getPhoneNumber());
//    }
//}