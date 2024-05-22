package ctu.cit.se.authenticationservice.daos.imples;

import ctu.cit.se.authenticationservice.daos.ifaces.IRoleDAO;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.roles.CreateRoleReqDTO;
import ctu.cit.se.authenticationservice.dtos.roles.RetrieveRoleResDTO;
import ctu.cit.se.authenticationservice.dtos.roles.UpdateRoleReqDTO;
import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.entities.Role;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.repositories.IProjectRepository;
import ctu.cit.se.authenticationservice.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleDAO implements IRoleDAO {
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IBaseMapper<CreateRoleReqDTO, Role> createMapper;
    @Autowired
    private IBaseMapper<UpdateRoleReqDTO, Role> updateMapper;
    @Autowired
    private IBaseMapper<Role, RetrieveRoleResDTO> retrieveMapper;

    @Override
    public List<RetrieveRoleResDTO> list() {
        return roleRepository.findAll().stream().map(role -> retrieveMapper.convert(role)).collect(Collectors.toList());
    }

    @Override
    public CommandDTO create(CreateRoleReqDTO createRoleReqDTO) {
        Role role = roleRepository.save(createMapper.convert(createRoleReqDTO));
        return CommandDTO.builder().id(role.getId().toString()).build();
    }

    @Override
    public CommandDTO update(UpdateRoleReqDTO updateRoleReqDTO) {
        Role role = roleRepository.save(updateMapper.convert(updateRoleReqDTO));
        return CommandDTO.builder().id(role.getId().toString()).build();
    }

    @Override
    public RetrieveRoleResDTO retrieve(UUID uuid) {
        Role role = roleRepository.findById(uuid).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.PROJECT_NOT_FOUND)
        );
        return retrieveMapper.convert(role);
    }

    @Override
    public void delete(UUID uuid) {
        Role role = roleRepository.findById(uuid).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.PROJECT_NOT_FOUND)
        );
        roleRepository.delete(role);
    }

    @Override
    public void createInitData(List<Role> roles) {
        for (Role role : roles) {
            roleRepository.saveAndFlush(role);
        }
    }
}
