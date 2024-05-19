package ctu.cit.se.authenticationservice.repository_tests.custom_users;

import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.repositories.CustomUserRepository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomUserRepositoryTests {
    @Autowired
    private CustomUserRepository customUserRepository;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Test
    public void shouldGet_oneUser_withThisUsername() {
        CustomUser customUser = customUserRepository.findByUsername("admin").orElse(null);
        logger.info(customUser.toString());
        assertNotNull(customUser);
    }
}
