package ctu.cit.se.authenticationservice.controllers.imples;

import ctu.cit.se.authenticationservice.daos.imples.CustomUserDetailsDAO;
import ctu.cit.se.authenticationservice.dtos.users.SignInResDTO;
import ctu.cit.se.authenticationservice.dtos.users.SignUpReqDTO;
import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.securities.jwt.JwtTokenUntil;
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
    private IBaseMapper<SignUpReqDTO, CustomUser> createUserMapper;
    @Autowired
    private JwtTokenUntil jwtTokenUntil;

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResDTO> authenticate(Authentication authentication) {
        String token = jwtTokenUntil.createToken(CustomUser.builder().username(authentication.getName()).build());
        return new ResponseEntity<>(SignInResDTO.builder().token(token).build(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpReqDTO signUpReqDTO) {
        userDetailsDAO.createUser(createUserMapper.convert(signUpReqDTO));
        return new ResponseEntity<>("Create user successfully !", HttpStatus.CREATED);
    }
}
