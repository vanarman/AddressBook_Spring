package AddressBookApp.WebControllers;

import AddressBookApp.JPA.AddressBook;
import AddressBookApp.JPA.AddressBookRepository;
import AddressBookApp.JPA.BuddyInfo;
import AddressBookApp.JPA.BuddyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/buddyInfo")
public class BuddyInfoController {

    @Autowired
    private final BuddyInfoRepository buddyRepo;
    @Autowired
    private final AddressBookRepository addressBookRepo;

    BuddyInfoController(BuddyInfoRepository buddyRepo, AddressBookRepository addressBookRepo) {
        this.buddyRepo = buddyRepo;
        this.addressBookRepo = addressBookRepo;
    }

    @GetMapping("")
    public String greeting(Model model) {
        model.addAttribute("buddyInfoList", buddyRepo.findAll());
        return "buddyInfo";
    }

    @GetMapping("/addBuddy")
    public String buddyAdd(Model model) {
        model.addAttribute("addressBookList", addressBookRepo.findAll());
        model.addAttribute("buddyInfo", new BuddyInfo());
        model.addAttribute("addressBookId", new String());
        return "addBuddy";
    }

    @PostMapping("")
    public String buddySubmit(@ModelAttribute BuddyInfo buddy, String addressBookId, Model model) {
        AddressBook ab = addressBookRepo.findById(Long.parseLong(addressBookId));
        if(ab == null) {
            ab = new AddressBook();
            addressBookRepo.save(ab);
        }

        ab.setBuddies(buddy);
        addressBookRepo.save(ab);

        model.addAttribute("buddyInfoList", buddyRepo.findAll());
        return "buddyInfo";
    }
}
