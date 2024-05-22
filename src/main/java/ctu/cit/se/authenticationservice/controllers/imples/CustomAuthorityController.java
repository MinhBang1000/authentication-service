package ctu.cit.se.authenticationservice.controllers.imples;

import ctu.cit.se.authenticationservice.controllers.ifaces.ICustomAuthorityController;
import ctu.cit.se.authenticationservice.daos.ifaces.ICustomAuthorityDAO;
import ctu.cit.se.authenticationservice.dtos.authorities.CreateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.dtos.authorities.RetrieveAuthorityResDTO;
import ctu.cit.se.authenticationservice.dtos.authorities.UpdateAuthorityReqDTO;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authorities")
public class CustomAuthorityController implements ICustomAuthorityController {
    @Autowired
    private ICustomAuthorityDAO authorityDAO;

    @GetMapping
    @Override
    public ResponseEntity<List<RetrieveAuthorityResDTO>> list() {
        return ResponseEntity.ok(authorityDAO.list());
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<RetrieveAuthorityResDTO> retrieve(@PathVariable String id) {
        return ResponseEntity.ok(authorityDAO.retrieve(UUID.fromString(id)));
    }

    @PostMapping
    @Override
    public ResponseEntity<CommandDTO> create(@RequestBody CreateAuthorityReqDTO createAuthorityReqDTO) {
        return new ResponseEntity<>(authorityDAO.create(createAuthorityReqDTO), HttpStatus.CREATED);
    }

    @PatchMapping
    @Override
    public ResponseEntity<CommandDTO> update(@RequestBody UpdateAuthorityReqDTO updateAuthorityReqDTO) {
        return new ResponseEntity<>(authorityDAO.update(updateAuthorityReqDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable String id) {
        authorityDAO.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
