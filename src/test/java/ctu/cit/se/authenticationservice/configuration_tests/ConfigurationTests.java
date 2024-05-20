package ctu.cit.se.authenticationservice.configuration_tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
public class ConfigurationTests {
    @Autowired
    private Environment environment;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void shouldEnvironment_getSpecific_ValueFromEnv() {
        String property = environment.getProperty("auth.secretkey");
        logger.info(property);
        assertNotNull(property);
    }
}
