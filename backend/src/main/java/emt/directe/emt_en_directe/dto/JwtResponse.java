package emt.directe.emt_en_directe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String rol;

    public JwtResponse(String token, Long id, String username, String email, String rol) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.rol = rol;
    }
}
