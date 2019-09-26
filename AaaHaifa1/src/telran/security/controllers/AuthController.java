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

import telran.security.dto.*;

import telran.security.services.AuthService;

@RestController

public class AuthController {
    final private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
        
    }

    @PostMapping(value=AccountingApiConstants.ADD_ACCOUNT)
    ResponseEntity<AccountingCodes> addAccount(@Valid @RequestBody AccountDto accountDto) {
        AccountingCodes res=authService.addAccount(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping(value=AccountingApiConstants.DELETE_ACCOUNT)
    ResponseEntity<AccountingCodes> deleteAccount(@RequestBody AccountDto accountDto) {
        AccountingCodes res = authService.removeAccount(accountDto.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping(value=AccountingApiConstants.UPDATE_PASSWORD)
    ResponseEntity<AccountingCodes> updatePassword(@RequestBody AccountDto accountDto
                                  ) {
        

        AccountingCodes res = authService.updatePassword(accountDto.getUsername(), accountDto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping(value=AccountingApiConstants.REVOKE_ACCOUNT)
    ResponseEntity<AccountingCodes> revokeAccount(@RequestBody AccountDto accountDto) {
       
        AccountingCodes res=authService.revokeAccount(accountDto.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping(value=AccountingApiConstants.ACTIVATE_ACCOUNT)
    ResponseEntity<AccountingCodes> activateAccount(@RequestBody AccountDto accountDto) {
        

        AccountingCodes res=authService.activateAccount(accountDto.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping(value=AccountingApiConstants.ADD_ROLE)
    ResponseEntity<AccountingCodes> addRole(@RequestBody RoleDto roleDto) {
        

        AccountingCodes res=authService.addRole(roleDto.getUsername(), roleDto.getRole());
      return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping(value=AccountingApiConstants.REMOVE_ROLE)
    ResponseEntity<AccountingCodes> removeRole(@Valid @RequestBody RoleDto roleDto) {
        

        AccountingCodes res=authService.removeRole(roleDto.getUsername(), roleDto.getRole());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    
        


 
}
