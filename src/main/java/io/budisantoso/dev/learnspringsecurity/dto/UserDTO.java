package io.budisantoso.dev.learnspringsecurity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.budisantoso.dev.learnspringsecurity.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @JsonIgnore
    private Long id;
    private String name;
    private String email;
    private List<RoleEntity> roles;
    private String password;

    public UserDTO(String name, String email, List<RoleEntity> roles) {
        this.name = name;
        this.email = email;
        this.roles = roles;

    }

    public UserDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
