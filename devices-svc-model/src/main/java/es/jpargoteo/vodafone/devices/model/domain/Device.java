package es.jpargoteo.vodafone.devices.model.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Class that describes a device object.
 */
@Data
@Entity
@Table(name = "DEVICE")
public class Device {

    // Attributes
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private Status status;
    private Double temperature;

    // Relationships
    @OneToOne
    private Sim sim;
}
