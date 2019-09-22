package pl.sda.finalproject.user;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.*;

@Getter
@Setter
@ToString(exclude = "password")  // ominac password przy wyswietlaniu , zeby nie pokazywalo przy wieswietlaniu jakie mamy haslo ustawione
public class RegisterForm {

 //  @NotNull
   @NotEmpty(message = "Pole nie może być puste.")
  // @NotBlank
  // @Pattern(regexp = ".+@.+") // patern sprawdza warunek czy jest spelniony , tutaj musimy byc wiecej niz jeden znak , malpa i po niej jeden lub wiecej znak
   @Email
   private String username;
   @Size(min=8,max=30,message = "Hasło musi mieć min 8 znaków i max 30 znaków")
   @NotEmpty
   private String password;
   @NotBlank
   @Size(max=50,message = "Pole nie może być puste, ani zawierać samych białych znaków, a maksymalna długość to 50 znaków")
   private String nickname;

}
