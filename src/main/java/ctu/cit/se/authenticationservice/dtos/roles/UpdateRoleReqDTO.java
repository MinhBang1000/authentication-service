package ctu.cit.se.authenticationservice.dtos.roles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleReqDTO {
    @JsonProperty("roleId")
    private String id;
    @JsonProperty("roleName")
    private String name;
}
