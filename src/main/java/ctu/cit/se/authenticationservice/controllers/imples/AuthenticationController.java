package ctu.cit.se.authenticationservice.controllers.imples;

import ctu.cit.se.authenticationservice.daos.imples.CustomUserDetailsDAO;
import ctu.cit.se.authenticationservice.dtos.authentications.SignInResDTO;
import ctu.cit.se.authenticationservice.dtos.authentications.SignUpReqDTO;
import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.securities.jwt.JwtTokenUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
        if (Objects.isNull(authentication)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtTokenUntil.createToken(CustomUser.builder().username(authentication.getName()).build());
        return new ResponseEntity<>(SignInResDTO.builder().token(token).build(), HttpStatus.OK);
    }

//    @PostMapping("/sign-up")
//    public ResponseEntity<String> signUp(@RequestBody SignUpReqDTO signUpReqDTO) {
//        userDetailsDAO.createUser(createUserMapper.convert(signUpReqDTO));
//        return new ResponseEntity<>("Create user successfully !", HttpStatus.CREATED);
//    }
}
