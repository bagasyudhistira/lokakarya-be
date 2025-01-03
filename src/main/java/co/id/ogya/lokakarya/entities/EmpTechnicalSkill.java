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
@Table(name = "TBL_EMP_TECHNICAL_SKILL")
public class EmpTechnicalSkill {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "TECHNICAL_SKILL_ID")
    private String technicalSkillId;

    @Column(name = "SKILL")
    private String skill;

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
