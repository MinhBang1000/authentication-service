package ctu.cit.se.authenticationservice.daos.imples;

import ctu.cit.se.authenticationservice.daos.ifaces.IRoleDAO;
import ctu.cit.se.authenticationservice.dtos.authorities.RetrieveAuthorityResDTO;
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
import ctu.cit.se.authenticationservice.repositories.IProjectRepository;
import ctu.cit.se.authenticationservice.repositories.IRoleRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
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
    @Autowired
    private IBaseMapper<CustomGrantedAuthority, RetrieveAuthorityResDTO> retrieveAuthorityMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RetrieveRoleResDTO> list() {
        return roleRepository.findAll().stream().map(role -> {
            RetrieveRoleResDTO retrieveRoleResDTO = retrieveMapper.convert(role);
            retrieveRoleResDTO.setAuthorities(null);
            return retrieveRoleResDTO;
        }).collect(Collectors.toList());
    }

    private void updateAuthorities(Set<String> authorities, Role role, boolean isCreated) {
        /* Add authorities continuously */
        Role updatingRole = role;
        if (isCreated) {
            updatingRole = entityManager.find(Role.class, role.getId());
        }
        for (String authorityId : authorities) {
            UUID authorityUuid = UUID.fromString(authorityId);
            CustomGrantedAuthority item = entityManager.find(CustomGrantedAuthority.class, authorityUuid);
            if (Objects.nonNull(item)) {
                item.addRole(role);
                updatingRole.addAuthority(item);
                entityManager.persist(item);
            }
        }
        entityManager.persist(updatingRole);
    }

    @Override
    @Transactional
    public CommandDTO create(CreateRoleReqDTO createRoleReqDTO) {
        Role role = roleRepository.save(createMapper.convert(createRoleReqDTO));
        updateAuthorities(createRoleReqDTO.getAuthorities(), role, true);
        return CommandDTO.builder().id(role.getId().toString()).build();
    }

    @Override
    @Transactional
    public CommandDTO update(UpdateRoleReqDTO updateRoleReqDTO) {
        Role role = entityManager.find(Role.class, UUID.fromString(updateRoleReqDTO.getId()));
        role.setName(Objects.nonNull(updateRoleReqDTO.getName()) ? updateRoleReqDTO.getName() : role.getName());
        if (Objects.nonNull(role.getAuthorities())) {
            role.getAuthorities().clear();
            updateAuthorities(updateRoleReqDTO.getAuthorities(), role, false);
        }
        return CommandDTO.builder().id(role.getId().toString()).build();
    }

    @Override
    @Transactional
    public RetrieveRoleResDTO retrieve(UUID uuid) {
        Role role = entityManager.find(Role.class, uuid);
        if (Objects.isNull(role)) {
            throw new IllegalArgumentException(CustomExceptionMessage.ROLE_NOT_FOUND);
        }
        /* Get all authorities of the role */
        RetrieveRoleResDTO retrieveRoleResDTO = retrieveMapper.convert(role);
        List<RetrieveAuthorityResDTO> retrieveAuthorityResDTOS = role.getAuthorities()
                .stream()
                .map((authority) -> retrieveAuthorityMapper.convert(authority)).collect(Collectors.toList());
        /* Map it into DTO response as a list */
        retrieveRoleResDTO.setAuthorities(retrieveAuthorityResDTOS);
        return retrieveRoleResDTO;
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
