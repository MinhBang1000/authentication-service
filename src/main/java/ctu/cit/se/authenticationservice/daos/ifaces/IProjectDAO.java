package ctu.cit.se.authenticationservice.daos.ifaces;

import ctu.cit.se.authenticationservice.daos.IBaseDAO;
import ctu.cit.se.authenticationservice.dtos.authorities.CreateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.dtos.authorities.RetrieveAuthorityResDTO;
import ctu.cit.se.authenticationservice.dtos.authorities.UpdateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.projects.CreateProjectReqDTO;
import ctu.cit.se.authenticationservice.dtos.projects.RetrieveProjectResDTO;
import ctu.cit.se.authenticationservice.dtos.projects.UpdateProjectReqDTO;
import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.entities.Role;

import java.util.List;
import java.util.UUID;

public interface IProjectDAO extends IBaseDAO<CreateProjectReqDTO, UpdateProjectReqDTO, RetrieveProjectResDTO, CommandDTO, UUID> {
    void createInitData(List<Project> projects);
}
