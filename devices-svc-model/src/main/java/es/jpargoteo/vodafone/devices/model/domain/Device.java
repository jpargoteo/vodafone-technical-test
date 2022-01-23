package es.jpargoteo.vodafone.devices.model.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Class that describes a device object.
 */
@Data
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "DEVICE")
public class Device {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private Status status;
    private Double temperature;

    // Relationships
    @OneToOne
    private Sim sim;
}
