package io.budisantoso.dev.learnspringsecurity.repositories;

import io.budisantoso.dev.learnspringsecurity.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByEmail(String email);
}
