package es.jpargoteo.vodafone.devices.model.business.impl;

import es.jpargoteo.vodafone.devices.boot.Application;
import es.jpargoteo.vodafone.devices.model.domain.Device;
import es.jpargoteo.vodafone.devices.model.domain.Sim;
import es.jpargoteo.vodafone.devices.model.domain.SimStatus;
import es.jpargoteo.vodafone.devices.model.domain.Status;
import es.jpargoteo.vodafone.devices.model.repository.DeviceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {Application.class,
        DeviceRepository.class, DeviceService.class})
class DeviceServiceTest {

    @MockBean
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceService deviceService;

    private Device device;

    @BeforeEach
    void setUp() {

        initialize();
    }

    @Test
    void removeConfiguration() {

        device.setSim(null);

        given(deviceRepository.existsById(anyInt())).willReturn(true);

        given(deviceRepository.findById(anyInt())).willReturn(Optional.ofNullable(device));

        Assert.isTrue(null == deviceService.removeConfiguration(1234).getSim(),
                "The device still has a sim");
    }

    @Test
    void removeConfiguration_with_null_id() {

        given(deviceRepository.existsById(anyInt())).willReturn(true);

        given(deviceRepository.findById(anyInt())).willReturn(Optional.ofNullable(device));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceService.removeConfiguration(null);
        });
    }

    @Test
    void removeConfiguration_with_no_existing_id() {


        given(deviceRepository.existsById(anyInt())).willReturn(false);

        given(deviceRepository.findById(anyInt())).willReturn(Optional.ofNullable(device));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceService.removeConfiguration(1234);
        });
    }

    @Test
    void one() {

        given(deviceRepository.findById(anyInt())).willReturn(Optional.ofNullable(device));

        Assert.notNull(deviceService.one(1234), "The device cannot be null");
    }

    @Test
    void one_with_null_id() {

        given(deviceRepository.findById(anyInt())).willReturn(Optional.ofNullable(device));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceService.one(null);
        });
    }

    @Test
    void one_with_no_existing_id() {

        given(deviceRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));

        Assert.isNull(deviceService.one(123), "The device is not null");
    }

    private void initialize() {

        Sim mockSim = new Sim();
        mockSim.setId(4321);
        mockSim.setCountryName("Italy");
        mockSim.setStatus(SimStatus.ACTIVE);
        mockSim.setOperatorCode("Vodafone");

        Device mockDevice = new Device();
        mockDevice.setSim(mockSim);
        mockDevice.setStatus(Status.NOT_READY);
        mockDevice.setTemperature(25.00);
        mockDevice.setId(1234L);

        device = mockDevice;
    }
}