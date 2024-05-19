package ctu.cit.se.authenticationservice.controllers.imples;

import ctu.cit.se.authenticationservice.daos.imples.CustomUserDetailsDAO;
import ctu.cit.se.authenticationservice.dtos.users.SignUpDTO;
import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    @Autowired
    private CustomUserDetailsDAO userDetailsDAO;
    @Autowired
    private IBaseMapper<SignUpDTO, CustomUser> createUserMapper;

    @PostMapping
    public Authentication authenticate(Authentication authentication) {
        return authentication;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDTO) {
        userDetailsDAO.createUser(createUserMapper.convert(signUpDTO));
        return new ResponseEntity<>("Create user successfully !", HttpStatus.CREATED);
    }
}
