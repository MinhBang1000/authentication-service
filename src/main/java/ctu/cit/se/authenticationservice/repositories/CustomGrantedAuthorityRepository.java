package ctu.cit.se.authenticationservice.repositories;

import ctu.cit.se.authenticationservice.entities.CustomGrantedAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomGrantedAuthorityRepository extends JpaRepository<CustomGrantedAuthority, UUID> {
}
