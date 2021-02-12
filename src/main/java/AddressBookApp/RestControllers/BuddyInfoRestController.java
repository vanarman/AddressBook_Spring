package AddressBookApp.RestControllers;

import AddressBookApp.JPA.BuddyInfo;
import AddressBookApp.JPA.BuddyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/buddyInfo")
public class BuddyInfoRestController {

    @Autowired
    private final BuddyInfoRepository buddyRepo;

    BuddyInfoRestController(BuddyInfoRepository buddyRepo) {
        this.buddyRepo = buddyRepo;
    }

    @GetMapping(path = "", produces = "application/json")
    @ResponseBody
    List<BuddyInfo> all() {
        return (List<BuddyInfo>) buddyRepo.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    BuddyInfo getById(@PathVariable("id") Long id) {
        Optional<BuddyInfo> biOpt = buddyRepo.findById(id);
        if(biOpt.isPresent()) {
            return biOpt.get();
        }
        return null;
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Long create(@RequestBody BuddyInfo newBuddy) {
        return buddyRepo.save(newBuddy).getId();
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable( "id" ) Long id, @RequestBody BuddyInfo resource) {
        buddyRepo.save(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        buddyRepo.deleteById(id);
    }
}
