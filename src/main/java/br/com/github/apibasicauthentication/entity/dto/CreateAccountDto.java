package br.com.github.apibasicauthentication.entity.dto;

import br.com.github.apibasicauthentication.entity.Account;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * A DTO for the {@link Account} entity
 */

@Getter
@ToString
public class CreateAccountDto extends AccountDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3760445487636086034L;

    @NotBlank
    private final String password;

    public CreateAccountDto(Long id, String username, boolean enabled, boolean credentialsExpired, boolean expired, boolean locked, String password) {
        super(id, username, enabled, credentialsExpired, expired, locked);
        this.password = password;
    }
}