package es.jpargoteo.vodafone.devices.model.repository;

import es.jpargoteo.vodafone.devices.model.domain.Sim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface that provides data access management for persistence.
 */
@Repository
public interface SimRepository extends JpaRepository<Sim, Integer> {

    @Query("update Sim set status = 'ACTIVE' where id= ?1")
    Sim activate(Integer id);

    @Query("update Sim set status = 'BLOCKED' where id= ?1")
    Sim block(Integer id);

    @Query("update Sim set status = 'DEACTIVATED' where id= ?1")
    Sim deactivate(Integer id);

    @Query("update Sim set status = 'WAITING_FOR_ACTIVATION' where id= ?1")
    Sim waitForActivation(Integer id);
}
