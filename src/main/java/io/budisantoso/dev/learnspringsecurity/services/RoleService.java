package io.budisantoso.dev.learnspringsecurity.services;

import io.budisantoso.dev.learnspringsecurity.dto.RoleDTO;
import io.budisantoso.dev.learnspringsecurity.entities.RoleEntity;

import java.util.List;

public interface RoleService extends UniversalService<RoleDTO,RoleEntity, Long>{
    void delete(Long id);
}
