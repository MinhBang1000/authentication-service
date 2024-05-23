package ctu.cit.se.authenticationservice.dtos.roles;

import com.fasterxml.jackson.annotation.JsonProperty;
import ctu.cit.se.authenticationservice.dtos.authorities.RetrieveAuthorityResDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetrieveRoleResDTO {
    @JsonProperty("roleId")
    private String id;
    @JsonProperty("roleName")
    private String name;
    @JsonProperty("roleAuthorities")
    private List<RetrieveAuthorityResDTO> authorities;
    @JsonProperty("roleProjectId")
    private String projectId;
}
