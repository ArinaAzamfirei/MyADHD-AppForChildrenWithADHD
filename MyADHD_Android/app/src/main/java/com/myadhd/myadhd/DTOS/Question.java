package com.myadhd.myadhd.DTOS;

public class Question {

    private Long number;
    private String text;
    private String qTypeId;

    public Question(Long number, String text, String qTypeId){
        this.number = number;
        this.text = text;
        this.qTypeId = qTypeId;
    }

    public Long getNumber(){return number;}
    public String getText(){return text;}
    public String getQTypeId(){return qTypeId;}

}
