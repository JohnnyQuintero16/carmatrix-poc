package co.com.vehicle.model.exceptions;

import co.com.vehicle.model.util.MessageError;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BussinesExceptions extends RuntimeException {

    private final MessageError messageError;

    @Builder
    public BussinesExceptions(MessageError messageError, Throwable cause) {
        super(messageError.getMessage(), cause);
        this.messageError = messageError;
    }

    public BussinesExceptions(MessageError messageError) {
        super(messageError.getMessage());
        this.messageError = messageError;
    }
}
