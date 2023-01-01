package br.com.github.apibasicauthentication.resource;

import br.com.github.apibasicauthentication.entity.dto.AccountDto;
import br.com.github.apibasicauthentication.entity.dto.CreateAccountDto;
import br.com.github.apibasicauthentication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/account")
public class AccountResource {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getEmployees() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountDto createAccountDto) {
        AccountDto accountDto = accountService.createAccount(createAccountDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(accountDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(accountDto);
    }

}
