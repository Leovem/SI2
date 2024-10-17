package mvm.backend.controller;

import mvm.backend.service.EventService;
import mvm.backend.model.EventModel; // Asegúrate de importar la clase EventModel

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events-getset")
public class EventController {

    @Autowired
    private EventService eventService;

    // Método para registrar un nuevo evento
    @PostMapping("/eventcreate")
    public ResponseEntity<?> createEvent(@RequestBody EventModel event) {
        try {
            eventService.createEvent(event);
            System.out.println("Evento recibido: " + event.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body("Evento creado exitosamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear evento: " + e.getMessage());
        }
    }

    // Método para obtener todos los eventos
    @GetMapping("/events")
    public ResponseEntity<List<EventModel>> getAllEvents() {
        try {
            List<EventModel> events = eventService.getAllEvents(); // Llama al servicio para obtener los eventos
            System.out.println("Eventos devueltos: " + events);
            return ResponseEntity.ok(events); // Devuelve los eventos en la respuesta
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Devuelve error si algo falla
        }
    }
}
