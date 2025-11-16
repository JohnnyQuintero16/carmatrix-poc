package co.com.vehicle.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder(toBuilder = true)
public class ResponseStatus {
    private String code;
    private String message;
    private Object object;
}
