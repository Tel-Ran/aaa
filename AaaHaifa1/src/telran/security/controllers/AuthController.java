package telran.security.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.security.dto.AccountDto;
import telran.security.dto.AccountingCodes;
import telran.security.dto.RoleDto;
import telran.security.entities.AccountEntity;
import telran.security.services.AuthService;

@RestController
@RequestMapping("/accounts")
public class AuthController {
    final private AuthService authService;
    final private ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    ResponseEntity<AccountDto> addAccount(@Valid @RequestBody AccountDto accountDto) {
        authService.addAccount(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    }

    @DeleteMapping("/delete")
    ResponseEntity<AccountingCodes> deleteAccount(@RequestBody AccountDto accountDto,
                                 @RequestHeader("Authorization") String authData) {

//        ResponseEntity x = authRequest(accountDto.getUsername(), authData);
//        if (x != null) return x;

        AccountingCodes res = authService.removeAccount(accountDto.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping("/password")
    ResponseEntity<AccountingCodes> updatePassword(@RequestBody AccountDto accountDto,
                                  @RequestHeader("Authorization") String authData) {
        ResponseEntity<AccountingCodes> x = authRequest(accountDto.getUsername(), authData);
        if (x != null) return x;

        AccountingCodes res = authService.updatePassword(accountDto.getUsername(), accountDto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping("/revoke")
    ResponseEntity revokeAccount(@RequestBody AccountDto accountDto,
                                 @RequestHeader("Authorization") String authData) {
        ResponseEntity x = authRequest(accountDto.getUsername(), authData);
        if (x != null) return x;

        authService.revokeAccount(accountDto.getUsername());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/activate")
    ResponseEntity activateAccount(@RequestBody AccountDto accountDto,
                                   @RequestHeader("Authorization") String authData) {
        ResponseEntity x = authRequestSuperuser(accountDto.getUsername(), authData);
        if (x != null) return x;

        authService.activateAccount(accountDto.getUsername());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/roles/add")
    ResponseEntity addRole(@Valid @RequestBody RoleDto roleDto,
                           @RequestHeader("Authorization") String authData) {
        ResponseEntity x = authRequestSuperuser(roleDto.getUsername(), authData);
        if (x != null) return x;

        authService.addRole(roleDto.getUsername(), roleDto.getRole());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/roles/remove")
    ResponseEntity removeRole(@Valid @RequestBody RoleDto roleDto,
                              @RequestHeader("Authorization") String authData) {
        ResponseEntity x = authRequestSuperuser(roleDto.getUsername(), authData);
        if (x != null) return x;

        authService.removeRole(roleDto.getUsername(), roleDto.getRole());
        return new ResponseEntity(HttpStatus.OK);
    }


    private ResponseEntity authRequest(String username, String authData) {
		return null;
    }
        

//        String passwordHash = authService.getPasswordHash(userDetails[0]);
//        if (Objects.isNull(passwordHash)) {
//            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//        }
//        if (!passwordHash.equals(hashPassword(userDetails[1]))) {
//            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//        }
//        AccountEntity accountEntity = authService.getUserByUsername(username);
//        if (accountEntity.isRevoked()) {
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
//        }
//        if (!userDetails[0].equals(accountEntity.getUsername()) && !accountEntity.getRoles().contains("SUPERUSER")) {
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
//        }
//        return null;
 //   }

    private ResponseEntity authRequestSuperuser(String username, String authData) {
//      //  String[] userDetails = decodeAuthHeader(authData);
//        String passwordHash = authService.getPasswordHash(userDetails[0]);
//        if (Objects.isNull(passwordHash)) {
//            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//        }
//        if (!passwordHash.equals(hashPassword(userDetails[1]))) {
//            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//        }
//        AccountEntity accountEntity = authService.getUserByUsername(username);
//        if (accountEntity.isRevoked()) {
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
//        }
//        if (!accountEntity.getRoles().contains("SUPERUSER")) {
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
//        }
        return null;
    }
}
