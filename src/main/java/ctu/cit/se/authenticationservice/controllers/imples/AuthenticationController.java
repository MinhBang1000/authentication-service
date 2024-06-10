package ctu.cit.se.authenticationservice.controllers.imples;

import ctu.cit.se.authenticationservice.daos.imples.CustomUserDetailsDAO;
import ctu.cit.se.authenticationservice.dtos.authentications.RefreshReqDTO;
import ctu.cit.se.authenticationservice.dtos.authentications.SignInResDTO;
import ctu.cit.se.authenticationservice.dtos.authentications.SignUpReqDTO;
import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.securities.jwt.JwtTokenUntil;
import jakarta.servlet.http.HttpServletRequest;
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
        String token = jwtTokenUntil.createToken(
                CustomUser.builder().username(authentication.getName()).build(),
                false
        );
        String refresh = jwtTokenUntil.createToken(
                CustomUser.builder().username(authentication.getName()).build(),
                true
        );
        SignInResDTO signInResDTO = new SignInResDTO(token, refresh);
        return new ResponseEntity<>(signInResDTO, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<SignInResDTO> refresh(HttpServletRequest request) {
        String refreshToken = jwtTokenUntil.resolveToken(request);
        if (Objects.isNull(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (!jwtTokenUntil.getIsRefreshedFromToken(refreshToken)) {
            throw new IllegalArgumentException(CustomExceptionMessage.IS_NOT_REFRESH_TOKEN);
        }
        String token = jwtTokenUntil.createToken(
                CustomUser.builder().username(jwtTokenUntil.getUsernameFromToken(refreshToken)).build(),
                false
        );
        SignInResDTO signInResDTO = new SignInResDTO(token, refreshToken);
        return new ResponseEntity<>(signInResDTO, HttpStatus.OK);
    }
}
