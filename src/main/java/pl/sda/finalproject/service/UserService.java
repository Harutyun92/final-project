package pl.sda.finalproject.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.finalproject.events.Event;
import pl.sda.finalproject.exception.UserExistException;
import pl.sda.finalproject.user.*;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    public void registerUser(RegisterForm registerForm) {
        Optional<User> user = userRepository.findByUsername(registerForm.getUsername());
        if (user.isPresent()) {
            throw new UserExistException();
        }
        // TODO: save to database
        User newUser = new User();
        newUser.setUsername(registerForm.getUsername());
        newUser.setPasswordHash(passwordEncoder.encode(registerForm.getPassword()));
        newUser.setNickname(registerForm.getNickname());

        String roleUser = "ROLE_USER";  // tworzymy role o nazwie uzytkownik(role_users)
        String admin = "ADMIN";  // tworzymy role o nazwie uzytkownik(role_users)
        Optional<Role> role = roleRepository.findByRoleName(roleUser);  // tworzymy optionala role i w bazie danych sprawdzamy czy rola uzytkownik juz istnieje , nie bedzie istaniala bo przy zakladaniu konta nie podajemy role
        if (role.isPresent()) {   // sprawdzamy jesli rola istnieje w bazie danych
            newUser.addRole(role.get()); // i dodajemy nasza role do uzytkownika jesli isteniej ta rola to znajdzie ja i doda do uzytkownika , a jak nie istnieje to nizej piszemy metode na stworzenie roli i dodanie jej do uzytkownika
        } else {
            Role newRole = new Role();   // tworzymy role
            newRole.setRoleName(roleUser); // nadajemy jej nazwe "role_user"
            roleRepository.save(newRole);  // zapisujemy do bazy danych nowa utworzona role
            newUser.addRole(newRole);      // to uzytkownika dodajemy utworzona role
        }
        userRepository.save(newUser);  // zapisujemy gotowego uzytkownika w bazie
    }
}

