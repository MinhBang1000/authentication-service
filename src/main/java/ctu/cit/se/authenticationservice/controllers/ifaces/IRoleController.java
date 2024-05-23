package ctu.cit.se.authenticationservice.controllers.ifaces;

import ctu.cit.se.authenticationservice.controllers.IBaseController;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.projects.CreateProjectReqDTO;
import ctu.cit.se.authenticationservice.dtos.projects.RetrieveProjectResDTO;
import ctu.cit.se.authenticationservice.dtos.projects.UpdateProjectReqDTO;
import ctu.cit.se.authenticationservice.dtos.roles.CreateRoleReqDTO;
import ctu.cit.se.authenticationservice.dtos.roles.RetrieveRoleResDTO;
import ctu.cit.se.authenticationservice.dtos.roles.UpdateRoleReqDTO;

public interface IRoleController extends IBaseController<CreateRoleReqDTO, UpdateRoleReqDTO, RetrieveRoleResDTO, CommandDTO> {

}
