package emt.directe.emt_en_directe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "username obligatorio")
    private String username;

    @NotBlank(message = "contraseña obligatoria")
    private String password;
}
