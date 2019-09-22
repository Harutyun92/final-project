package pl.sda.finalproject.user;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserContextService {

    //metoda zwracajaca nazwe aktualnie zalogowanego uzytkownika
    public String getLoggedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {  // sprawdza czy obiekt po lewej stronie(authentication) jest instancja klasy po prawej (Anonymous...)
            return null;
        }
        return authentication.getName();
    }
}
