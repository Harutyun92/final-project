package pl.sda.finalproject.user;
import lombok.Getter;
import lombok.Setter;
import pl.sda.finalproject.events.Event;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column
    private String nickname;

    @ManyToMany (mappedBy = "users")
    private Set<Role> roles = new HashSet<>();


    public void addRole(Role role) {
//        if (roles == null) {
//            roles = new HashSet<>();
//        }
        roles.add(role);
        role.addUser(this);
    }
}

