package mvm.backend.controller;

import mvm.backend.service.UserService;
import mvm.backend.model.UserModel; // Asegúrate de importar la clase Usuario

//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mvm.backend.model.LoginRequest;
import java.util.List; // Importa la clase genérica List

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            boolean isValid = usuarioService.validarCredenciales(loginRequest.getEmail(), loginRequest.getPassword());
            if (isValid) {
                return ResponseEntity.ok().body("Inicio de sesión exitoso");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }

    // Método para registrar usuario
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel usuario) {
        try {
            // Verificar si el email ya está registrado
            if (usuarioService.existeEmail(usuario.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: El correo ya está registrado.");
            }

            // Registrar usuario
            usuarioService.registrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar usuario: " + e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        try {
            List<UserModel> usuarios = usuarioService.obtenerTodosLosUsuarios(); // Llama al servicio para obtener los usuarios
            System.out.println("Usuarios devueltos: " + usuarios);
            return ResponseEntity.ok(usuarios); // Devuelve los usuarios en la respuesta
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Devuelve error si algo falla
        }
    }

    
}
