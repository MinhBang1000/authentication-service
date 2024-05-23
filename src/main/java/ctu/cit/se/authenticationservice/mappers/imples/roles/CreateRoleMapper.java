package ctu.cit.se.authenticationservice.mappers.imples.roles;

import ctu.cit.se.authenticationservice.dtos.roles.CreateRoleReqDTO;
import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.entities.Role;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.repositories.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateRoleMapper implements IBaseMapper<CreateRoleReqDTO, Role> {
    @Autowired
    private IProjectRepository IProjectRepository;

    @Override
    public Role convert(CreateRoleReqDTO source) {
        Project foundProject = IProjectRepository.findById(UUID.fromString(source.getProjectId())).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.PROJECT_NOT_FOUND));
        return Role.builder()
                .name(source.getName())
                .projectOfRoles(foundProject)
                .build();
    }

    @Override
    public boolean validate(CreateRoleReqDTO source) {
        return true;
    }
}
