package ctu.cit.se.authenticationservice.dtos.roles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleReqDTO {
    @JsonProperty("roleName")
    private String name;
    @JsonProperty("roleAuthorities")
    private Set<String> authorities = new HashSet<>();
    @JsonProperty("roleProjectId")
    private String projectId;
}
