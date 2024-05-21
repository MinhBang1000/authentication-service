package ctu.cit.se.authenticationservice.mappers.imples.projects;

import ctu.cit.se.authenticationservice.dtos.projects.CreateProjectReqDTO;
import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import org.springframework.stereotype.Component;

@Component
public class CreateProjectMapper implements IBaseMapper<CreateProjectReqDTO, Project> {
    @Override
    public Project convert(CreateProjectReqDTO source) {
        return Project.builder()
                .name(source.getName())
                .build();
    }
}
