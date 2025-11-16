package co.com.vehicle.usecase.vehicletype;

import co.com.vehicle.model.exceptions.BussinesExceptions;
import co.com.vehicle.model.util.MessageError;
import co.com.vehicle.model.vehicletype.VehicleType;
import co.com.vehicle.model.vehicletype.gateways.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Loggers;

@Log
@RequiredArgsConstructor
public class VehicleTypeUseCase {

    private final VehicleTypeRepository vehicleTypeRepository;

    public Mono<VehicleType> registerVehicleType(VehicleType vehicleType) {
        if (vehicleType == null || vehicleType.getName() == null || vehicleType.getSegment() == null) {
            log.info("Error vehicle type " + vehicleType);
            return Mono.error(
                    BussinesExceptions.builder()
                            .messageError(MessageError.ERROR_DATA_INVALID)
                            .build()
            );
        }
        return vehicleTypeRepository.register(vehicleType)
                .doOnNext(v -> log.info("Registro exitoso en BD: {}"))
                .doOnError(e -> log.info("Error en registerVehicleType: {} " + e.getCause()))
                .onErrorMap(error ->
                        (error instanceof BussinesExceptions)
                                ? error.getCause()
                                : BussinesExceptions.builder()
                                .messageError(MessageError.ERROR_REGISTER_VEHICLE_TYPE)
                                .cause(error.getCause())
                                .build()
                );
    }

    public Mono<VehicleType> updateVehicleType(VehicleType vehicleType) {
        if (vehicleType == null || vehicleType.getId() == null) {
            return Mono.error(
                    BussinesExceptions.builder()
                            .messageError(MessageError.ERROR_DATA_INVALID)
                            .build()
            );
        }
        return vehicleTypeRepository.findById(vehicleType.getId())
                .switchIfEmpty(Mono.error(
                        BussinesExceptions.builder()
                                .messageError(MessageError.ERROR_VEHICLE_TYPE_NOT_FOUND)
                                .build()
                ))
                .flatMap(existing -> {
                    if (vehicleType.getName() == null || vehicleType.getSegment() == null) {
                        return Mono.error(
                                BussinesExceptions.builder()
                                        .messageError(MessageError.ERROR_DATA_INVALID)
                                        .build()
                        );
                    }
                    VehicleType updated = VehicleType.builder()
                            .id(existing.getId())
                            .name(vehicleType.getName())
                            .segment(vehicleType.getSegment())
                            .build();

                    return vehicleTypeRepository.update(updated);
                })
                .onErrorMap(error ->
                        (error instanceof BussinesExceptions)
                                ? error
                                : BussinesExceptions.builder()
                                .messageError(MessageError.ERROR_UPDATE_VEHICLE_TYPE)
                                .cause(error)
                                .build()
                );
    }

    public Mono<VehicleType> findByIdVehicleType(Integer id) {
        return vehicleTypeRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        BussinesExceptions.builder()
                                .messageError(MessageError.ERROR_VEHICLE_TYPE_NOT_FOUND)
                                .build()
                ))
                .onErrorMap(error ->
                        (error instanceof BussinesExceptions)
                                ? error
                                : BussinesExceptions.builder()
                                .messageError(MessageError.ERROR_VEHICLE_TYPE_SEARCH)
                                .cause(error)
                                .build()
                );
    }

    public Flux<VehicleType> findAllVehiclesTypes() {
        return vehicleTypeRepository.findAll()
                .onErrorMap(error ->
                        (error instanceof BussinesExceptions)
                                ? error
                                : BussinesExceptions.builder()
                                .messageError(MessageError.ERROR_NOT_FOUND_VEHICLES_TYPES)
                                .cause(error)
                                .build()
                );
    }
}
