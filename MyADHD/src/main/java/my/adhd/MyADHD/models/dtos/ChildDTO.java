package my.adhd.MyADHD.models.dtos;

import lombok.Data;

@Data
public class ChildDTO {

    private Integer id;
    private String firstname;
    private String lastname;
    private String nickname;
    private Integer age;
    private String gender;
    private String code;
}
