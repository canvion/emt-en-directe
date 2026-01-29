package emt.directe.emt_en_directe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String rol;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // NO incluye password (seguridad)
}
