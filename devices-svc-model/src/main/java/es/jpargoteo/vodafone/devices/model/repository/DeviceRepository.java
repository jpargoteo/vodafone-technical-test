package es.jpargoteo.vodafone.devices.model.repository;

import es.jpargoteo.vodafone.devices.model.domain.Device;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface that provides data access management for persistence.
 */
@Repository
public interface DeviceRepository extends PagingAndSortingRepository<Device, UUID> {

    @Query("update Device set sim = NULL where id= ?1")
    void removeConfiguration(UUID id);
}
