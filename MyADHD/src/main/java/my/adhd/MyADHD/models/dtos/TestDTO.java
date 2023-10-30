package my.adhd.MyADHD.models.dtos;


import lombok.Data;

@Data
public class TestDTO {


    private String timestamp;

    private String diagnostic;
    private Integer percentage;

    private String childCode;
}
