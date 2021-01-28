package userapi.model;

import lombok.Data;
import userapi.domain.User;

@Data
public class UserEdit {
    private Long id;

    private String firstName;

    private String lastName;

    public UserEdit(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public User map(User user){
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        return user;
    }
}
