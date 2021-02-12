package AddressBookApp.JPA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue(generator = "addressBook", strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn()
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<BuddyInfo> buddies;

    public AddressBook() { buddies = null; }

    public Long getId() { return this.id; }

    public void setId(Long id) { this.id = id; }

    public List<BuddyInfo> getBuddies() { return (List<BuddyInfo>) buddies; }

    public void setBuddies(Collection<BuddyInfo> buddies) { this.buddies = buddies; }

    public void setBuddies(BuddyInfo buddy) {
        if (this.buddies == null) {
            this.buddies = new ArrayList<>();
        }

        this.buddies.add(buddy);
    }

    public void addBuddy(BuddyInfo newBuddy) {
        this.buddies.add(newBuddy);
    }
}
