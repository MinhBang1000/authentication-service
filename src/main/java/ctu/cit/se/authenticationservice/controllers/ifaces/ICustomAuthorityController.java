package ctu.cit.se.authenticationservice.controllers.ifaces;

import ctu.cit.se.authenticationservice.controllers.IBaseController;
import ctu.cit.se.authenticationservice.dtos.authorities.CreateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.dtos.authorities.RetrieveAuthorityResDTO;
import ctu.cit.se.authenticationservice.dtos.authorities.UpdateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.users.CreateUserReqDTO;
import ctu.cit.se.authenticationservice.dtos.users.RetrieveUserResDTO;
import ctu.cit.se.authenticationservice.dtos.users.UpdateUserReqDTO;

public interface ICustomAuthorityController extends IBaseController<CreateAuthorityReqDTO, UpdateAuthorityReqDTO, RetrieveAuthorityResDTO, CommandDTO> {

}
