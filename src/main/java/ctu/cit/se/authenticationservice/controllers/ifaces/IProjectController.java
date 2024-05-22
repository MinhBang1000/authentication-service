package ctu.cit.se.authenticationservice.controllers.ifaces;

import ctu.cit.se.authenticationservice.controllers.IBaseController;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.projects.CreateProjectReqDTO;
import ctu.cit.se.authenticationservice.dtos.projects.RetrieveProjectResDTO;
import ctu.cit.se.authenticationservice.dtos.projects.UpdateProjectReqDTO;

public interface IProjectController extends IBaseController<CreateProjectReqDTO, UpdateProjectReqDTO, RetrieveProjectResDTO, CommandDTO> {

}
