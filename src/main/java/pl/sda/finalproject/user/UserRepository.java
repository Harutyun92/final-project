package pl.sda.finalproject.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { // typ encji , typ klucza glownego

// na poczatku musimy utworzyc w service to co chcemy czyli np usera i zapisac do bazy danych i dopiero potem metode w repozytorium zeby juz bylo co wyciagac albo sprawdzac na bazie danych
    // metoda musi sie konkretnie tak nazywac bo na podstawie niej tworzy selecta ( zapytanie) w jezyku sql i wysyla do bazy danych
    Optional<User> findByUsername(String username);


}

