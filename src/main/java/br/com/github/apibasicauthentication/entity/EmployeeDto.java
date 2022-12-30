package br.com.github.apibasicauthentication.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * A DTO for the {@link Employee} entity
 */

@Data
public class EmployeeDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3760445487636086034L;

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String username;

}