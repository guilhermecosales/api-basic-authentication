package br.com.github.apibasicauthentication.service;

import br.com.github.apibasicauthentication.entity.dto.CreateAccountDto;
import br.com.github.apibasicauthentication.entity.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(CreateAccountDto createAccountDto);

    List<AccountDto> getAllAccounts();

    AccountDto findByUsername(String username);

}
