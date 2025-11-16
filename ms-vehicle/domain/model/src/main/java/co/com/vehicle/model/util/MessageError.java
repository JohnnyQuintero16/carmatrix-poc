package co.com.vehicle.model.util;

import lombok.Getter;

@Getter
public enum MessageError {
    ERROR_GENERIC("500", "Internal server error"),
    ERROR_REGISTER_VEHICLE_TYPE("400", "error register vehicle type"),
    ERROR_DATA_INVALID("400", "invalid data register vehicle type"),
    ERROR_UPDATE_VEHICLE_TYPE("400", "error update vehicle type"),
    ERROR_VEHICLE_TYPE_NOT_FOUND("404", "not found vehicle type"),
    ERROR_VEHICLE_TYPE_SEARCH("500", "error search vehicle type"),
    ERROR_NOT_FOUND_VEHICLES_TYPES("404", "not existe vehicles types"),
    ERROR_DELETE_VEHICLE_TYPE("500", "error delete vehicle type");


    private String code;
    private String message;

    MessageError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
