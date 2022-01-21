package es.jpargoteo.vodafone.devices.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonRootName(value = "sim")
@Relation(collectionRelation = "sims")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimModel extends RepresentationModel<SimModel> {

    private String id;
    private String operatorCode;
    private String countryName;
    private String status;
}
