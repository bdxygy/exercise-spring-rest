package io.budisantoso.dev.learnspringsecurity.repositories;

import io.budisantoso.dev.learnspringsecurity.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
