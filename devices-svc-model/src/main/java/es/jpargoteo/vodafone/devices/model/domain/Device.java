package es.jpargoteo.vodafone.devices.model.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

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
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    private Status status;
    private Double temperature;

    // Relationships
    @OneToOne
    private Sim sim;

}
