package co.com.vehicle.r2dbc.repositoryAdapter;

import co.com.vehicle.model.exceptions.BussinesExceptions;
import co.com.vehicle.model.util.MessageError;
import co.com.vehicle.model.vehicletype.VehicleType;
import co.com.vehicle.r2dbc.entity.VehicleTypeEntity;
import co.com.vehicle.r2dbc.helper.ReactiveAdapterOperations;
import co.com.vehicle.r2dbc.repository.VehicleTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class VehicleTypeRepositoryAdapter extends ReactiveAdapterOperations<
        VehicleType,
        VehicleTypeEntity,
        Integer,
        VehicleTypeRepository
        > implements co.com.vehicle.model.vehicletype.gateways.VehicleTypeRepository {

    public VehicleTypeRepositoryAdapter(VehicleTypeRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, VehicleType.class));
    }

    @Override
    public Mono<VehicleType> register(VehicleType vehicleType) {
        return this.save(vehicleType)
                .onErrorMap(error -> BussinesExceptions.builder()
                        .messageError(MessageError.ERROR_REGISTER_VEHICLE_TYPE)
                        .cause(error)
                        .build());
    }

    @Override
    public Mono<VehicleType> update(VehicleType vehicleType) {
        return this.save(vehicleType)
                .onErrorMap(error -> BussinesExceptions.builder()
                        .messageError(MessageError.ERROR_UPDATE_VEHICLE_TYPE)
                        .cause(error)
                        .build());
    }

    @Override
    public Mono<Void> remove(Integer id) {
        return repository.deleteById(id)
                .onErrorMap(error -> BussinesExceptions.builder()
                        .messageError(MessageError.ERROR_DELETE_VEHICLE_TYPE)
                        .cause(error)
                        .build());
    }

}
