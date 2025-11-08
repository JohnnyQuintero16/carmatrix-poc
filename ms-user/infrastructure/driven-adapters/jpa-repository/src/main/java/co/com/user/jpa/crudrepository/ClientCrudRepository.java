package co.com.user.jpa.crudrepository;

import co.com.user.jpa.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface ClientCrudRepository extends CrudRepository<ClientEntity, Integer>, QueryByExampleExecutor<ClientEntity> {
    Optional<ClientEntity> findByUsername(String username);
}
