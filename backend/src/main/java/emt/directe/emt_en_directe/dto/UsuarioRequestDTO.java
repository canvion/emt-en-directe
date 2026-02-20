package emt.directe.emt_en_directe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank(message = "username obligatorio")
    private String username;

    @NotBlank(message = "el mail es obligatorio")
    @Email(message = "el mail debe ser válido")
    private String email;

    @NotBlank(message = "la contraseña es obligatoria")
    @Size(min = 6, message = "la contraseña debe tener mínimo 6 caracteres")
    private String password;

    @NotBlank(message = " confirmació de contrasenya obligatòria")
    private String confirmPassword;

}
