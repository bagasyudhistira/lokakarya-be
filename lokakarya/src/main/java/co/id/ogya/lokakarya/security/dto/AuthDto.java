package co.id.ogya.lokakarya.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AuthDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;

    @JsonProperty("role_id")
    public String roleId;
}
