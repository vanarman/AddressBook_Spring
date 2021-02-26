package AddressBookApp.RestControllers;

import AddressBookApp.JPA.AddressBook;
import AddressBookApp.JPA.AddressBookRepository;
import AddressBookApp.JPA.BuddyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/addressBook")
public class AddressBookRestController {

    @Autowired
    private final AddressBookRepository addressRepo;

    AddressBookRestController(AddressBookRepository addressRepo) {
        this.addressRepo = addressRepo;
    }

    @GetMapping(path = "", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    List<AddressBook> all() {
        return (List<AddressBook>) addressRepo.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    AddressBook getById(@PathVariable("id") Long id) {
        Optional<AddressBook> abOpt = addressRepo.findById(id);
        if(abOpt.isPresent()) {
            return abOpt.get();
        }
        return null;
    }

    @PostMapping(path = "", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Object newAddressBook) {
        if(newAddressBook instanceof AddressBook) {
            return addressRepo.save((AddressBook) newAddressBook).getId();
        } else {
            return addressRepo.save(new AddressBook()).getId();
        }
    }

    @PostMapping(value = "/{id}/addBuddy", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void update(@PathVariable( "id" ) Long id, @RequestBody BuddyInfo resource) {
        Optional<AddressBook> addressBook = addressRepo.findById(id);
        if(addressBook.isPresent()) {
            AddressBook ab = addressBook.get();
            ab.addBuddy(resource);
            addressRepo.save(ab);
        } else {
            AddressBook ab = new AddressBook();
            ab.setBuddies(resource);
            addressRepo.save(ab);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        addressRepo.deleteById(id);
    }
}
