package br.com.github.apibasicauthentication.entity.dto;

import br.com.github.apibasicauthentication.entity.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link Account} entity
 */

@Getter
@ToString
@RequiredArgsConstructor
public class AccountDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3760445487636086034L;

    private final Long id;
    private final String username;
    private final boolean enabled;
    private final boolean credentialsExpired;
    private final boolean expired;
    private final boolean locked;
    private final Set<RoleDto> roles = new HashSet<>();

    public AccountDto(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.enabled = account.isEnabled();
        this.credentialsExpired = account.isCredentialsExpired();
        this.expired = account.isExpired();
        this.locked = account.isLocked();
        account.getRoles().forEach(role -> this.roles.add(new RoleDto(role)));
    }

}