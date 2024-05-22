package ctu.cit.se.authenticationservice.daos.ifaces;

import ctu.cit.se.authenticationservice.daos.IBaseDAO;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.users.CreateUserReqDTO;
import ctu.cit.se.authenticationservice.dtos.users.RetrieveUserResDTO;
import ctu.cit.se.authenticationservice.dtos.users.UpdateUserReqDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface ICustomUserDAO extends IBaseDAO<CreateUserReqDTO, UpdateUserReqDTO, RetrieveUserResDTO, CommandDTO, UUID> {
}
