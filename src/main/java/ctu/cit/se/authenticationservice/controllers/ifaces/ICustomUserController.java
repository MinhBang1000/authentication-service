package ctu.cit.se.authenticationservice.controllers.ifaces;

import ctu.cit.se.authenticationservice.controllers.IBaseController;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.projects.CreateProjectReqDTO;
import ctu.cit.se.authenticationservice.dtos.projects.RetrieveProjectResDTO;
import ctu.cit.se.authenticationservice.dtos.projects.UpdateProjectReqDTO;
import ctu.cit.se.authenticationservice.dtos.users.CreateUserReqDTO;
import ctu.cit.se.authenticationservice.dtos.users.RetrieveUserResDTO;
import ctu.cit.se.authenticationservice.dtos.users.UpdateUserReqDTO;

public interface ICustomUserController extends IBaseController<CreateUserReqDTO, UpdateUserReqDTO, RetrieveUserResDTO, CommandDTO> {

}
