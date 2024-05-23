package ctu.cit.se.authenticationservice.mappers.imples.users;

import ctu.cit.se.authenticationservice.dtos.users.CreateUserReqDTO;
import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.entities.Role;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.repositories.IProjectRepository;
import ctu.cit.se.authenticationservice.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateUserMapper implements IBaseMapper<CreateUserReqDTO, CustomUser> {
    @Autowired
    private IProjectRepository IProjectRepository;
    @Autowired
    private IRoleRepository IRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomUser convert(CreateUserReqDTO source) {
        Project foundProject = IProjectRepository.findById(UUID.fromString(source.getProjectId())).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.PROJECT_NOT_FOUND)
        );
        Role foundRole = IRoleRepository.findById(UUID.fromString(source.getRoleId())).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.ROLE_NOT_FOUND)
        );
        if (!validate(source)) {
            /* Still working */
            throw new IllegalArgumentException(CustomExceptionMessage.PASSWORD_CONFIRM_NOT_MATCH);
        }
        return CustomUser.builder()
                .username(source.getUsername())
                .password(passwordEncoder.encode(source.getPassword()))
                .firstname(source.getFirstname())
                .lastname(source.getLastname())
                .birthday(source.getBirthday())
                .projectOfUsers(foundProject)
                .role(foundRole)
                .build();
    }

    @Override
    public boolean validate(CreateUserReqDTO source) {
        return true;
    }
}
