package es.jpargoteo.vodafone.devices.ws.service;

import es.jpargoteo.vodafone.devices.api.dto.DeviceModel;
import es.jpargoteo.vodafone.devices.api.dto.SimModel;
import es.jpargoteo.vodafone.devices.boot.Application;
import es.jpargoteo.vodafone.devices.model.business.impl.DeviceService;
import es.jpargoteo.vodafone.devices.model.business.impl.SimService;
import es.jpargoteo.vodafone.devices.model.domain.Device;
import es.jpargoteo.vodafone.devices.model.domain.Sim;
import es.jpargoteo.vodafone.devices.model.domain.SimStatus;
import es.jpargoteo.vodafone.devices.model.domain.Status;
import es.jpargoteo.vodafone.devices.ws.assembler.DeviceAssembler;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = DeviceController.class)
@ContextConfiguration(classes = {Application.class, DeviceController.class,
        DeviceAssembler.class, SimService.class, DeviceService.class})
@EnableSpringDataWebSupport
class DeviceControllerTest {

    @MockBean
    private DeviceService deviceService;

    @MockBean
    private DeviceAssembler deviceAssembler;

    @MockBean
    private SimService simService;

    @Autowired
    private DeviceController deviceController;

    @Autowired
    private MockMvc mockMvc;

    private static String BASE_PATH = "http://localhost/devices";
    private static String MNG_BASE_PATH = "http://localhost/mng/devices";

    private Page<Device> devices;

    private Device device;

    private DeviceModel deviceModel;

    private Sim sim;

    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();

        initialize();

        given(deviceAssembler.toModel(any(Device.class))).willReturn(deviceModel);
    }

    @Test
    void available_returns_ok() throws Exception {

        mockMvc = MockMvcBuilders
                .standaloneSetup(deviceController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        given(deviceService.allAvailable(any(PageRequest.class))).willReturn(devices);

        final ResultActions result = mockMvc.perform(get(BASE_PATH + "/available?page=1&size=10"));

        result.andExpect(status().is2xxSuccessful());
    }

    @Test
    void waitingActivation_returns_ok() throws Exception {

        mockMvc = MockMvcBuilders
                .standaloneSetup(deviceController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        given(deviceService.allAvailable(any(PageRequest.class))).willReturn(devices);

        final ResultActions result = mockMvc.perform(get(BASE_PATH + "/waiting-activation?page=1&size=10"));

        result.andExpect(status().is2xxSuccessful());
    }

    @Test
    void removeConfiguration_returns_ok() throws Exception {

        given(deviceService.removeConfiguration(anyInt())).willReturn(device);

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/remove-configuration"));

        result.andExpect(status().is2xxSuccessful());
    }

    @Test
    void removeConfiguration_returns_bad_request() throws Exception {

        given(deviceService.removeConfiguration(anyInt())).willThrow(new IllegalArgumentException());

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/remove-configuration"));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void removeConfiguration_returns_internal_error() throws Exception {

        given(deviceService.removeConfiguration(anyInt())).willThrow(new NullPointerException());

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/remove-configuration"));

        result.andExpect(status().isInternalServerError());
    }

    @Test
    void activateSim_returns_ok() throws Exception {

        given(deviceService.one(anyInt())).willReturn(device);
        given(simService.activate(anyInt())).willReturn(sim);

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/sim/activate"));

        result.andExpect(status().is2xxSuccessful());
    }

    @Test
    void activateSim_returns_bad_request() throws Exception {

        given(deviceService.one(anyInt())).willThrow(new IllegalArgumentException());
        given(simService.activate(anyInt())).willReturn(sim);

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/sim/activate"));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void activateSim_returns_internal_error() throws Exception {

        given(deviceService.one(anyInt())).willThrow(new NullPointerException());
        given(simService.activate(anyInt())).willReturn(sim);

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/sim/activate"));

        result.andExpect(status().isInternalServerError());
    }

    @Test
    void deactivateSim_returns_ok() throws Exception {

        given(deviceService.one(anyInt())).willReturn(device);
        given(simService.activate(anyInt())).willReturn(sim);

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/sim/deactivate"));

        result.andExpect(status().is2xxSuccessful());

    }

    @Test
    void deactivateSim_returns_bad_request() throws Exception {

        given(deviceService.one(anyInt())).willThrow(new IllegalArgumentException());
        given(simService.activate(anyInt())).willReturn(sim);

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/sim/deactivate"));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void deactivateSim_returns_internal_error() throws Exception {

        given(deviceService.one(anyInt())).willThrow(new NullPointerException());
        given(simService.activate(anyInt())).willReturn(sim);

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/sim/deactivate"));

        result.andExpect(status().isInternalServerError());
    }

    @Test
    void blockSim_returns_ok() throws Exception {

        given(deviceService.one(anyInt())).willReturn(device);
        given(simService.activate(anyInt())).willReturn(sim);

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/sim/block"));

        result.andExpect(status().is2xxSuccessful());

    }

    @Test
    void blockSim_returns_bad_request() throws Exception {

        given(deviceService.one(anyInt())).willThrow(new IllegalArgumentException());
        given(simService.activate(anyInt())).willReturn(sim);

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/sim/block"));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void blockSim_returns_internal_error() throws Exception {

        given(deviceService.one(anyInt())).willThrow(new NullPointerException());
        given(simService.activate(anyInt())).willReturn(sim);

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/sim/block"));

        result.andExpect(status().isInternalServerError());
    }

    @Test
    void setWaitingActivationSim_returns_ok() throws Exception {

        given(deviceService.one(anyInt())).willReturn(device);
        given(simService.activate(anyInt())).willReturn(sim);

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/sim/waiting-activation"));

        result.andExpect(status().is2xxSuccessful());

    }

    @Test
    void setWaitingActivationSim_returns_bad_request() throws Exception {

        given(deviceService.one(anyInt())).willThrow(new IllegalArgumentException());
        given(simService.activate(anyInt())).willReturn(sim);

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/sim/waiting-activation"));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void setWaitingActivationSim_returns_internal_error() throws Exception {

        given(deviceService.one(anyInt())).willThrow(new NullPointerException());
        given(simService.activate(anyInt())).willReturn(sim);

        final ResultActions result = mockMvc.perform(post(MNG_BASE_PATH + "/1234/sim/waiting-activation"));

        result.andExpect(status().isInternalServerError());
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

        deviceModel = DeviceModel
                .builder()
                .id(String.valueOf(mockDevice.getId()))
                .status(mockDevice.getStatus().toString())
                .temperature(String.valueOf(mockDevice.getTemperature()))
                .sim(SimModel
                        .builder()
                        .id(String.valueOf(mockSim.getId()))
                        .countryName(mockSim.getCountryName())
                        .status(mockSim.getStatus().toString())
                        .operatorCode(mockSim.getOperatorCode())
                        .build())
                .build();

        device = mockDevice;
        devices = new PageImpl<>(Lists.newArrayList(mockDevice));
        sim = mockSim;
    }
}