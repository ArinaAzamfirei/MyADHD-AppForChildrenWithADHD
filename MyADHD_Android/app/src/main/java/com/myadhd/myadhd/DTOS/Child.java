package com.myadhd.myadhd.DTOS;



public class Child {
    private Integer id;
    private String firstname;
    private String lastname;
    private String nickname;
    private Integer age;
    private String gender;
    private String code;

    public Child(String firstname, String lastname, String nickname, Integer age, String gender, String code){
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id = " + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public Integer getAge(){
        return age;
    }
    public String getGender(){
        return gender;
    }
    public Integer getChildId(){return id;}

    public String getCode() {
        return code;
    }
}
