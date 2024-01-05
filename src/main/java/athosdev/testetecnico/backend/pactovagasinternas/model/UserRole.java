package athosdev.testetecnico.backend.pactovagasinternas.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer roleId;

    private String authority;



    //////////////// CONSTRUCTORS
    public UserRole() {
    }

    public UserRole(Integer roleId, String authority) {
        this.roleId = roleId;
        this.authority = authority;
    }

    public UserRole(String authority) {
        this.authority = authority;
    }

    /////////////// GETTER SETTER
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
