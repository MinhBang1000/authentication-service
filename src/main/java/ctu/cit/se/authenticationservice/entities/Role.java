package ctu.cit.se.authenticationservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "role_name", nullable = false, columnDefinition = "varchar(250)")
    private String name;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<CustomUser> users = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "role_project_id")
    private Project projectOfRoles;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    @JoinTable(name = "role_authorities",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<CustomGrantedAuthority> authorities = new HashSet<>();

    public void addAuthority(CustomGrantedAuthority authority) {
        if (Objects.isNull(authorities)) {
            authorities = new HashSet<>();
        }
        authorities.add(authority);
    }

    public void removeAuthority(CustomGrantedAuthority authority) {
        if (Objects.nonNull(authorities)) {
            authorities.removeIf(existedAuthority -> existedAuthority.getId().equals(authority.getId()));
        }
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
