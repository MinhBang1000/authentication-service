package ctu.cit.se.authenticationservice.migrations.imples;

import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.migrations.ifaces.IProjectInitData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectInitData implements IProjectInitData {
    @Override
    public List<Project> getInitData() {
        return List.of(
                Project.builder()
                        .name("Default Project - Testing as purpose")
                        .build()
        );
    }
}
