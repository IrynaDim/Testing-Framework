package prestashop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddressData {
    private String address;
    private String zip;
    private String city;
    private String country;
}
