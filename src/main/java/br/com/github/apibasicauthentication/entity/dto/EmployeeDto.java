package br.com.github.apibasicauthentication.entity.dto;

import br.com.github.apibasicauthentication.entity.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * A DTO for the {@link Employee} entity
 */

@Getter
@ToString
@RequiredArgsConstructor
public class EmployeeDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3760445487636086034L;

    private final Long id;
    @NotBlank
    @Size(min = 3)
    private final String firstName;
    @NotBlank
    @Size(min = 3)
    private final String lastName;
    @Email
    @NotBlank
    private final String email;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
    }

}