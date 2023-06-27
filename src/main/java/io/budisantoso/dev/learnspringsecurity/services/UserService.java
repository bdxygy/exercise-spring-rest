package io.budisantoso.dev.learnspringsecurity.services;

import io.budisantoso.dev.learnspringsecurity.dto.UserDTO;
import io.budisantoso.dev.learnspringsecurity.entities.UserEntity;


import java.util.List;

public interface UserService extends UniversalService<UserDTO, UserEntity, Long> {
    UserEntity addRole(Long userId, List<Long> listRoleId);
}
