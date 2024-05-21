package ctu.cit.se.authenticationservice.repository_tests.custom_users;

import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.repositories.ICustomUserRepository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ICustomUserRepositoryTests {
    @Autowired
    private ICustomUserRepository ICustomUserRepository;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Test
    public void shouldGet_oneUser_withThisUsername() {
        CustomUser customUser = ICustomUserRepository.findByUsername("admin").orElse(null);
        logger.info(customUser.toString());
        assertNotNull(customUser);
    }
}
