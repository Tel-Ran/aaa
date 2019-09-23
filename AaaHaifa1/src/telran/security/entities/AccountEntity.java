package telran.security.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = {"username"})
@NoArgsConstructor
@Data
@Document(collection = "accounts")
public class AccountEntity {

    @Id
    ObjectId _id;

    @Indexed(unique = true)
    String username;

    String password;

    List<String> roles = new ArrayList<>();

    boolean revoked = false;
    LocalDateTime activationDate;
}
