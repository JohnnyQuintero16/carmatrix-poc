package co.com.user.model.client.gateways;

import co.com.user.model.client.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client registerClient(Client client);
    Client updateClient(Client client);
    Optional<Client> findClientByUsername(String username);
    List<Client> findAllClients();
    void removeClientByUsername(Integer id);
}
