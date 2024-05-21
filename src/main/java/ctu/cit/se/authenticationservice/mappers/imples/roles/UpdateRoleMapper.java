package ctu.cit.se.authenticationservice.mappers.imples.roles;

import ctu.cit.se.authenticationservice.dtos.roles.UpdateRoleReqDTO;
import ctu.cit.se.authenticationservice.entities.Role;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class UpdateRoleMapper implements IBaseMapper<UpdateRoleReqDTO, Role> {
    @Autowired
    private IRoleRepository IRoleRepository;

    @Override
    public Role convert(UpdateRoleReqDTO source) {
        Role role = IRoleRepository.findById(UUID.fromString(source.getId())).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.ROLE_NOT_FOUND)
        );
        return Role.builder()
                .id(UUID.fromString(source.getId()))
                .name(Objects.nonNull(source.getName()) ? source.getName() : role.getName())
                .projectOfRoles(role.getProjectOfRoles())
                .build();
    }
}
