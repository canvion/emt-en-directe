package emt.directe.emt_en_directe.service;

import emt.directe.emt_en_directe.model.Usuario;
import emt.directe.emt_en_directe.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    //llista de  tots els usuaris
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    //obtener usuario por su id
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    //obtener usuario por su nombre de usuario
    public Optional<Usuario> getUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    //crear usuari
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //actualitzar usuari
    public Usuario updateUsuario(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setUsername(usuarioActualizado.getUsername());
                    usuario.setEmail(usuarioActualizado.getEmail());
                    usuario.setPassword(usuarioActualizado.getPassword());
                    usuario.setRol(usuarioActualizado.getRol());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("usuari no trobat amb l'id: " + id));
    }

    //eliminar usuari
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    //verificar si l'username existeix
    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    //verificar si el mail existeix
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
