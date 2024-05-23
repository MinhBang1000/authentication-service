package ctu.cit.se.authenticationservice.controllers.imples;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/confirm")
public class ConfirmController {
    @GetMapping
    public String confirm() {
        return "OK";
    }
}
