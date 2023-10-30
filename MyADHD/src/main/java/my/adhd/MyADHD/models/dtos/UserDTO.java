package my.adhd.MyADHD.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String email;

}
