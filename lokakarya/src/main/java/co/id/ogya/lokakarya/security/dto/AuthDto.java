package co.id.ogya.lokakarya.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Slf4j
public class AuthDto {
    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;
}