package ctu.cit.se.authenticationservice.daos.imples;

import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.repositories.CustomGrantedAuthorityRepository;
import ctu.cit.se.authenticationservice.repositories.CustomUserRepository;
import ctu.cit.se.authenticationservice.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetailsDAO implements UserDetailsManager {
    @Autowired
    private CustomUserRepository userRepository;
    @Autowired
    private CustomGrantedAuthorityRepository grantedAuthorityRepository;
    @Autowired
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(CustomExceptionMessage.USER_NOT_FOUND+": "+ username));
    }

    @Override
    public void createUser(UserDetails user) {
        CustomUser customUser = (CustomUser) user;
        userRepository.save(customUser);
    }

    @Override
    public void updateUser(UserDetails user) {
        CustomUser customUser = (CustomUser) user;
        customUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(customUser);
    }

    @Override
    public void deleteUser(String username) {
        CustomUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        userRepository.delete(user);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
