package es.jpargoteo.vodafone.devices.model.business;

import es.jpargoteo.vodafone.devices.model.domain.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Interface that describes the access to instances of {@link Device} class
 */
public interface IDeviceService {

    /**
     * Method that given a {@link Pageable} object retrieves a {@link Page} of {@link Device} waiting for activation.
     *
     * @param pageable - The given {@link Pageable} for retrieving a specific page of devices.
     * @return - A {@link Page} of devices.
     */
    Page<Device> allWaitingActivation(Pageable pageable);

    /**
     * Method that given a {@link Pageable} object retrieves a {@link Page} of available {@link Device}.
     *
     * @param pageable - The given {@link Pageable} for retrieving a specific page of devices.
     * @return - A {@link Page} of devices.
     */
    Page<Device> allAvailable(Pageable pageable);

    /**
     * Method that given an {@link UUID} id removes the configuration from a {@link Device}.
     *
     * @param id - The given {@link UUID} of a device.
     * @return - The modified {@link Device}
     */
    Device removeConfiguration(UUID id);

    /**
     * Method that given an {@link UUID} id returns a {@link Device} entity.
     *
     * @param id - The given {@link UUID} of a device.
     * @return - The modified {@link Device}.
     */
    Device one(UUID id);

    /**
     * Method that given a {@link Device} persists it.
     *
     * @param device - {@link Device} object to persist.
     * @return - {@link Device} persisted object.
     */
    Device save(Device device);
}
