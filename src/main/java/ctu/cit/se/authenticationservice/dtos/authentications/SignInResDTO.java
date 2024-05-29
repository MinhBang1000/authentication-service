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
public class SignInResDTO {
    @JsonProperty("accessToken")
    private String token;
    @JsonProperty("refreshToken")
    private String refresh;
}
