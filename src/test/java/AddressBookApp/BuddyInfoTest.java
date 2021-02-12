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
public class BuddyInfoTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BuddyInfoRestController buddyInfoRestCtrl;

    @BeforeEach
    public void setUp() {
        BuddyInfo b1 = new BuddyInfo("Name 1", "Phone # 1");
        BuddyInfo b2 = new BuddyInfo("Name 2", "Phone # 2");
        BuddyInfo b3 = new BuddyInfo("Name 3", "Phone # 3");
        buddyInfoRestCtrl.create(b1);
        buddyInfoRestCtrl.create(b2);
        buddyInfoRestCtrl.create(b3);
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
    }

    @Test
    public void updateBuddieInfoById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .put("/api/buddyInfo/3")
            .content("{ \"id\": \"3\", \"name\": \"New Name 2\", \"phoneNumber\": \"New Phone # 2\" }")
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