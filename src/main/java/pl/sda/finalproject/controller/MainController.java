package pl.sda.finalproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    // getmapping to jest juz requestmapping ustawiony juz na metode get, przy requestmapping musimy nadpisac wszystkie metody get post delete itp
    public String homePage(){
        return "homePage";
    }
}
