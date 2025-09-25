package com.gestion.citas.medicas.service;

import com.gestion.citas.medicas.dto.AuthResponse;
import com.gestion.citas.medicas.dto.LoginRequest;
import com.gestion.citas.medicas.dto.RegisterRequest;
import com.gestion.citas.medicas.entity.Paciente;
import com.gestion.citas.medicas.entity.enums.Rol;
import com.gestion.citas.medicas.repository.PacienteRepository;
import com.gestion.citas.medicas.repository.UsuarioRepository;
import com.gestion.citas.medicas.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Paciente paciente = new Paciente();
        paciente.setNombre(request.getNombre());
        paciente.setEmail(request.getEmail());
        paciente.setPassword(passwordEncoder.encode(request.getPassword()));
        paciente.setEdad(request.getEdad());
        paciente.setGenero(request.getGenero());
        paciente.setRol(Rol.PACIENTE);

        pacienteRepository.save(paciente);

        return AuthResponse.builder()
                .token(jwtService.getToken(paciente))
                .build();
    }
}
