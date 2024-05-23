package ctu.cit.se.authenticationservice.mappers.imples.projects;

import ctu.cit.se.authenticationservice.dtos.authorities.RetrieveAuthorityResDTO;
import ctu.cit.se.authenticationservice.dtos.projects.RetrieveProjectResDTO;
import ctu.cit.se.authenticationservice.entities.CustomGrantedAuthority;
import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import org.springframework.stereotype.Component;

@Component
public class RetrieveProjectMapper implements IBaseMapper<Project, RetrieveProjectResDTO> {
    @Override
    public RetrieveProjectResDTO convert(Project source) {
        return RetrieveProjectResDTO.builder()
                .id(source.getId().toString())
                .name(source.getName())
                .build();
    }

    @Override
    public boolean validate(Project source) {
        return true;
    }
}
