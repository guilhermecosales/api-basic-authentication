package br.com.github.apibasicauthentication.entity.dto;

import br.com.github.apibasicauthentication.entity.Account;
import lombok.*;

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

    private final String password;

    public CreateAccountDto(Account account, String password) {
        super(account);
        this.password = password;
    }
}