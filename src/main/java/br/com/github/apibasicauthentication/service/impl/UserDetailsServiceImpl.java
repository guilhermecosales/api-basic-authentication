package br.com.github.apibasicauthentication.service.impl;

import br.com.github.apibasicauthentication.entity.Account;
import br.com.github.apibasicauthentication.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

        if (account.getRoles() == null || account.getRoles().isEmpty()) {
            throw new RuntimeException();
        }

        Collection<GrantedAuthority> authorities = account.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return new User(
                account.getUsername(),
                account.getPassword(),
                account.isEnabled(),
                account.isExpired(),
                !account.isCredentialsExpired(),
                !account.isLocked(),
                authorities
        );
    }

}
