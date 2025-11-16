package co.com.vehicle.api.util;

import co.com.vehicle.api.model.ResponseStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class ResponseUtil {
    public static Mono<ServerResponse> handleError(Throwable error) {
        return ServerResponse.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(ResponseStatus.builder()
                        .code(MessageResponse.RESPONSE_ERROR.getCode())
                        .message(error.getMessage())
                        .build());
    }
}
