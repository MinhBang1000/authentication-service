package ctu.cit.se.authenticationservice.dtos.authorities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAuthorityReqDTO {
    @JsonProperty("authorityName")
    private String name;
    @JsonProperty("authorityProjectId")
    private String projectId;
}
