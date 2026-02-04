package emt.directe.emt_en_directe.service;

import emt.directe.emt_en_directe.dto.UsuarioRequestDTO;
import emt.directe.emt_en_directe.dto.UsuarioResponseDTO;
import emt.directe.emt_en_directe.dto.UsuarioUpdateDTO;
import emt.directe.emt_en_directe.model.Usuario;
import emt.directe.emt_en_directe.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    //tots els usuaris
    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    //usuari per id
    public UsuarioResponseDTO getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("usuari no trobat amb l'id: " + id));
        return convertToResponseDTO(usuario);
    }

    //usuari per username
    public UsuarioResponseDTO getUsuarioByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("usuari no trobat  amb l'username: " + username));
        return convertToResponseDTO(usuario);
    }

    //rear usuario(registre)
    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO requestDTO) {

        if (usuarioRepository.existsByUsername(requestDTO.getUsername())) {
            throw new RuntimeException("el username ya existe: " + requestDTO.getUsername());
        }

        if (usuarioRepository.existsByEmail(requestDTO.getEmail())) {
            throw new RuntimeException("el mail ya existe: " + requestDTO.getEmail());
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(requestDTO.getUsername());
        usuario.setEmail(requestDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(requestDTO.getPassword())); // Encriptar
        usuario.setRol(requestDTO.getRol() != null ? requestDTO.getRol() : "USER");

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToResponseDTO(savedUsuario);
    }

    //eliminar usuario
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    //actualizar usuario
    public UsuarioResponseDTO updateUsuario(Long id, UsuarioUpdateDTO updateDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("usuari no trobat amb l'id: " + id));

        //actualizar el mail si se introduce
        if (updateDTO.getEmail() != null && !updateDTO.getEmail().equals(usuario.getEmail())) {
            if (usuarioRepository.existsByEmail(updateDTO.getEmail())) {
                throw new RuntimeException("el mail ja existeix: " + updateDTO.getEmail());
            }
            usuario.setEmail(updateDTO.getEmail());
        }

        //actualizar contraseña si se introduce
        if (updateDTO.getPassword() != null && !updateDTO.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(updateDTO.getPassword()));
        }

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return convertToResponseDTO(updatedUsuario);
    }

    //convertir a responseDTO
    private UsuarioResponseDTO convertToResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getCreatedAt(),
                usuario.getUpdatedAt()
        );
    }
}
