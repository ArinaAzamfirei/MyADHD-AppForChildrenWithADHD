package my.adhd.MyADHD.models.dtos;

import lombok.Data;

@Data
public class QuestionDTO {
    private Long number;
    private String text;
    private String qTypeId;
}
