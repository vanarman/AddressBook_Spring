package AddressBookApp.WebControllers;

import AddressBookApp.JPA.AddressBookRepository;
import AddressBookApp.JPA.BuddyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    private final AddressBookRepository addressBookRepo;

    AddressBookController(AddressBookRepository addressRepo) {
        this.addressBookRepo = addressRepo;
    }

    @GetMapping("")
    public String greeting(Model model) {
        model.addAttribute("addressBookList", addressBookRepo.findAll());
        return "addressBook";
    }
}