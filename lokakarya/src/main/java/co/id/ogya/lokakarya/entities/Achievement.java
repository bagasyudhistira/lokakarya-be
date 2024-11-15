package co.id.ogya.lokakarya.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "TBL_ACHIEVEMENT")
public class Achievement {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "ACHIEVEMENT")
    private String achievement;

    @Column(name = "GROUP_ID")
    private String groupId;

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
