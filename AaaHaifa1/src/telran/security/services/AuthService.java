package telran.security.services;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.security.dto.AccountDto;
import telran.security.dto.AccountingCodes;
import telran.security.entities.AccountEntity;
import telran.security.entities.Roles;
import telran.security.repositories.AccountRepository;


@Service
public class AuthService implements IAuthService {

    final private AccountRepository accountRepository;

    final private ModelMapper modelMapper;

    @Autowired
    public AuthService(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountingCodes addAccount(AccountDto account) {
        AccountEntity accountEntity = modelMapper.map(account, AccountEntity.class);
        
        accountEntity.getRoles().add(Roles.USER.toString());
      //  accountEntity.setPassword(hashPassword(accountEntity.getPassword()));
        accountRepository.save(accountEntity);
        return AccountingCodes.OK;
    }

    @Override
    public AccountingCodes removeAccount(String username) {
        if (accountRepository.deleteByUsername(username) == 0) {
            return AccountingCodes.ACCOUNT_NOT_EXISTS;
        }
        return AccountingCodes.OK;
    }

    @Override
    public AccountingCodes updatePassword(String username, String password) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return AccountingCodes.ACCOUNT_NOT_EXISTS;
        }
//        if (accountEntity.getPassword().equals(hashPassword(password))) {
//        	return AccountingCodes.SAME_PASSWORD;
//        }
//        accountEntity.setPassword(hashPassword(password));
        accountRepository.save(accountEntity);
        return AccountingCodes.OK;
    }

    @Override
    public AccountingCodes revokeAccount(String username) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return AccountingCodes.ACCOUNT_NOT_EXISTS;
        }
        accountEntity.setRevoked(true);
        accountRepository.save(accountEntity);
        return AccountingCodes.OK;
    }

    @Override
    public AccountingCodes activateAccount(String username) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return AccountingCodes.ACCOUNT_NOT_EXISTS;
        }
        accountEntity.setRevoked(false);
        accountRepository.save(accountEntity);
        return AccountingCodes.OK;
    }

    @Override
    public String getPasswordHash(String username) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return null;
        }
        return accountEntity.getPassword();
    }

    @Override
    public LocalDateTime getActivationDate(String username) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return null;
        }
        return LocalDateTime.ofEpochSecond(accountEntity.get_id().getTimestamp(), 0, ZoneOffset.ofHours(0));
    }

    @Override
    public HashSet<String> getRoles(String username) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return null;
        }
        return new HashSet<>(accountEntity.getRoles());
    }

    @Override
    public AccountingCodes addRole(String username, String role) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return null;
        }
        accountEntity.getRoles().add(role);
        accountRepository.save(accountEntity);
        return AccountingCodes.OK;
    }

    @Override
    public AccountingCodes removeRole(String username, String role) {
        AccountEntity accountEntity = accountRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(accountEntity)) {
            return null;
        }
        accountEntity.getRoles().remove(role);
        accountRepository.save(accountEntity);
        return AccountingCodes.OK;
    }

    public boolean isUsernameAlreadyInUse(String username) {
        return accountRepository.existsByUsername(username);
    }

    public AccountEntity getUserByUsername(String username) {
        return accountRepository.findByUsername(username).orElse(null);
    }

}
