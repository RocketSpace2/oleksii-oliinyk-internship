package user.service.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import user.service.domain.model.User;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private final String email;
    private  String password;
    private final String firstName;
    private final String lastName;
    private  String roleName;

    public UserDTO(String email, String password, String firstName, String lastName){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static UserDTO fromDomain(User user) {
        return new UserDTO(
                user.id(),
                user.email(),
                user.password(),
                user.firstName(),
                user.lastName(),
                user.role().roleName()
        );
    }

    public User toDomain() {
        return new User(id, password, email, firstName, lastName, null);
    }
}
