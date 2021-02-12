package AddressBookApp;

import AddressBookApp.JPA.AddressBook;
import AddressBookApp.JPA.BuddyInfo;
import AddressBookApp.RestControllers.AddressBookRestController;
import AddressBookApp.RestControllers.BuddyInfoRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebAppConfiguration
@SpringBootTest
public class RestAddressBookTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private AddressBookRestController addressBookRestCtrl;

    @BeforeEach
    public void setUp() {
        AddressBook ab1 = new AddressBook();
        AddressBook ab2 = new AddressBook();
        BuddyInfo b1 = new BuddyInfo("Name 1", "Phone # 1");
        BuddyInfo b2 = new BuddyInfo("Name 2", "Phone # 2");
        BuddyInfo b3 = new BuddyInfo("Name 3", "Phone # 3");
        BuddyInfo b4 = new BuddyInfo("Name 4", "Phone # 4");
        BuddyInfo b5 = new BuddyInfo("Name 5", "Phone # 5");

        ab1.setBuddies(b1);
        ab1.setBuddies(b2);
        ab1.setBuddies(b3);

        ab2.setBuddies(b4);
        ab2.setBuddies(b5);

        addressBookRestCtrl.create(ab1);
        addressBookRestCtrl.create(ab2);
    }

    @Test
    public void getAllAddressBooks() throws Exception {
        mvc.perform( MockMvcRequestBuilders
            .get("/api/addressBook")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void getAddressBooksById() throws Exception {
        mvc.perform( MockMvcRequestBuilders
            .get("/api/addressBook/2")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void addBuddyToAddressBook() throws Exception {
        mvc.perform( MockMvcRequestBuilders
            .post("/api/addressBook/2/addBuddy")
            .content("{ \"name\": \"New Man For AddrB 2\", \"phoneNumber\": \"Phone of new Buddy\" }")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void deleteAddressBookById() throws Exception {
        mvc.perform( MockMvcRequestBuilders
            .delete("/api/addressBook/1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        mvc.perform( MockMvcRequestBuilders
            .get("/api/addressBook/1")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

//    @Test
//    public void addEmptyAddressBook() throws Exception {
//        AddressBook abTest = new AddressBook();
//
//        mvc.perform( MockMvcRequestBuilders
//            .post("/api/addressBook")
//            .content(asJsonString(abTest))
//            .contentType(MediaType.APPLICATION_JSON)
//            .accept(MediaType.APPLICATION_JSON))
//            .andDo(print())
//            .andExpect(status().isCreated());
//    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
