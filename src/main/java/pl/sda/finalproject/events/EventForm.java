package pl.sda.finalproject.events;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class EventForm {

    @NotBlank
    private String title;
    @Size(min=20,message = "Opis musi zawierac conajmniej 20 znakow")
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startingDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endingDate;

    @Override
    public String toString() {
        return "EventForm =" +
                "title=  " + title + " description=  " + description + " startingDate=  " + startingDate + " endingDate=  " + endingDate;
    }
}
