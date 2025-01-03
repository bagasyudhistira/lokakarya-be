package co.id.ogya.lokakarya.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "TBL_GROUP_ACHIEVEMENT")
public class GroupAchievement {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "PERCENTAGE")
    private double percentage;

    @Column(name = "ENABLED")
    private boolean enabled;

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
