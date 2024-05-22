package ctu.cit.se.authenticationservice.mappers.imples.users;

import ctu.cit.se.authenticationservice.dtos.roles.RetrieveRoleResDTO;
import ctu.cit.se.authenticationservice.dtos.users.RetrieveUserResDTO;
import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.entities.Role;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.repositories.ICustomUserRepository;
import ctu.cit.se.authenticationservice.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetrieveUserMapper implements IBaseMapper<CustomUser, RetrieveUserResDTO> {
    @Autowired
    private ICustomUserRepository userRepository;

    @Override
    public RetrieveUserResDTO convert(CustomUser source) {
        CustomUser foundUser = userRepository.findById(source.getId()).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.USER_NOT_FOUND)
        );
        /* Still working */
        return RetrieveUserResDTO.builder()
                .id(foundUser.getId().toString())
                .username(foundUser.getUsername())
                .firstname(foundUser.getFirstname())
                .lastname(foundUser.getLastname())
                .birthday(foundUser.getBirthday())
                .roleId(foundUser.getRole().getId().toString())
                .projectId(foundUser.getProjectOfUsers().getId().toString())
                .build();
    }

    @Override
    public boolean validate(CustomUser source) {
        return true;
    }
}
