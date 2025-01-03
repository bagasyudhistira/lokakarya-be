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
@Table(name = "TBL_APP_ROLE_MENU")
public class AppRoleMenu {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "MENU_ID")
    private String menuId;

    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
