package pl.sda.finalproject.events;
import lombok.Getter;
import lombok.Setter;
import pl.sda.finalproject.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate startingDate;
    private LocalDate endingDate;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;




}

