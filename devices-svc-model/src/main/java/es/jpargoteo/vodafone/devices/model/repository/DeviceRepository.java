package es.jpargoteo.vodafone.devices.model.repository;

import es.jpargoteo.vodafone.devices.model.domain.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface that provides data access management for persistence.
 */
@Repository
public interface DeviceRepository extends PagingAndSortingRepository<Device, Integer> {

    @Query("update Device set sim = NULL where id= ?1")
    void removeConfiguration(Integer id);

    @Query("select d FROM Device d join d.sim s where d.status = 'READY' and s.status <> 'WAITING_FOR_ACTIVATION' and (d.temperature >= -25.0 and d.temperature <= 80.0)")
    Page<Device> allAvailable(Pageable pageable);

    @Query("select d FROM Device d join d.sim s where s.status = 'WAITING_FOR_ACTIVATION'")
    Page<Device> allWaitingForActivation(Pageable pageable);


}
