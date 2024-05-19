package ctu.cit.se.authenticationservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "authorities")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomGrantedAuthority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "authority_name", unique = true, nullable = false, columnDefinition = "varchar(250)")
    private String authority;
    @ManyToMany(mappedBy = "authorities")
    @ToString.Exclude
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
}
