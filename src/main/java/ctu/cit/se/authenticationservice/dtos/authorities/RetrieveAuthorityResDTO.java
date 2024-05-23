package ctu.cit.se.authenticationservice.dtos.authorities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetrieveAuthorityResDTO {
    @JsonProperty("authorityId")
    private String id;
    @JsonProperty("authorityName")
    private String name;
    @JsonProperty("authorityDescription")
    private String description;
    @JsonProperty("authorityProjectId")
    private String projectId;
}
