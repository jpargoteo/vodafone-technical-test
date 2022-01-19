package es.jpargoteo.vodafone.devices.model.business.impl;

import es.jpargoteo.vodafone.devices.model.business.ISimService;
import es.jpargoteo.vodafone.devices.model.domain.Sim;
import es.jpargoteo.vodafone.devices.model.repository.SimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * Implementation of the interface for managing the {@link Sim} instances.
 */
@Service
public class SimService implements ISimService {


    private final SimRepository simRepository;

    /**
     * Constructor for instantiate a {@link SimService}.
     * @param simRepository - {@link SimRepository} to be used for data access.
     */
    public SimService(SimRepository simRepository) {
        this.simRepository = simRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int activate(UUID id) {

        Assert.notNull(id, "The given id cannot be null.");
        Assert.isTrue(simRepository.existsById(id), "There should exist a Sim for the given id.");

        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int block(UUID id) {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deactivate(UUID id) {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int waitForActivation(UUID id) {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sim save(Sim sim) {
        return null;
    }
}
