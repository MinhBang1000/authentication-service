package ctu.cit.se.authenticationservice.mappers.imples.projects;

import ctu.cit.se.authenticationservice.dtos.projects.UpdateProjectReqDTO;
import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.repositories.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class UpdateProjectMapper implements IBaseMapper<UpdateProjectReqDTO, Project> {
    @Autowired
    private IProjectRepository IProjectRepository;

    @Override
    public Project convert(UpdateProjectReqDTO source) {
        Project foundProject = IProjectRepository.findById(UUID.fromString(source.getId())).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.PROJECT_NOT_FOUND)
        );
        return Project.builder()
                .id(foundProject.getId())
                .name(Objects.nonNull(source.getName()) ? source.getName() : foundProject.getName())
                .build();
    }
}
