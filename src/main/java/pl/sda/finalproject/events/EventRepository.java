package pl.sda.finalproject.events;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.finalproject.user.User;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {



    Optional<Event> findByTitle(String title);
}
