package AddressBookApp.JPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {
    AddressBook findById(long id);
    void deleteById(long id);
}
