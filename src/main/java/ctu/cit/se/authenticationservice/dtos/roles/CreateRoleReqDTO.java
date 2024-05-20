package ctu.cit.se.authenticationservice.dtos.roles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleReqDTO {
    @JsonProperty("roleName")
    private String name;
}
