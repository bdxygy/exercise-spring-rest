package io.budisantoso.dev.learnspringsecurity.rest;

import io.budisantoso.dev.learnspringsecurity.dto.AddRoleDTO;
import io.budisantoso.dev.learnspringsecurity.dto.UserDTO;
import io.budisantoso.dev.learnspringsecurity.entities.UserEntity;
import io.budisantoso.dev.learnspringsecurity.services.UserService;
import io.budisantoso.dev.learnspringsecurity.utils.Null;
import io.budisantoso.dev.learnspringsecurity.utils.UniversalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/v1/users", "v1/users"})
public class UserController implements UniversalController<UserDTO, UserEntity, Null, Long> {

    final private UserService userService;

    public UserController(UserService us) {
        userService = us;
    }

    @Override
    public ResponseEntity<UniversalResponse<UserEntity>> save(UserDTO request) {
        UserEntity user = userService.create(request);
        UniversalResponse<UserEntity> response = new UniversalResponse<>(HttpStatus.CREATED.value(), "Create new user success", user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UniversalResponse<List<UserEntity>>> findAll() {
        List<UserEntity> users = userService.findAll();
        UniversalResponse<List<UserEntity>> response = new UniversalResponse<>(HttpStatus.OK.value(), "Get all users success", users);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UniversalResponse<UserEntity>> findById(Long id) {
        UserEntity user = userService.findById(id);
        UniversalResponse<UserEntity> response = new UniversalResponse<>(HttpStatus.OK.value(), "Get user success", user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UniversalResponse<UserEntity>> update(UserDTO request) {
        UserEntity user = userService.update(request);
        UniversalResponse<UserEntity> response = new UniversalResponse<>(HttpStatus.ACCEPTED.value(), "Update user success", user);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<UniversalResponse<Null>> delete(Long aLong) {
        userService.delete(aLong);
        UniversalResponse<Null> response = new UniversalResponse<>(HttpStatus.ACCEPTED.value(), "Delete user success");

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<UniversalResponse<Null>> deleteAll() {
        userService.deleteAll();
        UniversalResponse<Null> response = new UniversalResponse<>(HttpStatus.ACCEPTED.value(), "Delete all users success");

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping({"roles", "/roles"})
    public ResponseEntity<UniversalResponse<UserEntity>> addRole(@RequestBody AddRoleDTO addRoleDTO) {
        UserEntity user = userService.addRole(addRoleDTO.getUserId(), addRoleDTO.getListRolesId());
        UniversalResponse<UserEntity> response = new UniversalResponse<>(HttpStatus.CREATED.value(), "Add role for user success", user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
