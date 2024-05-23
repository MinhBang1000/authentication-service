package ctu.cit.se.authenticationservice.controllers.imples;

import ctu.cit.se.authenticationservice.daos.ifaces.IProjectDAO;
import ctu.cit.se.authenticationservice.daos.ifaces.IRoleDAO;
import ctu.cit.se.authenticationservice.dtos.projects.RetrieveProjectResDTO;
import ctu.cit.se.authenticationservice.dtos.roles.RetrieveRoleResDTO;
import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.entities.Role;
import ctu.cit.se.authenticationservice.migrations.ifaces.IProjectInitData;
import ctu.cit.se.authenticationservice.migrations.ifaces.IRoleInitData;
import ctu.cit.se.authenticationservice.migrations.imples.ProjectInitData;
import ctu.cit.se.authenticationservice.repositories.ICustomUserRepository;
import ctu.cit.se.authenticationservice.repositories.IProjectRepository;
import ctu.cit.se.authenticationservice.repositories.IRoleRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

record AdministratorDTO(String username, String password, String role, String project) {};

@RestController
@RequestMapping("/init-data")
public class InitController {
    @Autowired
    private IRoleDAO roleDAO;
    @Autowired
    private IProjectDAO projectDAO;
    @Autowired
    private IRoleInitData roleInitData;
    @Autowired
    private ICustomUserRepository userRepository;
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private ProjectInitData projectInitData;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/common-data")
    public ResponseEntity<String> initData() {
        roleDAO.createInitData(roleInitData.getInitData());
        projectDAO.createInitData(projectInitData.getInitData());
        return new ResponseEntity<>("Init data successfully !", HttpStatus.CREATED);
    }

    @PostMapping("/administrator-creation")
    public ResponseEntity<AdministratorDTO> initAccount() {
        Project project = projectRepository.findByName("Default Project - Testing as purpose").orElse(null);
        if (project == null) {
            project = projectRepository.save(Project.builder()
                            .name("Default Project - Testing as purpose")
                    .build());
        }
        Role role = roleRepository.findByName("Administrator").orElse(null);
        if (role == null) {
            role = roleRepository.save(Role.builder()
                            .name("Administrator")
                    .build());
        }
        CustomUser admin = CustomUser.builder()
                .firstname("LE")
                .lastname("MINH BANG")
                .birthday(LocalDate.of(2000, 02, 16))
                .role(role)
                .projectOfUsers(project)
                .enabled(true)
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .build();
        userRepository.save(admin);
        return new ResponseEntity<>(new AdministratorDTO(
                "admin",
                "admin",
                role.getName(),
                project.getName()
        ), HttpStatus.CREATED);
    }
}
