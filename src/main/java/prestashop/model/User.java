package prestashop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import prestashop.model.enums.SocialTitle;

@Data
@AllArgsConstructor
public class User {
    private SocialTitle socialTitle;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthday;
}
