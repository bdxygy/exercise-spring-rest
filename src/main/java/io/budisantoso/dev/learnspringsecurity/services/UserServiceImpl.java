package io.budisantoso.dev.learnspringsecurity.services;

import io.budisantoso.dev.learnspringsecurity.dto.UserDTO;
import io.budisantoso.dev.learnspringsecurity.entities.RoleEntity;
import io.budisantoso.dev.learnspringsecurity.entities.UserEntity;
import io.budisantoso.dev.learnspringsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    final private UserRepository userRepository;
    final private RoleService roleService;
    final private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository ur, RoleService roleService, PasswordEncoder passwordEncoder) {
        userRepository = ur;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity create(UserDTO newData) {
        return userRepository.save(new UserEntity(newData.getName(), newData.getEmail(), passwordEncoder.encode(newData.getPassword())));
    }

    @Override
    public UserEntity findById(Long aLong) {
//        final Optional<UserEntity> user = userRepository.findById(aLong);
//
//        if (user.isEmpty()) {
//            throw new RuntimeException("User with ID " + aLong.toString() + " not found!");
//        }
//
//        return user.get();

        return userRepository.findById(aLong)
                .orElseThrow(
                        () -> new RuntimeException("User with ID " + aLong.toString() + " not found!")
                );
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity update(UserDTO updatedData) {
        UserEntity userExist = findById(updatedData.getId());
        userExist.setName(updatedData.getName());
        userExist.setEmail(updatedData.getEmail());

        return userRepository.save(userExist);
    }

    @Override
    public void delete(Long aLong) {
        UserEntity user = findById(aLong);
        userRepository.delete(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public UserEntity addRole(Long userId, List<Long> listRolesId) {
        final UserEntity user = findById(userId);
        final List<RoleEntity> roles = roleService.findAllById(listRolesId);
        user.getRoles().addAll(roles);

        return userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found!"));
    }
}
