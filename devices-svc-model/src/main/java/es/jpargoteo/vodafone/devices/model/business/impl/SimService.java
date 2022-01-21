package es.jpargoteo.vodafone.devices.model.business.impl;

import es.jpargoteo.vodafone.devices.model.business.ISimService;
import es.jpargoteo.vodafone.devices.model.domain.Sim;
import es.jpargoteo.vodafone.devices.model.repository.SimRepository;
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
     *
     * @param simRepository - {@link SimRepository} to be used for data access.
     */
    public SimService(SimRepository simRepository) {
        this.simRepository = simRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sim activate(Integer id) {

        Assert.notNull(id, "The given id cannot be null.");
        Assert.isTrue(simRepository.existsById(id), "There should exist a Sim for the given id.");

        return simRepository.activate(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sim block(Integer id) {

        Assert.notNull(id, "The given id cannot be null.");
        Assert.isTrue(simRepository.existsById(id), "There should exist a Sim for the given id.");

        return simRepository.block(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sim deactivate(Integer id) {

        Assert.notNull(id, "The given id cannot be null.");
        Assert.isTrue(simRepository.existsById(id), "There should exist a Sim for the given id.");

        return simRepository.deactivate(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sim waitForActivation(Integer id) {

        Assert.notNull(id, "The given id cannot be null.");
        Assert.isTrue(simRepository.existsById(id), "There should exist a Sim for the given id.");

        return simRepository.waitForActivation(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sim save(Sim sim) {
        return null;
    }
}
