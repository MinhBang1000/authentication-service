package ctu.cit.se.authenticationservice.controllers.imples;

import ctu.cit.se.authenticationservice.daos.ifaces.IProjectDAO;
import ctu.cit.se.authenticationservice.daos.ifaces.IRoleDAO;
import ctu.cit.se.authenticationservice.migrations.ifaces.IRoleInitData;
import ctu.cit.se.authenticationservice.migrations.imples.ProjectInitData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init-data")
public class InitController {
    @Autowired
    private IRoleDAO roleDAO;
    @Autowired
    private IProjectDAO projectDAO;
    @Autowired
    private IRoleInitData roleInitData;
    @Autowired
    private ProjectInitData projectInitData;

    @PostMapping
    public ResponseEntity<String> initData() {
        roleDAO.createInitData(roleInitData.getInitData());
        projectDAO.createInitData(projectInitData.getInitData());
        return new ResponseEntity<>("Init data successfully !", HttpStatus.CREATED);
    }
}
