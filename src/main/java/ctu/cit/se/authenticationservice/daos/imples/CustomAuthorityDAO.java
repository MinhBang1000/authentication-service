package ctu.cit.se.authenticationservice.daos.imples;

import ctu.cit.se.authenticationservice.daos.ifaces.ICustomAuthorityDAO;
import ctu.cit.se.authenticationservice.daos.ifaces.IRoleDAO;
import ctu.cit.se.authenticationservice.dtos.authorities.CreateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.dtos.authorities.RetrieveAuthorityResDTO;
import ctu.cit.se.authenticationservice.dtos.authorities.UpdateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.roles.CreateRoleReqDTO;
import ctu.cit.se.authenticationservice.dtos.roles.RetrieveRoleResDTO;
import ctu.cit.se.authenticationservice.dtos.roles.UpdateRoleReqDTO;
import ctu.cit.se.authenticationservice.entities.CustomGrantedAuthority;
import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.entities.Role;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.repositories.ICustomGrantedAuthorityRepository;
import ctu.cit.se.authenticationservice.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomAuthorityDAO implements ICustomAuthorityDAO {
    @Autowired
    private ICustomGrantedAuthorityRepository authorityRepository;
    @Autowired
    private IBaseMapper<CreateAuthorityReqDTO, CustomGrantedAuthority> createMapper;
    @Autowired
    private IBaseMapper<UpdateAuthorityReqDTO, CustomGrantedAuthority> updateMapper;
    @Autowired
    private IBaseMapper<CustomGrantedAuthority, RetrieveAuthorityResDTO> retrieveMapper;

    @Override
    public List<RetrieveAuthorityResDTO> list() {
        return authorityRepository.findAll().stream().map(authority -> retrieveMapper.convert(authority)).collect(Collectors.toList());
    }

    @Override
    public CommandDTO create(CreateAuthorityReqDTO createAuthorityReqDTO) {
        CustomGrantedAuthority authority = authorityRepository.save(createMapper.convert(createAuthorityReqDTO));
        return CommandDTO.builder().id(authority.getId().toString()).build();
    }

    @Override
    public CommandDTO update(UpdateAuthorityReqDTO updateAuthorityReqDTO) {
        CustomGrantedAuthority authority = authorityRepository.save(updateMapper.convert(updateAuthorityReqDTO));
        return CommandDTO.builder().id(authority.getId().toString()).build();
    }

    @Override
    public RetrieveAuthorityResDTO retrieve(UUID uuid) {
        CustomGrantedAuthority authority = authorityRepository.findById(uuid).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.PROJECT_NOT_FOUND)
        );
        return retrieveMapper.convert(authority);
    }

    @Override
    public void delete(UUID uuid) {
        CustomGrantedAuthority authority = authorityRepository.findById(uuid).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.PROJECT_NOT_FOUND)
        );
        authorityRepository.delete(authority);
    }

    @Override
    public void createInitData(List<CustomGrantedAuthority> authorities) {
        for (CustomGrantedAuthority authority : authorities) {
            authorityRepository.save(authority);
        }
    }
}
