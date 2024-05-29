package ctu.cit.se.authenticationservice.dtos.authentications;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshReqDTO {
    @JsonProperty("refreshToken")
    private String refreshToken;
}
