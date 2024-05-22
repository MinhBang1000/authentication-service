package ctu.cit.se.authenticationservice.dao_tests;

import ctu.cit.se.authenticationservice.daos.imples.CustomUserDAO;
import ctu.cit.se.authenticationservice.dtos.users.CreateUserReqDTO;
import ctu.cit.se.authenticationservice.dtos.users.RetrieveUserResDTO;
import ctu.cit.se.authenticationservice.dtos.users.UpdateUserReqDTO;
import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.entities.Role;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.repositories.ICustomUserRepository;
import ctu.cit.se.authenticationservice.repositories.IProjectRepository;
import ctu.cit.se.authenticationservice.repositories.IRoleRepository;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
@Transactional
public class CustomUserDAOTests {
    @Autowired
    private CustomUserDAO customUserDAO;
    @Autowired
    private ICustomUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /* Common variables */
    private CreateUserReqDTO createUserReqDTO;
    private UpdateUserReqDTO updateUserReqDTO;
    private Project project;
    private Role role;
    private CustomUser customUser;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @BeforeEach
    public void setUp() {
        project = projectRepository.findByName("Project 1").orElse(null);
        if (project == null) {
            project = projectRepository.save(Project.builder().name("Project 1").build());
        }
        role = roleRepository.findByName("Role 1").orElse(null);
        if (role == null) {
            role = roleRepository.save(Role.builder().name("Role 1").build());
        }
        customUser = userRepository.save(CustomUser.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .projectOfUsers(project)
                        .role(role)
                        .birthday(LocalDate.of(2000,2,16))
                        .firstname("Le")
                        .lastname("Minh Bang")
                .build());
        createUserReqDTO = CreateUserReqDTO.builder()
                .birthday(LocalDate.of(2000, 2, 16))
                .username("bradonleminhbang1")
                .password("leminhbang")
                .confirm("leminhbang")
                .firstname("Lê")
                .lastname("Minh Bằng")
                .projectId(project.getId().toString())
                .roleId(role.getId().toString())
                .build();
        updateUserReqDTO = UpdateUserReqDTO.builder()
                .id(customUser.getId().toString())
                .birthday(LocalDate.of(2000, 02, 16))
                .firstname("LE")
                .lastname("MINH BANG")
                .roleId(customUser.getRole().getId().toString())
                .build();
    }

    @Test
    public void shouldCreate_userWithCustomUserInstance() {
        assertNotNull(createUserReqDTO);
        assertNotNull(customUserDAO);
        assertNotNull(customUserDAO.create(createUserReqDTO));
    }

    @Test
    public void shouldUpdate_useWith_UpdateUserReqDTO() {
        assertNotNull(updateUserReqDTO);
        assertNotNull(customUserDAO);
        assertNotNull(customUserDAO.update(updateUserReqDTO));
        logger.info("Update User: " + customUserDAO.retrieve(UUID.fromString(updateUserReqDTO.getId())));
    }

    @Test
    public void shouldRetrieve_useWith_UUID() {
        assertNotNull(customUserDAO);
        RetrieveUserResDTO retrieveUserResDTO = customUserDAO.retrieve(UUID.fromString(customUser.getId().toString()));
        assertNotNull(retrieveUserResDTO);
        logger.info("Retrieve User: " + retrieveUserResDTO);
    }

    @Test
    public void shouldDelete_useWith_UUID() {
        assertNotNull(customUserDAO);
        RetrieveUserResDTO retrieveUserResDTO = customUserDAO.retrieve(UUID.fromString(customUser.getId().toString()));
        assertNotNull(retrieveUserResDTO);
        customUserDAO.delete(customUser.getId());
        CustomUser findBackUser = userRepository.findById(UUID.fromString(retrieveUserResDTO.getId())).orElse(null);
        assertNull(findBackUser);
    }
}
