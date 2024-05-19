package ctu.cit.se.authenticationservice.daos.ifaces;

import ctu.cit.se.authenticationservice.entities.Role;

import java.util.List;

public interface IRoleDAO {
    void createInitData(List<Role> roles);
}
