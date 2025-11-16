package co.com.vehicle.api.vehicleType.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder(toBuilder = true)
public class VehicleTypeRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("segment")
    private String segment;

}
