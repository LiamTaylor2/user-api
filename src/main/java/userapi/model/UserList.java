package userapi.model;

import lombok.Data;
import userapi.domain.User;

@Data
public class UserList {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    public UserList(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }
}
