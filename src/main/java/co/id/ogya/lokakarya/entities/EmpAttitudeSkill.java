package co.id.ogya.lokakarya.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "TBL_EMP_ATTITUDE_SKILL")
public class EmpAttitudeSkill {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "ATTITUDE_SKILL_ID")
    private String attitudeSkillId;

    @Column(name = "SCORE")
    private double score;

    @Column(name = "ASSESSMENT_YEAR")
    private int assessmentYear;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
