// src/main/java/mvm/backend/repository/ServiceProviderRepository.java
package mvm.backend.repository;

import mvm.backend.model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
}
