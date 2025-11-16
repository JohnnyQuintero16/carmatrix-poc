package co.com.vehicle.api.vehicleType;

import co.com.vehicle.api.util.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class VehicleTypeRouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(HandlerVehicleType handlerVehicleType) {
        return route(GET(Constants.ENDPOINT_VEHICLE_TYPE), handlerVehicleType::listGETFindAllVehiclesTypes)
                .andRoute(GET(Constants.ENDPOINT_FIND_VEHICLE_TYPE), handlerVehicleType::listenGETFindByIdVehicleTypeUseCase)
                .andRoute(POST(Constants.ENDPOINT_VEHICLE_TYPE), handlerVehicleType::listenPOSTRegisterVehicleTypeUseCase)
                .andRoute(PUT(Constants.ENDPOINT_VEHICLE_TYPE), handlerVehicleType::listenPUTUpdateVehicleTypeUseCase);
    }
}
