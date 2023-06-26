package io.budisantoso.dev.learnspringsecurity.rest;

import io.budisantoso.dev.learnspringsecurity.dto.RoleDTO;
import io.budisantoso.dev.learnspringsecurity.entities.RoleEntity;
import io.budisantoso.dev.learnspringsecurity.services.RoleService;
import io.budisantoso.dev.learnspringsecurity.utils.Null;
import io.budisantoso.dev.learnspringsecurity.utils.UniversalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v1/roles")
public class RoleController implements UniversalController<RoleDTO, RoleEntity, Null, Long> {

    final private RoleService roleService;

    @Autowired
    public RoleController(RoleService rs) {
        roleService = rs;
    }

    @Override

    public ResponseEntity<UniversalResponse<RoleEntity>> save(RoleDTO request) {
        RoleEntity role = roleService.create(request);
        UniversalResponse<RoleEntity> response = new UniversalResponse<>(HttpStatus.CREATED.value(), "Create new role success", role);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UniversalResponse<List<RoleEntity>>> findAll() {
        List<RoleEntity> roles = roleService.findAll();
        UniversalResponse<List<RoleEntity>> response = new UniversalResponse<>(HttpStatus.OK.value(), "Get all roles success", roles);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UniversalResponse<RoleEntity>> findById(Long id) {
        RoleEntity role = roleService.findById(id);
        UniversalResponse<RoleEntity> response = new UniversalResponse<>(HttpStatus.OK.value(), "Find role success", role);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UniversalResponse<RoleEntity>> update(RoleDTO request) {
        RoleEntity role = roleService.update(request);
        UniversalResponse<RoleEntity> response = new UniversalResponse<>(HttpStatus.ACCEPTED.value(), "Update role success", role);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<UniversalResponse<Null>> delete(Long id) {
        roleService.delete(id);
        UniversalResponse<Null> response = new UniversalResponse<>(HttpStatus.ACCEPTED.value(), "Delete role success");

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<UniversalResponse<Null>> deleteAll() {
        roleService.deleteAll();
        UniversalResponse<Null> response = new UniversalResponse<>(HttpStatus.ACCEPTED.value(), "Delete all roles success");

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
