package ctu.cit.se.authenticationservice.mappers.imples.users;

import ctu.cit.se.authenticationservice.dtos.users.UpdateUserReqDTO;
import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.entities.Role;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.repositories.ICustomUserRepository;
import ctu.cit.se.authenticationservice.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class UpdateUserMapper implements IBaseMapper<UpdateUserReqDTO, CustomUser> {
    @Autowired
    private IRoleRepository IRoleRepository;
    @Autowired
    private ICustomUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomUser convert(UpdateUserReqDTO source) {
        CustomUser user = userRepository.findById(UUID.fromString(source.getId())).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.USER_NOT_FOUND)
        );
        Role role = IRoleRepository.findById(UUID.fromString(source.getRoleId())).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.ROLE_NOT_FOUND)
        );
        /* Still working */
        user.setRole(role);
        user.setBirthday(Objects.nonNull(source.getBirthday()) ? source.getBirthday() : user.getBirthday());
        user.setFirstname(Objects.nonNull(source.getFirstname()) ? source.getFirstname() : user.getFirstname());
        user.setLastname(Objects.nonNull(source.getLastname()) ? source.getLastname() : user.getLastname());
        return user;
    }

    @Override
    public boolean validate(UpdateUserReqDTO source) {
        return true;
    }
}
