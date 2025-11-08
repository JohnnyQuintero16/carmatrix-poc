package co.com.user.api;
import co.com.user.api.mapper.UserRequestMapper;
import co.com.user.api.request.UserRequest;
import co.com.user.api.response.UserListResponse;
import co.com.user.api.response.UserResponse;
import co.com.user.model.client.Client;
import co.com.user.usecase.manageclient.ManageClientUseCase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserRest {

    private final ManageClientUseCase manageClientUseCase;
    private final UserRequestMapper userRequestMapper;

    @PostMapping(path = "/")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        Client client = userRequestMapper.toDomain(userRequest);
        UserResponse userResponse = UserResponse.builder()
                .code("200")
                .message("Usuario registrado exitosamente")
                .userData(userRequestMapper.toResponseData(manageClientUseCase.registerClient(client)))
                .build();
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping(path = "/update")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
        Client client = userRequestMapper.toDomain(userRequest);
        UserResponse userResponse = UserResponse.builder()
                .code("200")
                .message("Usuario actualizado exitosamente")
                .userData(userRequestMapper.toResponseData(manageClientUseCase.updateClient(client)))
                .build();
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("username") String username) {
        UserResponse userResponse = UserResponse.builder()
                .code("200")
                .message("Consulta exitosa")
                .userData(userRequestMapper.toResponseData(manageClientUseCase.findClientByUsername(username)))
                .build();
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<UserListResponse> findAllUser() {
        List<Client> clientList = manageClientUseCase.findAllClients();
        List<UserResponse.userData> userDataList = clientList.stream()
                .map(client -> userRequestMapper.toResponseData(client))
                .toList();
        UserListResponse userListResponse = UserListResponse.builder()
                .code("200")
                .message("listado de usuarios")
                .userDataList(userDataList)
                .build();
        return ResponseEntity.ok(userListResponse);
    }

    @DeleteMapping(path = "/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username) {
        manageClientUseCase.removeClientByUsername(username);
        return new ResponseEntity<>("Usuario eliminado", HttpStatus.ACCEPTED);
    }
}
