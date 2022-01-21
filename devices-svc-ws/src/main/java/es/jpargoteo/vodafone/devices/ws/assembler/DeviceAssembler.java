package es.jpargoteo.vodafone.devices.ws.assembler;

import es.jpargoteo.vodafone.devices.api.dto.DeviceModel;
import es.jpargoteo.vodafone.devices.api.dto.SimModel;
import es.jpargoteo.vodafone.devices.model.domain.Device;
import es.jpargoteo.vodafone.devices.model.domain.Sim;
import es.jpargoteo.vodafone.devices.ws.service.DeviceController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Class that extends {@link RepresentationModelAssemblerSupport} to provide the methods to convert from entity to model
 * representations.
 */
@Component
public class DeviceAssembler extends RepresentationModelAssemblerSupport<Device, DeviceModel> {

    /**
     * Constructor of the class.
     */
    public DeviceAssembler() {
        super(DeviceController.class, DeviceModel.class);
    }

    /**
     * Method that given a {@link Device} entity converts it into a {@link DeviceModel} representation.
     *
     * @param entity - The given entity.
     * @return - The resultant representation.
     */
    @Override
    public DeviceModel toModel(Device entity) {

        DeviceModel deviceModel = instantiateModel(entity);

        deviceModel.add(linkTo(methodOn(DeviceController.class).available(null)).withSelfRel());

        deviceModel.setId(String.valueOf(entity.getId()));
        deviceModel.setStatus(entity.getStatus().toString());
        deviceModel.setTemperature(String.valueOf(entity.getTemperature()));
        deviceModel.setSim(toSimModel(entity.getSim()));

        return deviceModel;
    }

    /**
     * Methods that given an {@link Iterable} of {@link Device} entities converts them into model representations.
     *
     * @param entities - The given iterable of devices.
     * @return - The resultant collection of representations.
     */
    public CollectionModel<DeviceModel> toCollectionModel(Iterable<? extends Device> entities) {

        CollectionModel<DeviceModel> deviceModels = super.toCollectionModel(entities);

        deviceModels.add(linkTo(methodOn(DeviceController.class).available(null)).withSelfRel());
        deviceModels.add(linkTo(methodOn(DeviceController.class).activateSim(null)).withSelfRel());

        return deviceModels;
    }

    /**
     * Method that given a {@link Sim} entity converts it into a {@link SimModel} representation.
     *
     * @param entity - The given sim entity.
     * @return - The resultant representation.
     */
    private SimModel toSimModel(Sim entity) {

        if (entity == null) {

            return null;
        }

        return SimModel
                .builder()
                .id(String.valueOf(entity.getId()))
                .countryName(entity.getCountryName())
                .operatorCode(entity.getOperatorCode())
                .status(entity.getStatus().toString())
                .build();
    }
}
