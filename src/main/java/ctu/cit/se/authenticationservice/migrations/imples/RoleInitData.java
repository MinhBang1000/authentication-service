package ctu.cit.se.authenticationservice.migrations.imples;

import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.entities.Role;
import ctu.cit.se.authenticationservice.migrations.ifaces.IRoleInitData;
import ctu.cit.se.authenticationservice.repositories.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Component
public class RoleInitData implements IRoleInitData {
    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public List<Role> getInitData() {
        Project project = projectRepository.findByName("Default Project - Testing as purpose").orElse(null);
        List<Role> roles = new ArrayList<>();
        if (Objects.nonNull(project)) {
            roles = List.of(
                    Role.builder()
                            .name("Administrator")
                            .projectOfRoles(project)
                            .build()
            );
        }
        return roles;
    }
}
