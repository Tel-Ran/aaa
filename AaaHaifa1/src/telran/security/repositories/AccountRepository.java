package telran.security.repositories;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import telran.security.entities.AccountEntity;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<AccountEntity, String> {
    boolean existsByUsername(String username);
    long deleteByUsername(String username);
    Optional<AccountEntity> findByUsername(String username);
}
