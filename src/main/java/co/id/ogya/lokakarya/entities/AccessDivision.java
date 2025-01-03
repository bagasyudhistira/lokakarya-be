package co.id.ogya.lokakarya.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "TBL_ACCESS_DIVISION")
public class AccessDivision {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "DIVISION_ID")
    private String divisionId;

    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
