package ctu.cit.se.authenticationservice.mappers.imples.roles;

import ctu.cit.se.authenticationservice.dtos.roles.RetrieveRoleResDTO;
import ctu.cit.se.authenticationservice.entities.Role;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetrieveRoleMapper implements IBaseMapper<Role, RetrieveRoleResDTO> {
    @Autowired
    private IRoleRepository IRoleRepository;

    @Override
    public RetrieveRoleResDTO convert(Role source) {
        return RetrieveRoleResDTO.builder()
                .id(source.getId().toString())
                .name(source.getName())
                .projectId(source.getProjectOfRoles().getId().toString())
                .build();
    }
}
