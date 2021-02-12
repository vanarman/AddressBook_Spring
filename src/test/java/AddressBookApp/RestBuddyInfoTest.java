package AddressBookApp;

import AddressBookApp.JPA.BuddyInfo;
import AddressBookApp.RestControllers.BuddyInfoRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebAppConfiguration
@SpringBootTest
public class RestBuddyInfoTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BuddyInfoRestController buddyInfoRestCtrl;

    @BeforeEach
    public void setUp() {
        BuddyInfo b1 = new BuddyInfo("Name 1", "Phone # 1");
        BuddyInfo b2 = new BuddyInfo("Name 2", "Phone # 2");
        BuddyInfo b3 = new BuddyInfo("Name 3", "Phone # 3");
        BuddyInfo b4 = new BuddyInfo("Name 4", "Phone # 4", "Address of 4");
        BuddyInfo b5 = new BuddyInfo("Name 5", "Phone # 5", "Address of 5");
        buddyInfoRestCtrl.create(b1);
        buddyInfoRestCtrl.create(b2);
        buddyInfoRestCtrl.create(b3);
        buddyInfoRestCtrl.create(b4);
        buddyInfoRestCtrl.create(b5);
    }

    @Test
    public void addBuddyInfo() throws Exception {
        mvc.perform( MockMvcRequestBuilders
            .post("/api/buddyInfo")
            .content(asJsonString(new BuddyInfo("Buddy 1", "Phone # 1")))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void deleteBuddyInfo() throws Exception {
        mvc.perform( MockMvcRequestBuilders
            .delete("/api/buddyInfo/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void getAllBuddiesInfo() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .get("/api/buddyInfo")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void getBuddieInfoById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .get("/api/buddyInfo/2")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("2"))
            .andExpect(jsonPath("$.name").value("Name 2"))
            .andExpect(jsonPath("$.phoneNumber").value("Phone # 2"));

        mvc.perform(MockMvcRequestBuilders
                .get("/api/buddyInfo/4")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("4"))
                .andExpect(jsonPath("$.name").value("Name 4"))
                .andExpect(jsonPath("$.phoneNumber").value("Phone # 4"))
                .andExpect(jsonPath("$.address").value("Address of 4"));
    }

    @Test
    public void updateBuddieInfoById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .put("/api/buddyInfo/5")
            .content("{ \"id\": \"5\", \"name\": \"New Name 5\", \"phoneNumber\": \"New Phone # 5\", \"address\": \"New address of 5\" }")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}