package ctu.cit.se.authenticationservice.daos.imples;

import ctu.cit.se.authenticationservice.daos.ifaces.IRoleDAO;
import ctu.cit.se.authenticationservice.entities.Role;
import ctu.cit.se.authenticationservice.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDAO implements IRoleDAO {
    @Autowired
    private IRoleRepository IRoleRepository;

    @Override
    public void createInitData(List<Role> roles) {
        for (Role role : roles) {
            IRoleRepository.saveAndFlush(role);
        }
    }
}
