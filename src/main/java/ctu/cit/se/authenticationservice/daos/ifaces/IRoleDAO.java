package ctu.cit.se.authenticationservice.daos.ifaces;

import ctu.cit.se.authenticationservice.daos.IBaseDAO;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.roles.CreateRoleReqDTO;
import ctu.cit.se.authenticationservice.dtos.roles.RetrieveRoleResDTO;
import ctu.cit.se.authenticationservice.dtos.roles.UpdateRoleReqDTO;
import ctu.cit.se.authenticationservice.entities.Role;

import java.util.List;
import java.util.UUID;

public interface IRoleDAO extends IBaseDAO<CreateRoleReqDTO, UpdateRoleReqDTO, RetrieveRoleResDTO, CommandDTO, UUID> {
    void createInitData(List<Role> roles);
}
