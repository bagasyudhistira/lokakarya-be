package co.id.ogya.lokakarya.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AppUserGetDto {
    @JsonProperty("id")
    private String id;

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

    @JsonProperty("rolename")
    private String rolename;

    @JsonProperty("division_name")
    private String divisionName;
}