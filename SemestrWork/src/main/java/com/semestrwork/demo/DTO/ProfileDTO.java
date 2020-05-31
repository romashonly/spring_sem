package com.semestrwork.demo.DTO;

import lombok.Data;

import java.io.File;

@Data
public class ProfileDTO {
    private String login;
    private String password;
    private String phone_number;
    private String name;
    private String serName;
    private String city;
    private String date_birth;
}
