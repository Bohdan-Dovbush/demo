package com.example.demo.validation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FailedLogin {
    private Integer count;
    private LocalDateTime date;

    public FailedLogin() {
        this.count = 0;
        this.date = LocalDateTime.now();
    }

    public FailedLogin(Integer count, LocalDateTime date) {
        this.count = count;
        this.date = date;
    }
}
