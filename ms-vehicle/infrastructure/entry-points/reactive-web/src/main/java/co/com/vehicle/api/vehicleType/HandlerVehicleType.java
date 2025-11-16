package co.com.vehicle.api.vehicleType;

import co.com.vehicle.api.model.ResponseStatus;
import co.com.vehicle.api.util.MessageResponse;
import co.com.vehicle.api.util.ResponseUtil;
import co.com.vehicle.api.vehicleType.request.UpdateVehicleTypeRequest;
import co.com.vehicle.api.vehicleType.request.VehicleTypeRequest;
import co.com.vehicle.model.exceptions.BussinesExceptions;
import co.com.vehicle.model.util.MessageError;
import co.com.vehicle.model.vehicletype.VehicleType;
import co.com.vehicle.usecase.vehicletype.VehicleTypeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerVehicleType {
    private  final VehicleTypeUseCase vehicleTypeUseCase;

    public Mono<ServerResponse> listenPOSTRegisterVehicleTypeUseCase(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(VehicleTypeRequest.class)
                .switchIfEmpty(Mono.error(new BussinesExceptions(MessageError.ERROR_DATA_INVALID)))
                .flatMap(request -> {
                    if (request.getName() == null || request.getSegment() == null) {
                        return Mono.error(new BussinesExceptions(MessageError.ERROR_DATA_INVALID));
                    }
                    return vehicleTypeUseCase.registerVehicleType(
                            VehicleType.builder()
                                    .name(request.getName())
                                    .segment(request.getSegment())
                                    .build()
                    );
                })
                .flatMap(vehicleType ->
                        ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(ResponseStatus.builder()
                                        .code(MessageResponse.RESPONSE_SUCCES.getCode())
                                        .message(MessageResponse.RESPONSE_SUCCES.getMessage())
                                        .object(vehicleType)
                                        .build())
                )
                .onErrorResume(ResponseUtil::handleError);
    }

    public Mono<ServerResponse> listenPUTUpdateVehicleTypeUseCase(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(UpdateVehicleTypeRequest.class)
                .switchIfEmpty(Mono.error(BussinesExceptions.builder()
                                .messageError(MessageError.ERROR_UPDATE_VEHICLE_TYPE)
                                .build()))
                .flatMap(request -> {
                    return vehicleTypeUseCase.updateVehicleType(
                            VehicleType.builder()
                                    .id(request.getId())
                                    .name(request.getName())
                                    .segment(request.getSegment())
                                    .build());
                })
                .doOnSuccess(response -> System.out.println("Registro actualizado correctamente " + response))
                .flatMap(vehicleType ->
                    ServerResponse.status(HttpStatus.ACCEPTED)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(ResponseStatus.builder()
                                    .code(MessageResponse.RESPONSE_SUCCES.getCode())
                                    .message(MessageResponse.RESPONSE_SUCCES.getMessage())
                                    .object(vehicleType)
                                    .build())
                )
                .onErrorResume(ResponseUtil::handleError);
    }

    public Mono<ServerResponse> listenGETFindByIdVehicleTypeUseCase(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return vehicleTypeUseCase.findByIdVehicleType(Integer.valueOf(id))
                .doOnSuccess(response -> System.out.println("objeto encontrado"))
                .doOnError(response -> System.out.println("Objeto no encontrado " + response))
                .switchIfEmpty(Mono.error(BussinesExceptions.builder()
                                .messageError(MessageError.ERROR_VEHICLE_TYPE_NOT_FOUND)
                                .build()))
                .flatMap(request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(ResponseStatus.builder()
                                .code(MessageResponse.RESPONSE_SUCCES.getCode())
                                .message(MessageResponse.RESPONSE_SUCCES.getMessage())
                                .object(request)
                                .build()))
                .onErrorResume(ResponseUtil::handleError);
    }

    public Mono<ServerResponse> listGETFindAllVehiclesTypes(ServerRequest request) {
        return vehicleTypeUseCase.findAllVehiclesTypes()
                .collectList()
                .doOnSuccess(response -> System.out.println("lista de objetos -> " + response))
                .doOnError(response -> System.out.println("Error al consultar la lista -> " + response))
                .flatMap(list -> {
                    if (list.isEmpty()) {
                        return Mono.error(BussinesExceptions.builder()
                                .messageError(MessageError.ERROR_NOT_FOUND_VEHICLES_TYPES)
                                .build());
                    }
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(ResponseStatus.builder()
                                    .code(MessageResponse.RESPONSE_SUCCES.getCode())
                                    .message(MessageResponse.RESPONSE_SUCCES.getMessage())
                                    .object(list)
                                    .build());
                })
                .onErrorResume(ResponseUtil::handleError);
    }

}
