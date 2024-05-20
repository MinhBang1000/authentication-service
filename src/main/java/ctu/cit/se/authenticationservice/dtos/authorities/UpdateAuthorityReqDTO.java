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
public class UpdateAuthorityReqDTO {
    @JsonProperty("authorityId")
    private UUID id;
    @JsonProperty("authorityName")
    private String name;
}
