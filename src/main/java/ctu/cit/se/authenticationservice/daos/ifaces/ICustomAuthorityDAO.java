package ctu.cit.se.authenticationservice.daos.ifaces;

import ctu.cit.se.authenticationservice.daos.IBaseDAO;
import ctu.cit.se.authenticationservice.dtos.authorities.CreateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.dtos.authorities.RetrieveAuthorityResDTO;
import ctu.cit.se.authenticationservice.dtos.authorities.UpdateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.entities.CustomGrantedAuthority;
import ctu.cit.se.authenticationservice.entities.Project;

import java.util.List;
import java.util.UUID;

public interface ICustomAuthorityDAO extends IBaseDAO<CreateAuthorityReqDTO, UpdateAuthorityReqDTO, RetrieveAuthorityResDTO, CommandDTO, UUID> {
    void createInitData(List<CustomGrantedAuthority> authorities);
}
