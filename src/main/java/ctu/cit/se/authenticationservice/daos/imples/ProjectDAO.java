package ctu.cit.se.authenticationservice.daos.imples;

import ctu.cit.se.authenticationservice.daos.ifaces.ICustomUserDAO;
import ctu.cit.se.authenticationservice.daos.ifaces.IProjectDAO;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.projects.CreateProjectReqDTO;
import ctu.cit.se.authenticationservice.dtos.projects.RetrieveProjectResDTO;
import ctu.cit.se.authenticationservice.dtos.projects.UpdateProjectReqDTO;
import ctu.cit.se.authenticationservice.dtos.users.CreateUserReqDTO;
import ctu.cit.se.authenticationservice.dtos.users.RetrieveUserResDTO;
import ctu.cit.se.authenticationservice.dtos.users.UpdateUserReqDTO;
import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.repositories.ICustomUserRepository;
import ctu.cit.se.authenticationservice.repositories.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectDAO implements IProjectDAO {
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private IBaseMapper<CreateProjectReqDTO, Project> createMapper;
    @Autowired
    private IBaseMapper<UpdateProjectReqDTO, Project> updateMapper;
    @Autowired
    private IBaseMapper<Project, RetrieveProjectResDTO> retrieveMapper;

    @Override
    public List<RetrieveProjectResDTO> list() {
        return projectRepository.findAll().stream().map(project -> retrieveMapper.convert(project)).collect(Collectors.toList());
    }

    @Override
    public CommandDTO create(CreateProjectReqDTO createProjectReqDTO) {
        Project createdProject = projectRepository.save(createMapper.convert(createProjectReqDTO));
        return CommandDTO.builder().id(createdProject.getId().toString()).build();
    }

    @Override
    public CommandDTO update(UpdateProjectReqDTO updateProjectReqDTO) {
        Project updatedProject = projectRepository.save(updateMapper.convert(updateProjectReqDTO));
        return CommandDTO.builder().id(updatedProject.getId().toString()).build();
    }

    @Override
    public RetrieveProjectResDTO retrieve(UUID uuid) {
        Project foundProject = projectRepository.findById(uuid).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.PROJECT_NOT_FOUND)
        );
        return retrieveMapper.convert(foundProject);
    }

    @Override
    public void delete(UUID uuid) {
        Project foundProject = projectRepository.findById(uuid).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.PROJECT_NOT_FOUND)
        );
        projectRepository.delete(foundProject);
    }
}
