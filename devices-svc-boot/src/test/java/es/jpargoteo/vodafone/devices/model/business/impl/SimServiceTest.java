package es.jpargoteo.vodafone.devices.model.business.impl;

import es.jpargoteo.vodafone.devices.boot.Application;
import es.jpargoteo.vodafone.devices.model.domain.Sim;
import es.jpargoteo.vodafone.devices.model.domain.SimStatus;
import es.jpargoteo.vodafone.devices.model.repository.SimRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {Application.class,
        SimRepository.class, SimService.class})
class SimServiceTest {

    @MockBean
    private SimRepository simRepository;

    @Autowired
    private SimService simService;

    private Sim sim;

    @BeforeEach
    void setUp() {

        initialize();
    }

    @Test
    void activate() {

        sim.setStatus(SimStatus.ACTIVE);

        given(simRepository.existsById(anyInt())).willReturn(true);
        given(simRepository.activate(anyInt())).willReturn(sim);

        simService.activate(1234);

        Assert.isTrue(SimStatus.ACTIVE == sim.getStatus(),
                "The resultant status is not the expected.");
    }

    @Test
    void activate_with_null_id() {

        sim.setStatus(SimStatus.ACTIVE);

        given(simRepository.existsById(anyInt())).willReturn(true);
        given(simRepository.activate(anyInt())).willReturn(sim);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            simService.activate(null);
        });
    }

    @Test
    void activate_with_no_existing_id() {

        sim.setStatus(SimStatus.ACTIVE);

        given(simRepository.existsById(anyInt())).willReturn(false);
        given(simRepository.activate(anyInt())).willReturn(sim);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            simService.activate(1234);
        });
    }

    @Test
    void block() {

        sim.setStatus(SimStatus.BLOCKED);

        given(simRepository.existsById(anyInt())).willReturn(true);
        given(simRepository.activate(anyInt())).willReturn(sim);

        simService.activate(1234);

        Assert.isTrue(SimStatus.BLOCKED == sim.getStatus(),
                "The resultant status is not the expected.");
    }

    @Test
    void block_with_null_id() {

        sim.setStatus(SimStatus.ACTIVE);

        given(simRepository.existsById(anyInt())).willReturn(true);
        given(simRepository.block(anyInt())).willReturn(sim);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            simService.block(null);
        });
    }

    @Test
    void block_with_no_existing_id() {

        sim.setStatus(SimStatus.ACTIVE);

        given(simRepository.existsById(anyInt())).willReturn(false);
        given(simRepository.block(anyInt())).willReturn(sim);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            simService.block(1234);
        });
    }

    @Test
    void deactivate() {

        sim.setStatus(SimStatus.DEACTIVATED);

        given(simRepository.existsById(anyInt())).willReturn(true);
        given(simRepository.activate(anyInt())).willReturn(sim);

        simService.activate(1234);

        Assert.isTrue(SimStatus.DEACTIVATED == sim.getStatus(),
                "The resultant status is not the expected.");
    }

    @Test
    void deactivate_with_null_id() {

        sim.setStatus(SimStatus.ACTIVE);

        given(simRepository.existsById(anyInt())).willReturn(true);
        given(simRepository.deactivate(anyInt())).willReturn(sim);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            simService.deactivate(null);
        });
    }

    @Test
    void deactivate_with_no_existing_id() {

        sim.setStatus(SimStatus.ACTIVE);

        given(simRepository.existsById(anyInt())).willReturn(false);
        given(simRepository.deactivate(anyInt())).willReturn(sim);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            simService.deactivate(1234);
        });
    }

    @Test
    void waitForActivation() {

        sim.setStatus(SimStatus.WAITING_FOR_ACTIVATION);

        given(simRepository.existsById(anyInt())).willReturn(true);
        given(simRepository.activate(anyInt())).willReturn(sim);

        simService.activate(1234);

        Assert.isTrue(SimStatus.WAITING_FOR_ACTIVATION == sim.getStatus(),
                "The resultant status is not the expected.");
    }

    @Test
    void waitForActivation_with_null_id() {

        sim.setStatus(SimStatus.ACTIVE);

        given(simRepository.existsById(anyInt())).willReturn(true);
        given(simRepository.waitForActivation(anyInt())).willReturn(sim);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            simService.waitForActivation(null);
        });
    }

    @Test
    void waitForActivation_with_no_existing_id() {

        sim.setStatus(SimStatus.ACTIVE);

        given(simRepository.existsById(anyInt())).willReturn(false);
        given(simRepository.waitForActivation(anyInt())).willReturn(sim);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            simService.waitForActivation(1234);
        });
    }

    private void initialize() {

        Sim mockSim = new Sim();
        mockSim.setId(4321);
        mockSim.setCountryName("Italy");
        mockSim.setStatus(SimStatus.ACTIVE);
        mockSim.setOperatorCode("Vodafone");

        sim = mockSim;
    }
}