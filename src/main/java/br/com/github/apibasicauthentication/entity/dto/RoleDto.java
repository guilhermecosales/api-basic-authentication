package br.com.github.apibasicauthentication.entity.dto;

import br.com.github.apibasicauthentication.entity.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link Role} entity
 */

@Getter
@ToString
@RequiredArgsConstructor
public class RoleDto implements Serializable {

    private final Long id;
    private final String code;
    private final String name;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.code = role.getCode();
        this.name = role.getName();
    }

}