package co.com.vehicle.r2dbc.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("VehicleType")   // importante: en minúscula
public class VehicleTypeEntity {

    @Id
    private Integer id;

    @Column("segment")
    private String segment;

    @Column("name")
    private String name;
}
