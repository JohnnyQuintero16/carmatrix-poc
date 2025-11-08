package co.com.user.usecase.manageclient;

import co.com.user.model.client.Client;
import co.com.user.model.client.gateways.ClientRepository;
import co.com.user.model.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ManageClientUseCase {
    private final ClientRepository clientRepository;

    public Client registerClient(Client client) {
        return clientRepository.registerClient(client);
    }

    public Client updateClient(Client client) {
        Client isExistClient = findClientByUsername(client.getUsername());
        if(Objects.nonNull(isExistClient)) {
            Client newClient = Client.builder()
                    .id(isExistClient.getId())
                    .documentNumber(Objects.isNull(client.getDocumentNumber()) ? isExistClient.getDocumentNumber() : client.getDocumentNumber())
                    .documentType(Objects.isNull(client.getDocumentType()) ? isExistClient.getDocumentType() : client.getDocumentType())
                    .email(Objects.isNull(client.getEmail()) ? isExistClient.getEmail() : client.getEmail())
                    .phone(Objects.isNull(client.getPhone()) ? isExistClient.getPhone() : client.getPhone())
                    .addres(Objects.isNull(client.getAddres()) ? isExistClient.getAddres() : client.getAddres())
                    .firstName(Objects.isNull(client.getFirstName()) ? isExistClient.getFirstName() : client.getFirstName())
                    .lastName(Objects.isNull(client.getLastName()) ? isExistClient.getLastName() : client.getLastName())
                    .password(Objects.isNull(client.getPassword()) ? isExistClient.getPassword() : client.getPassword())
                    .username(isExistClient.getUsername())
                    .build();
            return clientRepository.updateClient(newClient);
        } else {
            throw new UserNotFoundException("User not found username:" + client.getUsername());
        }

    }

    public Client findClientByUsername(String username) {
        return clientRepository.findClientByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found username:" + username));
    }

    public List<Client> findAllClients() {
        return clientRepository.findAllClients();
    }

    public void removeClientByUsername(String username) {
        Client client = findClientByUsername(username);
        clientRepository.removeClientByUsername(client.getId());
    }

}
