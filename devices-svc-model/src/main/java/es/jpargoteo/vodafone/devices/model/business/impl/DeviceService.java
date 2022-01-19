package es.jpargoteo.vodafone.devices.model.business.impl;

import es.jpargoteo.vodafone.devices.model.business.IDeviceService;
import es.jpargoteo.vodafone.devices.model.domain.Device;
import es.jpargoteo.vodafone.devices.model.repository.DeviceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * Implementation of the interface for managing the {@link Device} instances.
 */
@Service
public class DeviceService implements IDeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Device> allWaitingActivation(Pageable pageable) {

        return deviceRepository.findAll(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Device> allAvailable(Pageable pageable) {

        return deviceRepository.findAll(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Device removeConfiguration(UUID id) {

        Assert.notNull(id, "The given device id cannot be null.");
        Assert.isTrue(deviceRepository.existsById(id), "There should exist a Device for the given id.");

        deviceRepository.removeConfiguration(id);

        return deviceRepository.findById(id).get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Device one(UUID id) {

        return deviceRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Device save(Device device) {

        Assert.notNull(device, "The given device to store cannot be null.");

        return deviceRepository.save(device);
    }
}
