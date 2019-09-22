package pl.sda.finalproject.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // getmapping to jest juz requestmapping ustawiony juz na metode get, przy requestmapping musimy napisac jaka metode  chcemy pisac
    @GetMapping("/")
    public String homePage() {
        return "homepage";
    }

    @GetMapping("/restricted")
    public String restrictedPage(Authentication authentication) {
        authentication.getName();
        return "restrictedPage";
    }
}
