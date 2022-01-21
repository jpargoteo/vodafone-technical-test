package es.jpargoteo.vodafone.devices.model.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Class that describes a sim object.
 */
@Data
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "SIM")
public class Sim {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    private String operatorCode;
    private String countryName;
    private SimStatus status;
}
