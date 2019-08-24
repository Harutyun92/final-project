package pl.sda.finalproject.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration   // piszemy konfiguracje dla spring security zeby nas przepuszczalo , bez tego blokuje i nie mamy dostepu do szablonu
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override  // alt insert i wybieramy metode
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll();   // przepuszcza kazde żądanie
    }
}
