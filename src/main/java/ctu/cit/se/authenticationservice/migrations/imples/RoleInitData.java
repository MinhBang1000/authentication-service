package ctu.cit.se.authenticationservice.migrations.imples;

import ctu.cit.se.authenticationservice.entities.Role;
import ctu.cit.se.authenticationservice.migrations.ifaces.IRoleInitData;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
public class RoleInitData implements IRoleInitData {
    @Override
    public List<Role> getInitData() {
        return List.of(
                Role.builder()
                        .name("Administrator")
                        .build()
        );
    }
}
