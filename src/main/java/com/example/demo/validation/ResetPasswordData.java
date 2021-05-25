package com.example.demo.validation;

import lombok.*;

@Getter
@Setter
public class ResetPasswordData {

    private String email;
    private String token;
    private String password;
    private String repeatPassword;
}
