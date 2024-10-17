package mvm.backend.service;

import mvm.backend.model.EventModel;
import mvm.backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Crear un nuevo evento
    public EventModel createEvent(EventModel event) {
        return eventRepository.save(event);  // Usar la instancia del repositorio
    }

    // Obtener todos los eventos
    public List<EventModel> getAllEvents() {
        return (List<EventModel>) eventRepository.findAll();
    }
}
