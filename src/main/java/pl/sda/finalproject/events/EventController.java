package pl.sda.finalproject.events;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.finalproject.service.EventService;

import javax.validation.Valid;

@Controller
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/eventForm")
    public String showEventForm(Model model) {     // trzeba utworzyc model zebysmy mogli potem w eventForm.html dodac jako th:object
        EventForm eventForm = new EventForm();      // th:object potem pozwala nam na tworzenie th field i error do wyswietlania bledow przy prowadzaniu danych
        model.addAttribute("eventForm", eventForm);
        return "user/eventForm";
    }


    @PostMapping("/eventForm") // to samo dajemy co w registerFrom.html w metodzie post co jest w action
    public String handleEventForm(@ModelAttribute @Valid EventForm eventForm,    //walidujemy rzeczy znajdujace sie w klasie RegisterForm na podtawie zaleznosci podanych w adnotacjach, obslugujemy formularz , mozemy pisac jako @RequestParam ale wtedy jest dluzszy kod
                                  BindingResult bindingResult,   // tworzymy przestrzen(pudelko) na bledy , nie beda nam sie wyswietlaly w normalny sposob bledy beda niewidoczne
                                  Model model) {    // wyniki rezultatu valid , maja byc wrzucone do kubełka blindresult
        System.out.println(eventForm);

        if (bindingResult.hasErrors()) {  // tutaj sprawdzamy czy mamy jakis blad w naszym pudelko utworzonym wyzej
            //     model.addAllAttributes("registerForm",registerForm);
            return "user/eventForm"; // jak jest blad to chcemy zeby znow pokazal sie nasz wypelniony formularz , zebysmy nie musieli od nowa wypelniac , tylko to pole ktore zle do poprawy
        }

        eventService.saveEvent(eventForm);

        return "redirect:/thank-you-for-addingEvent";
    }

    @GetMapping("/thank-you-for-addingEvent")
    public String showAddEventThankYouPage() {
        return "user/eventFormSuccess";
    }
}
