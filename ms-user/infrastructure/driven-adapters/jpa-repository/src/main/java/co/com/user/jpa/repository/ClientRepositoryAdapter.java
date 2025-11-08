package co.com.user.jpa.repository;

import co.com.user.jpa.crudrepository.ClientCrudRepository;
import co.com.user.jpa.entity.ClientEntity;
import co.com.user.jpa.helper.AdapterOperations;
import co.com.user.model.client.Client;
import co.com.user.model.client.gateways.ClientRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryAdapter extends AdapterOperations<Client, ClientEntity, Integer, ClientCrudRepository>
    implements ClientRepository
{

    public ClientRepositoryAdapter(ClientCrudRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Client.class));
    }

    @Override
    public Client registerClient(Client client) {
        ClientEntity clientEntity = mapper.map(client, ClientEntity.class);
        return mapper.map(repository.save(clientEntity), Client.class);
    }

    @Override
    public Client updateClient(Client client) {
        ClientEntity clientEntity = mapper.map(client, ClientEntity.class);
        return mapper.map(repository.save(clientEntity), Client.class);
    }

    @Override
    public Optional<Client> findClientByUsername(String username) {
        Optional<ClientEntity> clientEntity = repository.findByUsername(username);
        return clientEntity.map(entity -> mapper.map(entity, Client.class));
    }

    @Override
    public List<Client> findAllClients() {
        Iterable<ClientEntity> clientEntityList = repository.findAll();
        List<Client> clientList = new ArrayList<>();
        for (ClientEntity clientEntity : clientEntityList) {
            clientList.add(mapper.map(clientEntity, Client.class));
        }
        return clientList;
    }

    @Override
    public void removeClientByUsername(Integer id) {
        repository.deleteById(id);
    }
}
