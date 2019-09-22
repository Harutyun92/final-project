package pl.sda.finalproject.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.finalproject.exception.UserExistException;
import pl.sda.finalproject.service.UserService;

import javax.validation.Valid;


@Controller
//@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public RegistrationController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        RegisterForm registerForm = new RegisterForm();
        //  registerForm.setUsername("alamakota");
        model.addAttribute("registerForm", registerForm);
        return "user/registerForm";
    }


    @PostMapping("/register") // to samo dajemy co w registerFrom.html w metodzie post co jest w action
    public String handleRegisterForm(@ModelAttribute @Valid RegisterForm registerForm,    //walidujemy rzeczy znajdujace sie w klasie RegisterForm na podtawie zaleznosci podanych w adnotacjach, obslugujemy formularz , mozemy pisac jako @RequestParam ale wtedy jest dluzszy kod
                                     BindingResult bindingResult,   // tworzymy przestrzen(pudelko) na bledy , nie beda nam sie wyswietlaly w normalny sposob bledy beda niewidoczne
                                     Model model)throws RuntimeException {    // wyniki rezultatu valid , maja byc wrzucone do kubełka blindresult
        System.out.println(registerForm);

        if (bindingResult.hasErrors()) {  // tutaj sprawdzamy czy mamy jakis blad w naszym pudelko utworzonym wyzej
            //     model.addAllAttributes("registerForm",registerForm);
            return "user/registerForm"; // jak jest blad to chcemy zeby znow pokazal sie nasz wypelniony formularz , zebysmy nie musieli od nowa wypelniac , tylko to pole ktore zle do poprawy
        }

        try {
            userService.registerUser(registerForm);  // controller wysyla do servisa zadanie o utworzenie uzytkownika , jesli taki uzytkownik juz bedzie istnial to w servisie napisalismy wyjatek ktory poleci , nastepnie ten wyjatek bedzie odeslany z servisu do controllera i tutaj musimy go zlapac

        } catch (UserExistException e) {
            bindingResult.rejectValue("username", "username.exist", "Uzytkownik o takim loginie juz istnieje");
            return "user/registerForm";
        }
        return "redirect:/thank-you-for-registering";
    }

    @GetMapping("/thank-you-for-registering")
    public String showRegisterThankYouPage() {
        return "user/registerFormSuccess";
    }
}


