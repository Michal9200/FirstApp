package pl.sda.finalapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller()
public class UserController {

    @Autowired
    private RegistrationValidator registrationValidator;

    @GetMapping("/register")
    String registerForm(Model model){
        model.addAttribute("registrationUserDto", new RegistrationUserDto());
        model.addAttribute("countryList", Country.values());
        return "registerPage";
    }

    @PostMapping("/register")
    String registerUser(@ModelAttribute RegistrationUserDto registrationUserDto, Model model){
        Map<String, String> errorMap = registrationValidator.isValid(registrationUserDto);
        if(errorMap.isEmpty()){
            return"";
        }
        model.addAttribute("registrationUserDto", registrationUserDto);
        model.addAttribute("countryList", Country.values());
        model.addAllAttributes(errorMap);
        return "registerPage";
    }
}
