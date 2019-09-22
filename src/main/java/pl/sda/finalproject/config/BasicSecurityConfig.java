package pl.sda.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

// piszemy konfiguracje dla spring security zeby nas przepuszczalo , bez tego blokuje i nie mamy dostepu do szablonu
@Configuration
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;  // potrzebne w jdbc do dostepu do bazy danych , on jest juz pod spodem my tylko go wyciagamy

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override // alt insert i wybieramy metode
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/restricted").authenticated()
                .antMatchers("/eventForm").authenticated()
                .anyRequest().permitAll() // przepuszcza kazde żądanie
                .and()
                .csrf().disable() // wylaczamy csrf , bledy zwiazane z 403
                .headers().frameOptions().disable() // zeby h2 dzialalo w przegladarce , springsecurity zeby nie blokowalo
                .and()
                .formLogin() // uzywamy formularza do logowania
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username") // podajemy parametr z pliku html z name username
                .passwordParameter("passwordParam") // podajemy parametr z pliku html
                .failureUrl("/login?error=true") // przy zlym zalogowaniu chcemy wrocic na ta sama strone, i dajemy ? i doklejamy parametr zebysmy wiedzieli ze cos poszlo nie tak przy logowaniu
                .defaultSuccessUrl("/eventForm") // po poprawnym zalogowaniu chcemy zeby wyladowal na nasza glowna strone
                //dodane z yt , jak wezmiemy logout to skieruje nas na strone glowna / ,
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/denied");  // jak ktos bedzie chcial sie dostac do strony do ktorej nie ma dostepu wyskoczy mu strona denied
    }

    @Override
    // dzieki tej metodzie bedziemy budowac menagera springsecurity , jak dostac sie do danego uzytkownika znajdujacego sie w bazie danych
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                //pobieranie uzytkownika i hasla i czy uzytkownik jest aktywny
                .usersByUsernameQuery("SELECT u.username, u.password_hash, 1 FROM user u WHERE u.username = ?") // piszemy selecta zeby uzyskac informacje o uzytkowniku na podstawie username z bazy danych , 1 jest to flaga aktywnosci ustawiamy 1 ze zawsze aktywni
                //pobieranie roli uzytkownika i na jej podstawie bedzie robione wiele rzeczy w zaleznosci jaka role ma uzytkownik
                .authoritiesByUsernameQuery("SELECT u.username, r.role_name " +  // select na zwrocenie wszystkich rol jakie posiada uzytkownik w bazie danych
                        "FROM user u " +
                        "JOIN role_users ur ON u.id = ur.users_id " +  // porownuje tabelki role i users i joinuje
                        "JOIN role r ON ur.roles_id = r.id " +
                        "WHERE u.username = ?")
                .passwordEncoder(passwordEncoder);
    }
}



