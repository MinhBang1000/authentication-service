package ctu.cit.se.authenticationservice.controllers.imples;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

record Todo(String username, String description) {}

@RestController
@RequestMapping("/todo")
public class TodoController {
    private Logger logger = Logger.getLogger(TodoController.class.getName());

    @GetMapping
    public ResponseEntity<String> getInformation() {
        return ResponseEntity.ok("This is a todo service");
    }

    

}