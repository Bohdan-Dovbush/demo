package com.example.demo.validation;

import lombok.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
public class UserData implements Serializable {

    @NotEmpty(message = "{registration.validation.firstName}")
    private String firstName;

    @NotEmpty(message = "{registration.validation.lastName}")
    private String lastName;

    @Email(message = "{registration.validation.email}")
    private String email;

    @NotEmpty(message = "{registration.validation.password}")
    private String password;
}
