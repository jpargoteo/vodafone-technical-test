package es.jpargoteo.vodafone.devices.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonRootName(value = "device")
@Relation(collectionRelation = "devices")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceModel extends RepresentationModel<DeviceModel> {

    // Attributes
    private String id;
    private String status;
    private String temperature;

    //Relationships
    private SimModel sim;
}
