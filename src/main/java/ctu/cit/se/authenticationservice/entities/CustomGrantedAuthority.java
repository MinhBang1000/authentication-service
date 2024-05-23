package ctu.cit.se.authenticationservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Objects;
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
    @Column(name = "authority_name", nullable = false, columnDefinition = "varchar(250)")
    private String authority;
    @Column(name = "authority_description", columnDefinition = "varchar(250)")
    private String description;
    @ManyToMany(mappedBy = "authorities")
    @ToString.Exclude
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "authority_project_id")
    private Project projectOfAuthorities;

    public void addRole(Role role) {
        if (Objects.isNull(roles)) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }

    public void removeRole(Role role) {
        if (Objects.nonNull(roles)) {
            roles.removeIf(existedRole -> role.getId().equals(existedRole.getId()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomGrantedAuthority authority = (CustomGrantedAuthority) o;
        return Objects.equals(id, authority.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
