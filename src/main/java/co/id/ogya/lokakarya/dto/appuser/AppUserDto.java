package co.id.ogya.lokakarya.dto.appuser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AppUserDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("position")
    private String position;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("employee_status")
    private byte employeeStatus;

    @JsonProperty("join_date")
    private Date joinDate;

    @JsonProperty("enabled")
    private boolean enabled;

//    @JsonProperty("password")
//    private String password;

//    @JsonProperty("role_id")
//    private String roleId;

    @JsonProperty("division_id")
    private String divisionId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_by")
    private String updatedBy;
}
