package telran.security.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import telran.security.dto.validators.UniqueUsernameValidator;

import javax.validation.constraints.Size;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @Size(min = 6, max = 10, message = "Username should be from 6 to 10 symbols")
    @UniqueUsernameValidator(message = "User with provided username already exists")
    String username;
    @Size(min = 6, message = "Password should be 6 symbols or longer")
    String password;
    
}
