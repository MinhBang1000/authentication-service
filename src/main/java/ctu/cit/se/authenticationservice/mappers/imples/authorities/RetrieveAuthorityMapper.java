package ctu.cit.se.authenticationservice.mappers.imples.authorities;

import ctu.cit.se.authenticationservice.dtos.authorities.RetrieveAuthorityResDTO;
import ctu.cit.se.authenticationservice.entities.CustomGrantedAuthority;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import org.springframework.stereotype.Component;

@Component
public class RetrieveAuthorityMapper implements IBaseMapper<CustomGrantedAuthority, RetrieveAuthorityResDTO> {
    @Override
    public RetrieveAuthorityResDTO convert(CustomGrantedAuthority source) {
        return RetrieveAuthorityResDTO.builder()
                .id(source.getId().toString())
                .name(source.getAuthority())
                .projectId(source.getProjectOfAuthorities().getId().toString())
                .build();
    }
}
