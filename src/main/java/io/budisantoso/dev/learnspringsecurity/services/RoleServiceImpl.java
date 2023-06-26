package io.budisantoso.dev.learnspringsecurity.services;

import io.budisantoso.dev.learnspringsecurity.dto.RoleDTO;
import io.budisantoso.dev.learnspringsecurity.entities.RoleEntity;
import io.budisantoso.dev.learnspringsecurity.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository rs) {
        roleRepository = rs;
    }

    @Override
    public RoleEntity create(RoleDTO newData) {
        return roleRepository.save(new RoleEntity("ROLE_" + newData.getName().toUpperCase()));
    }

    @Override
    public RoleEntity findById(Long id) {
        final Optional<RoleEntity> role = roleRepository.findById(id);

        if (role.isEmpty()) {
            throw new RuntimeException("Role with ID "+ id.toString() +" Not Found!");
        }

        return role.get();
    }

    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity update(RoleDTO updatedData) {
        if (updatedData.getId() == null) {
            throw new IllegalArgumentException("Please insert the specific Role ID for update!");
        }

        final RoleEntity existRole = findById(updatedData.getId());
        existRole.setName("ROLE_" + updatedData.getName().toUpperCase());
        return roleRepository.save(existRole);
    }

    @Override
    public void delete(Long id) {
        final RoleEntity role = findById(id);
        roleRepository.delete(role);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }
}
