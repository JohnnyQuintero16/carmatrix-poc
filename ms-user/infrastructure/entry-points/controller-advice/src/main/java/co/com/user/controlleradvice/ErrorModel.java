package co.com.user.controlleradvice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorModel {
    private String code;
    private String message;
    private String exception;
}
