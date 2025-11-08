package co.com.user.api.mapper;

import co.com.user.api.request.UserRequest;
import co.com.user.api.response.UserResponse;
import co.com.user.model.client.Client;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper {
    public Client toDomain(UserRequest userRequest) {
        return Client.builder()
                .documentNumber(userRequest.getDocumentNumber())
                .documentType(userRequest.getDocumentType())
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .addres(userRequest.getAddres())
                .phone(userRequest.getPhone())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();
    }

    public UserResponse.userData toResponseData(Client client) {
        return UserResponse.userData.builder()
                .documentNumber(client.getDocumentNumber())
                .documentType(client.getDocumentType())
                .email(client.getEmail())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .addres(client.getAddres())
                .phone(client.getPhone())
                .username(client.getUsername())
                .build();
    }
}
