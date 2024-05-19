package ctu.cit.se.authenticationservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "user_name", nullable = false, unique = true, columnDefinition = "varchar(250)")
    private String username;
    @Column(name = "user_password", nullable = false, unique = true, columnDefinition = "text")
    private String password;
    @Column(name = "user_firstname", columnDefinition = "varchar(250)")
    private String firstname;
    @Column(name = "user_lastname", columnDefinition = "varchar(250)")
    private String lastname;
    @Column(name = "user_birthday")
    private LocalDate birthday;
    @Column(name = "user_account_non_expired", columnDefinition = "boolean default true")
    private boolean accountNonExpired;
    @Column(name = "user_account_non_locked", columnDefinition = "boolean default true")
    private boolean accountNonLocked;
    @Column(name = "user_credentials_non_expired", columnDefinition = "boolean default true")
    private boolean credentialsNonExpired;
    @Column(name = "user_enabled", columnDefinition = "boolean default true")
    private boolean enabled;
    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private Role role;
    @Override
    public Collection<CustomGrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String toString() {
        return "CustomUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday=" + birthday +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                '}';
    }
}
