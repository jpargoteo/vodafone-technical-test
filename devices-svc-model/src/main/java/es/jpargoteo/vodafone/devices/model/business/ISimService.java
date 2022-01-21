package es.jpargoteo.vodafone.devices.model.business;


import es.jpargoteo.vodafone.devices.model.domain.Sim;

import java.util.UUID;

/**
 * Interface that describes the access to instances of {@link Sim} class
 */
public interface ISimService {

    /**
     * Method that given the id for a {@link Sim} activates it.
     *
     * @param id - {@link Integer} of the Sim to be activated.
     * @return - An int object. 0 for successful execution, -1 for an unsuccessful execution.
     */
    Sim activate(Integer id);

    /**
     * Method that given the id for a {@link Sim} blocks it.
     *
     * @param id - {@link Integer} of the Sim to be blocked.
     * @return - An int object. 0 for successful execution, -1 for an unsuccessful execution.
     */
    Sim block(Integer id);

    /**
     * Method that given the id for a {@link Sim} deactivates it.
     *
     * @param id - {@link Integer} of the Sim to be deactivated.
     * @return - An int object. 0 for successful execution, -1 for an unsuccessful execution.
     */
    Sim deactivate(Integer id);

    /**
     * Method that given the id for a {@link Sim} sets it on waiting status for activation.
     *
     * @param id - {@link Integer} of the Sim to be set on waiting status.
     * @return - An int object. 0 for successful execution, -1 for an unsuccessful execution.
     */
    Sim waitForActivation(Integer id);

    /**
     * Method that given a {@link Sim} persists it.
     *
     * @param sim - {@link Sim} object to persist.
     * @return - {@link Sim} persisted object.
     */
    Sim save(Sim sim);
}
