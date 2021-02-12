package AddressBookApp.JPA;

import javax.persistence.*;

@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue(generator = "buddyInfo", strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private AddressBook aBook;

    public BuddyInfo(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public BuddyInfo() {
        this.id = null;
        this.name = null;
        this.phoneNumber = null;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
