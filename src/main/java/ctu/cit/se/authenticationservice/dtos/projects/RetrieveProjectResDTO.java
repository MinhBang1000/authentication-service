package ctu.cit.se.authenticationservice.dtos.projects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetrieveProjectResDTO {
    @JsonProperty("projectId")
    private String id;
    @JsonProperty("projectName")
    private String name;
}
