package ctu.cit.se.authenticationservice.controllers.imples;

import ctu.cit.se.authenticationservice.controllers.ifaces.ICustomAuthorityController;
import ctu.cit.se.authenticationservice.controllers.ifaces.ICustomUserController;
import ctu.cit.se.authenticationservice.daos.ifaces.ICustomAuthorityDAO;
import ctu.cit.se.authenticationservice.daos.ifaces.ICustomUserDAO;
import ctu.cit.se.authenticationservice.dtos.authorities.CreateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.dtos.authorities.RetrieveAuthorityResDTO;
import ctu.cit.se.authenticationservice.dtos.authorities.UpdateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.users.CreateUserReqDTO;
import ctu.cit.se.authenticationservice.dtos.users.RetrieveUserResDTO;
import ctu.cit.se.authenticationservice.dtos.users.UpdateUserReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class CustomUserController implements ICustomUserController {
    @Autowired
    private ICustomUserDAO userDAO;

    @GetMapping
    @Override
    public ResponseEntity<List<RetrieveUserResDTO>> list() {
        return ResponseEntity.ok(userDAO.list());
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<RetrieveUserResDTO> retrieve(@PathVariable String id) {
        return ResponseEntity.ok(userDAO.retrieve(UUID.fromString(id)));
    }

    @PostMapping
    @Override
    public ResponseEntity<CommandDTO> create(@RequestBody CreateUserReqDTO createUserReqDTO) {
        return new ResponseEntity<>(userDAO.create(createUserReqDTO), HttpStatus.CREATED);
    }

    @PatchMapping
    @Override
    public ResponseEntity<CommandDTO> update(@RequestBody UpdateUserReqDTO updateUserReqDTO) {
        return new ResponseEntity<>(userDAO.update(updateUserReqDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userDAO.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
