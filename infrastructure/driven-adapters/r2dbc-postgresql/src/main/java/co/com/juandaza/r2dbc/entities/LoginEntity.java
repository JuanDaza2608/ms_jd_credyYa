package co.com.juandaza.r2dbc.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ms_jd_credy_ya", name = "login")
public class LoginEntity {
    private String userLogin;
    private String passwordLogin;
    private String roleLogin;
}
