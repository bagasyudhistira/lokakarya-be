package co.id.ogya.lokakarya.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Slf4j
public class AuthPasswordChangeDto {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("new_password")
    private String newPassword;
}
