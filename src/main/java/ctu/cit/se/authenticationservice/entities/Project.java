package ctu.cit.se.authenticationservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@Table(name = "projects")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "project_name", columnDefinition = "varchar(250) not null", unique = false)
    private String name;

    @OneToMany(mappedBy = "projectOfUsers")
    @ToString.Exclude
    private List<CustomUser> users = new ArrayList<>();

    @OneToMany(mappedBy = "projectOfRoles")
    @ToString.Exclude
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "projectOfAuthorities")
    @ToString.Exclude
    private List<CustomGrantedAuthority> authorities = new ArrayList<>();
}
