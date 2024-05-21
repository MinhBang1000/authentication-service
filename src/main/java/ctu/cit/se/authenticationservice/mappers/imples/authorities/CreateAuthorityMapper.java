package ctu.cit.se.authenticationservice.mappers.imples.authorities;

import ctu.cit.se.authenticationservice.dtos.authorities.CreateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.entities.CustomGrantedAuthority;
import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.repositories.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateAuthorityMapper implements IBaseMapper<CreateAuthorityReqDTO, CustomGrantedAuthority> {
    @Autowired
    private IProjectRepository IProjectRepository;

    @Override
    public CustomGrantedAuthority convert(CreateAuthorityReqDTO source) {
        Project foundProject = IProjectRepository.findById(UUID.fromString(source.getProjectId())).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.PROJECT_NOT_FOUND));
        return CustomGrantedAuthority.builder()
                .authority(source.getName())
                .projectOfAuthorities(foundProject)
                .build();
    }
}
