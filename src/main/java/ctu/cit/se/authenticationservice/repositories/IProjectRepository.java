package ctu.cit.se.authenticationservice.repositories;

import ctu.cit.se.authenticationservice.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProjectRepository extends JpaRepository<Project, UUID> {
    Optional<Project> findByName(String name);
}
