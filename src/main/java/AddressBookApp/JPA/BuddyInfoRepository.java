package AddressBookApp.JPA;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {
    List<BuddyInfo> findByName(String name);
    List<BuddyInfo> findByPhoneNumber(String phone);
    BuddyInfo findById(long id);
    void deleteById(long id);
}