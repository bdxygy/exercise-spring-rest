package io.budisantoso.dev.learnspringsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRoleDTO {
    private Long userId;
    private List<Long> listRolesId;
}
