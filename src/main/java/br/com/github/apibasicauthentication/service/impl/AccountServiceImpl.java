package br.com.github.apibasicauthentication.service.impl;

import br.com.github.apibasicauthentication.entity.Account;
import br.com.github.apibasicauthentication.entity.Role;
import br.com.github.apibasicauthentication.entity.dto.AccountDto;
import br.com.github.apibasicauthentication.entity.dto.CreateAccountDto;
import br.com.github.apibasicauthentication.entity.dto.RoleDto;
import br.com.github.apibasicauthentication.repository.AccountRepository;
import br.com.github.apibasicauthentication.repository.RoleRepository;
import br.com.github.apibasicauthentication.service.AccountService;
import br.com.github.apibasicauthentication.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountDto createAccount(CreateAccountDto createAccountDto) {
        Account account = new Account();
        account.setPassword(passwordEncoder.encode(createAccountDto.getPassword()));
        fromDtoToEntity(createAccountDto, account);
        account = accountRepository.save(account);
        return new AccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream().map(AccountDto::new).collect(Collectors.toList());
    }

    @Override
    public AccountDto findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new NotFoundException("Username " + username + "not found");
        }

        return new AccountDto(account);
    }

    private void fromDtoToEntity(AccountDto accountDto, Account account) {
        account.setUsername(accountDto.getUsername());
        account.setEnabled(accountDto.isEnabled());
        account.setCredentialsExpired(accountDto.isCredentialsExpired());
        account.setExpired(accountDto.isExpired());
        account.setLocked(accountDto.isLocked());

        account.getRoles().clear();
        for (RoleDto roleDto : accountDto.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDto.getId());
            account.getRoles().add(role);
        }
    }

}
