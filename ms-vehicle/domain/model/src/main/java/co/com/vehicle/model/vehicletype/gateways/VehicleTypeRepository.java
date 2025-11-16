package co.com.vehicle.model.vehicletype.gateways;

import co.com.vehicle.model.vehicletype.VehicleType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VehicleTypeRepository {
    Mono<VehicleType> register(VehicleType vehicleType);
    Mono<VehicleType> update(VehicleType vehicleType);
    Mono<VehicleType> findById(Integer id);
    Flux<VehicleType> findAll();
    Mono<Void> remove(Integer id);
}
