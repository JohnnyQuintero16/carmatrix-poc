package co.com.vehicle.api.util;

import lombok.Getter;

@Getter
public enum MessageResponse {

    RESPONSE_SUCCES("200", "SUCCESS"),
    RESPONSE_ERROR("400", "ERROR");

    private String code;
    private String message;

    MessageResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
