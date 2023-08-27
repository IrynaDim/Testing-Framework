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
    private String birthdate;

    private String address;
    private String zip;
    private String city;
    private String country;

    public User(SocialTitle socialTitle, String firstName, String lastName,
                String email, String password, String birthdate) {
        this.socialTitle = socialTitle;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
    }
}
