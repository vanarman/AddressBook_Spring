package AddressBookApp.WebControllers;

import AddressBookApp.JPA.BuddyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buddyInfo")
public class BuddyInfoController {

    @Autowired
    private final BuddyInfoRepository buddyRepo;

    BuddyInfoController(BuddyInfoRepository buddyRepo) {
        this.buddyRepo = buddyRepo;
    }

    @GetMapping("")
    public String greeting(Model model) {
        model.addAttribute("buddyInfoList", buddyRepo.findAll());
        return "buddyInfo";
    }
}
