package emt.directe.emt_en_directe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateDTO {

    @Email(message = "el mail debe ser válido")
    private String email;

    @Size(min = 6, message = "la contraseña debe tener mínimo 6 caracteres")
    private String password;
}
