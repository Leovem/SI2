// src/main/java/mvm/backend/controller/ServiceProviderController.java
package mvm.backend.controller;

import mvm.backend.model.ServiceProvider;
import mvm.backend.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service_providers")
public class ServiceProviderController {
    @Autowired
    private ServiceProviderService serviceProviderService;

    @PostMapping("/createProvider")
    public ResponseEntity<?> createServiceProvider(@RequestBody ServiceProvider serviceProvider) {
        try {
            System.out.println("Nombre recibido: " + serviceProvider.getName());
            System.out.println("Correo recibido: " + serviceProvider.getEmail());
            System.out.println("Información de contacto recibida: " + serviceProvider.getPhone()); // Verifica aquí
            serviceProviderService.registrarProveedor(serviceProvider); // Llama al servicio para registrar al usuario
            return ResponseEntity.status(HttpStatus.CREATED).body("Provedor registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar proveedor: " + e.getMessage());
        }
    }

    @GetMapping("/providers")
    public ResponseEntity<List<ServiceProvider>> getAllServiceProviders() {
        try {
            List<ServiceProvider> providers = serviceProviderService.obtenerTodosLosProveedores();
            for (ServiceProvider supplier : providers) {
                if (supplier.getPhone() == null) {
                    supplier.setPhone("No tiene"); // Cambiar null a vacío
                }
            }
            System.out.println("Proveedores obtenidos: " + providers);
            return ResponseEntity.ok(providers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
