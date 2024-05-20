package ctu.cit.se.authenticationservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "project_name", columnDefinition = "varchar(250) not null", unique = false)
    private String name;

    @OneToMany(mappedBy = "projectOfUsers")
    private List<CustomUser> users = new ArrayList<>();

    @OneToMany(mappedBy = "projectOfRoles")
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "projectOfAuthorities")
    private List<CustomGrantedAuthority> authorities = new ArrayList<>();
}
