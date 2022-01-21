package es.jpargoteo.vodafone.devices.ws.service;

import es.jpargoteo.vodafone.devices.api.dto.DeviceModel;
import es.jpargoteo.vodafone.devices.model.business.impl.DeviceService;
import es.jpargoteo.vodafone.devices.model.business.impl.SimService;
import es.jpargoteo.vodafone.devices.model.domain.Device;
import es.jpargoteo.vodafone.devices.model.domain.Sim;
import es.jpargoteo.vodafone.devices.ws.assembler.DeviceAssembler;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.UUID;

/**
 * Class that provides the API's for managing devices.
 */
@RestController
public class DeviceController {

    private DeviceService deviceService;
    private SimService simService;
    private DeviceAssembler deviceAssembler;

    /**
     * Constructor that given a {@link DeviceService}, a {@link SimService} and a {@link DeviceAssembler} autowires them
     * and constructs the class.
     *
     * @param deviceService   - The given service for managing devices business logic.
     * @param simService      - The given service for managing sims business logic.
     * @param deviceAssembler - The given service for managing devices' assembly.
     */
    public DeviceController(DeviceService deviceService, SimService simService,
                            DeviceAssembler deviceAssembler) {

        this.deviceService = deviceService;
        this.simService = simService;
        this.deviceAssembler = deviceAssembler;
    }

    /**
     * Method that responses to an API request. Given a {@link Pageable} returns a {@link CollectionModel} of devices
     * that are available.
     *
     * @param pageable - The page to retrieve.
     * @return - The resultant page.
     */
    @GetMapping("/devices/available")
    public ResponseEntity<CollectionModel<DeviceModel>> available(Pageable pageable) {

        return ResponseEntity.ok(deviceAssembler.toCollectionModel(deviceService.allAvailable(pageable)));
    }

    /**
     * Method that responses to an API request. Given a {@link Pageable} returns a {@link CollectionModel} of devices
     * that are waiting for sim activation.
     *
     * @param pageable - The page to retrieve.
     * @return - The resultant page.
     */
    @GetMapping("/devices/waiting-activation")
    public ResponseEntity<CollectionModel<DeviceModel>> waitingActivation(Pageable pageable) {

        return ResponseEntity.ok(deviceAssembler.toCollectionModel(deviceService.allWaitingActivation(pageable)));
    }

    /**
     * Method that given a device {@link String} id removes its configuration - if exists - and returns the device.
     *
     * @param id - The id of the device.
     * @return - The resultant device representation.
     */
    @PostMapping("/devices/{id}/remove-configuration")
    public ResponseEntity<DeviceModel> removeConfiguration(String id) {

        return ResponseEntity.ok(deviceAssembler.toModel(deviceService.removeConfiguration(UUID.fromString(id))));
    }

    /**
     * Method that given a device {@link String} id activates its sim and returns the device.
     *
     * @param id - The id of the device.
     * @return - The resultant device representation.
     */
    @PostMapping("/devices/{id}/sim/activate")
    public ResponseEntity<DeviceModel> activateSim(String id) {

        try {

            Device device = deviceService.one(UUID.fromString(id));

            if (device != null && device.getSim() != null) {

                Sim activatedSim = simService.activate(device.getSim().getId());

                device.setSim(activatedSim);

                return ResponseEntity.ok(deviceAssembler.toModel(device));
            } else {

                // Todo: improve to send a bad request

                throw new RestClientException("bad request error");
            }

        } catch (IllegalArgumentException e) {

            // Todo: improve to send a bad request

            throw new RestClientException("bad request error");
        } catch (Exception e) {

            // Todo: improve to send a bad request

            throw new RestClientException("unexpected error");
        }
    }

    /**
     * Method that given a device {@link String} id deactivates its sim and returns the device.
     *
     * @param id - The id of the device.
     * @return - The resultant device representation.
     */
    @PostMapping("/devices/{id}/sim/deactivate")
    public ResponseEntity<DeviceModel> deactivateSim(String id) {

        try {

            Device device = deviceService.one(UUID.fromString(id));

            if (device != null && device.getSim() != null) {

                Sim activatedSim = simService.deactivate(device.getSim().getId());

                device.setSim(activatedSim);

                return ResponseEntity.ok(deviceAssembler.toModel(device));
            } else {

                // Todo: improve to send a bad request

                throw new RestClientException("bad request error");
            }

        } catch (IllegalArgumentException e) {

            // Todo: improve to send a bad request

            throw new RestClientException("bad request error");
        } catch (Exception e) {

            // Todo: improve to send a bad request

            throw new RestClientException("unexpected error");
        }
    }

    /**
     * Method that given a device {@link String} id blocks its sim and returns the device.
     *
     * @param id - The id of the device.
     * @return - The resultant device representation.
     */
    @PostMapping("/devices/{id}/sim/block")
    public ResponseEntity<DeviceModel> blockSim(String id) {

        try {

            Device device = deviceService.one(UUID.fromString(id));

            if (device != null && device.getSim() != null) {

                Sim activatedSim = simService.block(device.getSim().getId());

                device.setSim(activatedSim);

                return ResponseEntity.ok(deviceAssembler.toModel(device));
            } else {

                // Todo: improve to send a bad request

                throw new RestClientException("bad request error");
            }

        } catch (IllegalArgumentException e) {

            // Todo: improve to send a bad request

            throw new RestClientException("bad request error");
        } catch (Exception e) {

            // Todo: improve to send a bad request

            throw new RestClientException("unexpected error");
        }
    }

    /**
     * Method that given a device {@link String} id set its sim to waiting for activation and returns the device.
     *
     * @param id - The id of the device.
     * @return - The resultant device representation.
     */
    @PostMapping("/devices/{id}/sim/waiting-activation")
    public ResponseEntity<DeviceModel> setWaitingActivationSim(String id) {

        try {

            Device device = deviceService.one(UUID.fromString(id));

            if (device != null && device.getSim() != null) {

                Sim activatedSim = simService.waitForActivation(device.getSim().getId());

                device.setSim(activatedSim);

                return ResponseEntity.ok(deviceAssembler.toModel(device));
            } else {

                // Todo: improve to send a bad request

                throw new RestClientException("bad request error");
            }

        } catch (IllegalArgumentException e) {

            // Todo: improve to send a bad request

            throw new RestClientException("bad request error");
        } catch (Exception e) {

            // Todo: improve to send a bad request

            throw new RestClientException("unexpected error");
        }
    }
}
