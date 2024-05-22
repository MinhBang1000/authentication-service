package ctu.cit.se.authenticationservice.mappers.imples.authorities;

import ctu.cit.se.authenticationservice.dtos.authorities.UpdateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.entities.CustomGrantedAuthority;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.repositories.ICustomGrantedAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class UpdateAuthorityMapper implements IBaseMapper<UpdateAuthorityReqDTO, CustomGrantedAuthority> {
    @Autowired
    private ICustomGrantedAuthorityRepository authorityRepository;

    @Override
    public CustomGrantedAuthority convert(UpdateAuthorityReqDTO source) {
        CustomGrantedAuthority authority = authorityRepository.findById(UUID.fromString(source.getId())).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.AUTHORITY_NOT_FOUND)
        );
        return CustomGrantedAuthority.builder()
                .id(UUID.fromString(source.getId()))
                .authority(Objects.nonNull(source.getName()) ? source.getName() : authority.getAuthority())
                .projectOfAuthorities(authority.getProjectOfAuthorities())
                .build();
    }

    @Override
    public boolean validate(UpdateAuthorityReqDTO source) {
        return true;
    }
}
