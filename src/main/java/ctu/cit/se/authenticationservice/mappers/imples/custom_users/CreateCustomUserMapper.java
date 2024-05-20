package ctu.cit.se.authenticationservice.mappers.imples.custom_users;

import ctu.cit.se.authenticationservice.dtos.users.SignUpReqDTO;
import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomUserMapper implements IBaseMapper<SignUpReqDTO, CustomUser> {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomUser convert(SignUpReqDTO source) {
        return CustomUser.builder()
                .username(source.getUsername())
                .password(passwordEncoder.encode(source.getPassword()))
                .firstname(source.getFirstname())
                .lastname(source.getLastname())
                .birthday(source.getBirthday())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }
}
