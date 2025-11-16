package co.com.vehicle.r2dbc.repository;

import co.com.vehicle.r2dbc.entity.VehicleTypeEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface VehicleTypeRepository extends ReactiveCrudRepository<VehicleTypeEntity, Integer>, ReactiveQueryByExampleExecutor<VehicleTypeEntity> {
}
