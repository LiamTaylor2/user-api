package userapi.model;

import lombok.Data;
import userapi.domain.User;

@Data
public class UserCreate {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public User map(User user){
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(this.password);
        return user;
    }
}
