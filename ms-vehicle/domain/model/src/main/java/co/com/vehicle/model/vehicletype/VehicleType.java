package co.com.vehicle.model.vehicletype;
import lombok.*;
//import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class VehicleType {
    private Integer id;
    private String segment;
    private String name;
}
