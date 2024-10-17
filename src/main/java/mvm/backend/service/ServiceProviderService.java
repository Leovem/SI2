// src/main/java/mvm/backend/service/ServiceProviderService.java
package mvm.backend.service;

import mvm.backend.model.ServiceProvider;
import mvm.backend.repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProviderService {
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    public ServiceProvider registrarProveedor(ServiceProvider serviceProvider) {
        return serviceProviderRepository.save(serviceProvider);
    }

    public List<ServiceProvider> obtenerTodosLosProveedores() {
        return serviceProviderRepository.findAll();
    }
}
