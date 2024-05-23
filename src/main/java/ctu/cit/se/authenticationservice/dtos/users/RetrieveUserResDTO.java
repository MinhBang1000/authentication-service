package ctu.cit.se.authenticationservice.dtos.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import ctu.cit.se.authenticationservice.dtos.authorities.RetrieveAuthorityResDTO;
import ctu.cit.se.authenticationservice.dtos.projects.RetrieveProjectResDTO;
import ctu.cit.se.authenticationservice.dtos.roles.RetrieveRoleResDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetrieveUserResDTO {
    @JsonProperty("userId")
    private String id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("role")
    private RetrieveRoleResDTO role;
    @JsonProperty("project")
    private RetrieveProjectResDTO project;
}
