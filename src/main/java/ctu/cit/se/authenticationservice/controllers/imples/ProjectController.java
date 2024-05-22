package ctu.cit.se.authenticationservice.controllers.imples;

import ctu.cit.se.authenticationservice.controllers.ifaces.ICustomUserController;
import ctu.cit.se.authenticationservice.controllers.ifaces.IProjectController;
import ctu.cit.se.authenticationservice.daos.ifaces.ICustomUserDAO;
import ctu.cit.se.authenticationservice.daos.ifaces.IProjectDAO;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.projects.CreateProjectReqDTO;
import ctu.cit.se.authenticationservice.dtos.projects.RetrieveProjectResDTO;
import ctu.cit.se.authenticationservice.dtos.projects.UpdateProjectReqDTO;
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
@RequestMapping("/projects")
public class ProjectController implements IProjectController {
    @Autowired
    private IProjectDAO projectDAO;

    @GetMapping
    @Override
    public ResponseEntity<List<RetrieveProjectResDTO>> list() {
        return ResponseEntity.ok(projectDAO.list());
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<RetrieveProjectResDTO> retrieve(@PathVariable String id) {
        return ResponseEntity.ok(projectDAO.retrieve(UUID.fromString(id)));
    }

    @PostMapping
    @Override
    public ResponseEntity<CommandDTO> create(@RequestBody CreateProjectReqDTO createUserReqDTO) {
        return new ResponseEntity<>(projectDAO.create(createUserReqDTO), HttpStatus.CREATED);
    }

    @PatchMapping
    @Override
    public ResponseEntity<CommandDTO> update(@RequestBody UpdateProjectReqDTO updateProjectReqDTO) {
        return new ResponseEntity<>(projectDAO.update(updateProjectReqDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable String id) {
        projectDAO.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
