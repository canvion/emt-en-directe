package emt.directe.emt_en_directe.service;

import emt.directe.emt_en_directe.config.JwtTokenUtil;
import emt.directe.emt_en_directe.dto.JwtResponse;
import emt.directe.emt_en_directe.dto.LoginRequest;
import emt.directe.emt_en_directe.model.Usuario;
import emt.directe.emt_en_directe.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UsuarioRepository usuarioRepository;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtTokenUtil.generateToken(userDetails.getUsername());
            Usuario usuario = usuarioRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("usuari no trobat"));
            return new JwtResponse(jwt, usuario.getId(), usuario.getUsername(), usuario.getEmail(), usuario.getRol());
        } catch (BadCredentialsException e) {
            throw new RuntimeException("credenciales inválidas");
        }
    }
}
