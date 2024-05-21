package ctu.cit.se.authenticationservice.daos.imples;

import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.repositories.ICustomGrantedAuthorityRepository;
import ctu.cit.se.authenticationservice.repositories.ICustomUserRepository;
import ctu.cit.se.authenticationservice.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsDAO implements UserDetailsManager {
    @Autowired
    private ICustomUserRepository userRepository;
    @Autowired
    private ICustomGrantedAuthorityRepository grantedAuthorityRepository;
    @Autowired
    private IRoleRepository IRoleRepository;
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
