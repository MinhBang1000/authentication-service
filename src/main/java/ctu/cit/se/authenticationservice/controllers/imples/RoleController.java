package ctu.cit.se.authenticationservice.controllers.imples;

import ctu.cit.se.authenticationservice.controllers.ifaces.IProjectController;
import ctu.cit.se.authenticationservice.controllers.ifaces.IRoleController;
import ctu.cit.se.authenticationservice.daos.ifaces.IProjectDAO;
import ctu.cit.se.authenticationservice.daos.ifaces.IRoleDAO;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.projects.CreateProjectReqDTO;
import ctu.cit.se.authenticationservice.dtos.projects.RetrieveProjectResDTO;
import ctu.cit.se.authenticationservice.dtos.projects.UpdateProjectReqDTO;
import ctu.cit.se.authenticationservice.dtos.roles.CreateRoleReqDTO;
import ctu.cit.se.authenticationservice.dtos.roles.RetrieveRoleResDTO;
import ctu.cit.se.authenticationservice.dtos.roles.UpdateRoleReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController implements IRoleController {
    @Autowired
    private IRoleDAO roleDAO;

    @GetMapping
    @Override
    public ResponseEntity<List<RetrieveRoleResDTO>> list() {
        return ResponseEntity.ok(roleDAO.list());
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<RetrieveRoleResDTO> retrieve(@PathVariable String id) {
        return ResponseEntity.ok(roleDAO.retrieve(UUID.fromString(id)));
    }

    @PostMapping
    @Override
    public ResponseEntity<CommandDTO> create(@RequestBody CreateRoleReqDTO createRoleReqDTO) {
        return new ResponseEntity<>(roleDAO.create(createRoleReqDTO), HttpStatus.CREATED);
    }

    @PatchMapping
    @Override
    public ResponseEntity<CommandDTO> update(@RequestBody UpdateRoleReqDTO updateRoleReqDTO) {
        return new ResponseEntity<>(roleDAO.update(updateRoleReqDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable String id) {
        roleDAO.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
