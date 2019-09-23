package telran.security.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import telran.security.dto.validators.EnumValidator;
import telran.security.entities.Roles;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    String username;

    @EnumValidator(enumClass=Roles.class, message="Improper role choice")
    String role;
}
